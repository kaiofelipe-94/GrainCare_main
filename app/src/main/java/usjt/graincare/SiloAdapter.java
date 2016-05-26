package usjt.graincare;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;

/**
 * Created by kaio on 21/05/16.
 */
public class SiloAdapter extends BaseAdapter implements SectionIndexer
{
    Activity context;
    Silo[] silos;
    Object[] sectionHeaders;
    Hashtable<Integer, Integer> positionForSectionMap;
    Hashtable<Integer, Integer> sectionForPositionMap;

    public SiloAdapter(Activity context, Silo[] silos){
        this.context = context;
        this.silos = silos;
        sectionHeaders = usjt.graincare.SectionIndexBuilder.BuildSectionHeaders(silos);
        positionForSectionMap = usjt.graincare.SectionIndexBuilder.BuildPositionForSectionMap(silos);
        sectionForPositionMap = SectionIndexBuilder.BuildSectionForPositionMap(silos);

    }
    @Override
    public int getCount() {
        return silos.length;
    }

    @Override
    public Object getItem(int position) {
        if(position >= 0 && position < silos.length)
            return silos[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //o list view recicla os layouts para melhor performance
        //o layout reciclado vem no parametro convert view
        View view = convertView;
        //se nao recebeu um layout para reutilizar deve inflar um
        if(view == null) {
            //um inflater transforma um layout em uma view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.silo_row, parent, false);

            ImageView imgSilo = (ImageView)view.findViewById(R.id.fotoSiloImageView);
            TextView nomeSilo = (TextView)view.findViewById(R.id.nomeSiloTextView);
            TextView tipoGrao = (TextView)view.findViewById(R.id.tipoGraoTextView);



            //faz cache dos widgets instanciados na tag da view para reusar quando houver reciclagem
            view.setTag(new ViewHolder(imgSilo, nomeSilo, tipoGrao));
        }
        //usa os widgets cacheados na view reciclada
        ViewHolder holder = (ViewHolder)view.getTag();
        //carrega os novos valores
        Drawable drawable = Util.getDrawable(context, "silo2");
        holder.getFotoSilo().setImageDrawable(drawable);
        Locale locale = new Locale("pt", "BR");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        String status = "";

        if(silos[position].getDataAbertura().trim().equals("")) status = "fechado";
        else status = "aberto";

        holder.getNomeSilo().setText(silos[position].getNomeSilo());
        holder.getTipoGrao().setText(String.format("%s  -   %s  -   %s", silos[position].getTipoGrao(),"Status: " + status,silos[position].getCapacidadeSilo() + " quilos"));

        return view;
    }
//metodos da interface SectionIndexer


    @Override
    public Object[] getSections() {
        return sectionHeaders;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionForSectionMap.get(sectionIndex).intValue();
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionForPositionMap.get(position).intValue();
    }
}
