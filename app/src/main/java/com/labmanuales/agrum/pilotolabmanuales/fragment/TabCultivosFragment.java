package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.Cultivo;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseCrud;
import com.labmanuales.agrum.pilotolabmanuales.fragment.utils.ListCultivosAdapter;

import java.util.List;

/**
 * Created by diego on 1/09/16.
 */
public class TabCultivosFragment extends Fragment implements OnClickListener {

    private DatabaseCrud database;
    private List<Cultivo> cultivoList;
    private ListCultivosAdapter adapterCultivo = null;


    private ListView listView;
    Context thiscontext;


    public TabCultivosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_fragment_cultivos, container, false);
        database = new DatabaseCrud(container.getContext());
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }

    @Override
    public void onStart() {
        Log.i("CultivosFragment", "onStart");
        poblarLista();
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.i("CultivosFragment", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("CultivosFragment", "onStop");
        super.onStop();
    }

    private void inicializarComponentes(View view) {
        listView = (ListView) view.findViewById(R.id.listViewCultivo);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(thiscontext, "presiono " + i, Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(thiscontext, "presiono LARGO " + i, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void poblarLista(){
        //Consulta los cultivos que se crean para visualizarlos en pantalla
        cultivoList = database.obtenerCutivos();
        Log.i("CultivosFragment", "poblarLista tamaño: "+cultivoList.size());
        if(cultivoList.size()>0 && cultivoList != null) {
            adapterCultivo = new ListCultivosAdapter(thiscontext, R.layout.list_cultivo_row, cultivoList);
            listView.setAdapter(adapterCultivo);
        }
    }

    @Override
    public void onClick(View view) {

    }
}
