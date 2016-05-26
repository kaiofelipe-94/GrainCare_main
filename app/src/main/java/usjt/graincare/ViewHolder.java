package usjt.graincare;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kaio on 21/05/2016.
 */
public class ViewHolder {
    private ImageView fotoSilo;
    private TextView nomeSilo, tipoGrao;

    public ViewHolder(ImageView fotoSilo, TextView nomeSilo, TextView tipoGrao) {
        this.fotoSilo = fotoSilo;
        this.nomeSilo = nomeSilo;
        this.tipoGrao = tipoGrao;
    }


    public TextView getNomeSilo() {
        return nomeSilo;
    }

    public void setNomeSilo(TextView nomeSilo)
    {
        this.nomeSilo = nomeSilo;
    }

    public TextView getTipoGrao()
    {
        return tipoGrao;
    }

    public void setTipoGrao(TextView tipoGrao) {
        this.tipoGrao = tipoGrao;
    }

    public ImageView getFotoSilo() {
        return fotoSilo;
    }

    public void setFotoSilo(ImageView fotoSilo) {
        this.fotoSilo = fotoSilo;
    }
}
