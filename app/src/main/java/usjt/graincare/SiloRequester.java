package usjt.graincare;

/**
 * Created by kaio on 14/05/2016.
 */

import android.app.VoiceInteractor;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Request;
import okhttp3.Response;

/*import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;*/

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
// Vai fazer a requisição na API REST

public class SiloRequester
{

    OkHttpClient client = new OkHttpClient();

    public ArrayList<Silo> get(String url, String pOpcao) throws IOException, JSONException
    {

        ArrayList<Silo> lista = new ArrayList<Silo>();

        //acentuacao nao funciona se mandar via get, mesmo usando URLEncode.encode(String,UTF-8)
        RequestBody formBody = new FormBody.Builder()
                .add("opcao", pOpcao)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();

        String jsonStr = response.body().string();

        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));


        JSONArray root = new JSONArray(jsonStr);
        JSONObject item = null;
        for (int i = 0; i < root.length(); i++ ) {
            item = (JSONObject)root.get(i);

            int id = item.getInt("id");
            int idArduino = item.getInt("idArduino");
            String nomeSilo = item.getString("nomeSilo");
            String tipoGrao = item.getString("tipoGrao");
            double capacidadeSilo = item.getDouble("capacidadeSilo");
            String dataFechamento = item.getString("dataFechamento");
            String dataAbertura = item.getString("dataAbertura");
            String localizacao = item.getString("localizacao");

            lista.add(new Silo(id, idArduino, nomeSilo, tipoGrao, capacidadeSilo, dataFechamento, dataAbertura, localizacao));
        }
        return lista;
    }
    public Bitmap getImage(String url) throws IOException {

        Bitmap img = null;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        InputStream is = response.body().byteStream();

        img = BitmapFactory.decodeStream(is);

        is.close();

        return img;
    }

    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context
                        .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }


}