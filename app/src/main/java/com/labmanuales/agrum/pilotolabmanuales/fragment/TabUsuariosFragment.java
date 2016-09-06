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
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        syncSQLiteMySQLDB(); //TODO: sincroniza la base de datos con el servidor
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
                //database.updateSyncStatusUsuario(String.valueOf(id),"No");
                //poblarLista(); //TODO:Cambiar aqui no se realiza la actualizacion
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
                client.post("http://agrum.net16.net/agrumlabmanuales/insertuserandroid.php", params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("UsuariosFragment","syncSQLiteMySQLDB composeJSONfromSQLiteUsuario: "+database.composeJSONfromSQLiteUsuario());

                        try {
                            Log.i("UsuariosFragment","syncSQLiteMySQLDB response: "+response);
                            JSONArray arr = new JSONArray(response);
                            for(int i=0; i<arr.length();i++){
                                JSONObject obj = (JSONObject)arr.get(i);
                                database.updateSyncStatusUsuario(obj.get("usuario_id").toString(),obj.get("updateState").toString());
                                Log.i("UsuariosFragment","updateSyncStatusUsuario id: "+obj.get("usuario_id").toString()+" status: "+obj.get("updateState").toString());
                            }
                            Toast.makeText(thiscontext, "DB Sync completed!", Toast.LENGTH_LONG).show();
                            poblarLista();
                        } catch (JSONException e) {
                            Toast.makeText(thiscontext, "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable error, String content) {
                        if(statusCode == 404){
                            Toast.makeText(thiscontext, "Requested resource not found", Toast.LENGTH_LONG).show();
                        }else if(statusCode == 500){
                            Toast.makeText(thiscontext, "Something went wrong at server end", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(thiscontext, "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet]", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }else{
                Toast.makeText(thiscontext, "SQLite and Remote MySQL DBs are in Sync!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(thiscontext, "No data in SQLite DB, please do enter User name to perform Sync action", Toast.LENGTH_LONG).show();
        }
    }



}