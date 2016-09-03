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

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.Cultivo;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseHelper;
import com.labmanuales.agrum.pilotolabmanuales.fragment.utils.ListCultivosAdapter;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by diego on 1/09/16.
 */
public class CultivosFragment extends Fragment implements OnClickListener {

    private DatabaseHelper databaseHelper = null;
    private Dao<Cultivo,Integer> cultivoDao;
    private List<Cultivo> cultivoList;
    private ListCultivosAdapter adapterCultivo = null;


    private ListView listView;

    Context thiscontext;


    public CultivosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.cultivos_fragment, container, false);
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }


    private DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        Log.i("CultivosFragment", "onDestroy");
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
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

        //poblarLista();
    }

    public void poblarLista(){
        //Consulta los cultivos que se crean para visualizarlos en pantalla
        try{
            Log.i("datossssss", "datos:"+cultivoList);
            cultivoDao = getHelper().getCultivoDao();
            cultivoList = cultivoDao.queryForAll();
            if(cultivoList.size()>0){
                adapterCultivo = new ListCultivosAdapter(thiscontext,R.layout.list_cultivo_row,cultivoList);
                listView.setAdapter(adapterCultivo);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {

    }
}
