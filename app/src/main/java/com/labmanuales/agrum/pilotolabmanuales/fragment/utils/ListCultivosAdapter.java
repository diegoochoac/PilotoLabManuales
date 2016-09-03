package com.labmanuales.agrum.pilotolabmanuales.fragment.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.Cultivo;

import java.util.List;

/**
 * Created by diego on 1/09/16.
 */
public class ListCultivosAdapter extends ArrayAdapter<Cultivo> {

    private Context mContext;
    private int row;
    private List<Cultivo> list ;

    public ListCultivosAdapter(Context context, int textViewResourceId, List<Cultivo> list) {
        super(context, textViewResourceId, list);
        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if ((list == null) || ((position + 1) > list.size()))
            return view; // Can't extract item

        Cultivo obj = list.get(position);

        holder.nombre = (TextView)view.findViewById(R.id.list_row_title);
        holder.area =  (TextView)view.findViewById(R.id.list_row_area);
        holder.plantas = (TextView)view.findViewById(R.id.list_row_plantas);
        holder.imagen = (ImageView) view.findViewById(R.id.list_row_image);


        if(null!=holder.nombre && null!=obj && obj.getCultivoNombre().length()!=0){
            holder.nombre.setText(obj.getCultivoNombre());
            holder.area.setText("Area: "+obj.getCultivoArea());
            holder.plantas.setText("N° Plantas: "+obj.getCultivoPlantas());
            //holder.imagen.setImageResource(Integer.parseInt(obj.getCultivoFoto())); ///TODO:Falta mostrar la imagen
        }
        return view;
    }

    public static class ViewHolder {
        public TextView nombre;
        public TextView area;
        public TextView plantas;
        public ImageView imagen;
    }


}