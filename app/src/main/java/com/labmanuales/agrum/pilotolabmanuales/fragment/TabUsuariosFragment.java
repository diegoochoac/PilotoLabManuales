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
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseCrud;
import com.labmanuales.agrum.pilotolabmanuales.db.Usuario;
import com.labmanuales.agrum.pilotolabmanuales.fragment.utils.ListUsuariosAdapter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.List;



/**
 * Created by diego on 4/09/16.
 */
public class TabUsuariosFragment extends Fragment implements OnClickListener{

    private DatabaseCrud database;
    private List<Usuario> usuarioList;
    private ListUsuariosAdapter adapterUsuario= null;

    private ListView listView;
    Context thiscontext;

    public TabUsuariosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_fragment_usuarios, container, false);
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
        Log.i("UsuariosFragment", "onStop");
        database.releaseHelper();
        super.onStop();
    }

    private void inicializarComponentes(View view) {
        listView = (ListView) view.findViewById(R.id.listViewUsuario);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                int id = adapterUsuario.getItem(i).getUsuarioId();
                database.updateSyncStatusUsuario(String.valueOf(id),"Si");
                poblarLista(); //TODO:Cambiar aqui no se realiza la actualizacion
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
        usuarioList = database.obtenerUsuarios();
        Log.i("UsuariosFragment", "poblarLista tamaño: "+usuarioList.size());
        if(usuarioList.size()>0 && usuarioList !=null){
            adapterUsuario = new ListUsuariosAdapter(thiscontext,R.layout.list_usuario_row,usuarioList);
            listView.setAdapter(adapterUsuario);
        }
        Log.i("UsuariosFragment", "No sincronizados: "+database.dbSyncCountUsuario());
    }

    @Override
    public void onClick(View view) {


    }

    public void syncSQLiteMySQLDB(){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        if(usuarioList.size()!=0){
            if(database.dbSyncCountUsuario()!=0){

                params.put("usersJSON", database.composeJSONfromSQLiteUsuario());


            }else{
                Toast.makeText(thiscontext, "SQLite and Remote MySQL DBs are in Sync!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(thiscontext, "No data in SQLite DB, please do enter User name to perform Sync action", Toast.LENGTH_LONG).show();
        }
    }



}