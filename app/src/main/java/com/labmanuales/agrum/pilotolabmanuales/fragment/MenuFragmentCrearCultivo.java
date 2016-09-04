package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.Cultivo;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseHelper;
import com.labmanuales.agrum.pilotolabmanuales.fragment.utils.ListCultivosAdapter;

import java.sql.SQLException;
import java.util.List;

public class MenuFragmentCrearCultivo extends Fragment implements OnClickListener{


    private EditText nombre,area, plantas;
    private ImageView foto;
    private Button Btnagregar;
    private int request_code = 1;

    private DatabaseHelper databaseHelper = null;
    private Dao<Cultivo,Integer> cultivoDao;
    private List<Cultivo> cultivoList;
    private ListCultivosAdapter adapterCultivo = null;


    public MenuFragmentCrearCultivo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment_crear_cultivo, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    protected DatabaseHelper getHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }

    @Override
    public void onDestroy() {
        Log.i("FragmentCrearCultivo", "onDestroy");
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    @Override
    public void onPause() {
        Log.i("FragmentCrearCultivo", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("FragmentCrearCultivo", "onStop");
        super.onStop();
    }

    private void inicializarComponentes(final View view) {
        nombre = (EditText)view.findViewById(R.id.cmpNombreCultivo);
        area = (EditText)view.findViewById(R.id.cmpAreaCultivo);
        plantas = (EditText)view.findViewById(R.id.cmpPlantasCultivo);
        foto = (ImageView)view.findViewById(R.id.imageCargarCultivo);
        foto.setOnClickListener(this);
        Btnagregar= (Button)view.findViewById(R.id.btnAgregarCultivo);
        Btnagregar.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.i("FragmentCrearCultivo", "onClick");
        switch (view.getId()) {
            case R.id.btnAgregarCultivo:
                if(nombre.getText().toString().trim().length() > 0 && area.getText().toString().trim().length() > 0 && plantas.getText().toString().trim().length() > 0) {

                    agregarCultivo(
                            nombre.getText().toString(),
                            area.getText().toString(),
                            plantas.getText().toString(),
                            String.valueOf(foto.getTag())
                    );
                    Toast.makeText(view.getContext(), "Cultivo Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(view.getContext(),"Por favor complete los campos", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.imageCargarCultivo:
                Intent intent=null;
                if(Build.VERSION.SDK_INT<19){
                    intent= new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }else{
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                }
                intent.setType("image/*");
                startActivityForResult(intent,request_code);
                break;

        }
    }

    public void agregarCultivo(String nombre, String area, String plantas, String foto){
        Cultivo nuevo = new Cultivo(nombre, area, plantas, foto);
        try{
            cultivoDao = getHelper().getCultivoDao();
            cultivoDao.create(nuevo);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void limpiarCampos(){
        nombre.getText().clear();
        area.getText().clear();
        plantas.getText().clear();
        //foto.setImageResource(R.android.dr);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode == request_code){
            foto.setImageURI(data.getData());
            foto.setTag(data.getData());
        }
    }
}