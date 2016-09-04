package com.labmanuales.agrum.pilotolabmanuales.fragment.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.Usuario;

import java.util.List;

/**
 * Created by diego on 4/09/16.
 */
public class ListUsuariosAdapter extends ArrayAdapter<Usuario> {

    private Context mContext;
    private int row;
    private List<Usuario> list ;

    public ListUsuariosAdapter(Context context, int textViewResourceId, List<Usuario> list) {
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

        Usuario obj = list.get(position);

        holder.nombre = (TextView)view.findViewById(R.id.list_row_title_usuario);
        holder.campo2 =  (TextView)view.findViewById(R.id.list_row_campo2_usuario);
        holder.campo3 = (TextView)view.findViewById(R.id.list_row_campo3_usuario);
        holder.imagen = (ImageView) view.findViewById(R.id.list_row_image);


        if(null!=holder.nombre && null!=obj && obj.getUsuarioNombre().length()!=0){
            holder.nombre.setText(obj.getUsuarioNombre());
            holder.campo2.setText("Telefono: "+obj.getUsuarioTelefono()); //TODO: colocar otra informacion puede ser el tiempo en horas
            holder.campo3.setText("Sincrinizado: "+obj.getUpdateState()); //TODO: colocar otra informacion puede ser el tiempo en horas
            //holder.imagen.setImageResource(Integer.parseInt(obj.getCultivoFoto())); ///TODO:Falta mostrar la imagen
        }
        return view;
    }

    public static class ViewHolder {
        public TextView nombre;
        public TextView campo2;
        public TextView campo3;
        public ImageView imagen;
    }



}
