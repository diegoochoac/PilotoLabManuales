package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseCrud;
import com.labmanuales.agrum.pilotolabmanuales.db.Labor;

/**
 * Created by diego on 3/09/16.
 */
public class MenuFragmentCrearLabor extends Fragment implements OnClickListener {

    private EditText nombre;
    private Button Btnagregar;
    private DatabaseCrud database;

    public MenuFragmentCrearLabor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.menu_fragment_crear_labor, container, false);
        database = new DatabaseCrud(container.getContext());
        inicializarComponentes(rootview);
        return rootview;
    }

    @Override
    public void onPause() {
        Log.i("FragmentCrearCultivo", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("FragmentCrearCultivo", "onStop");
        database.releaseHelper();
        super.onStop();
    }

    private void inicializarComponentes(View view) {
        nombre = (EditText)view.findViewById(R.id.cmpNombreLabor);
        Btnagregar= (Button)view.findViewById(R.id.btnAgregarLabor);
        Btnagregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAgregarLabor:
                if(nombre.getText().toString().trim().length() > 0){
                    agregarLabor(
                            nombre.getText().toString()
                    );
                    Toast.makeText(view.getContext(), "Labor Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(view.getContext(),"Por favor complete los campos", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void agregarLabor(String nombre) {
        Labor nuevo = new Labor(nombre);
        database.crearLabor(nuevo);
    }

    public void limpiarCampos(){
        nombre.getText().clear();
    }

}
