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
import android.widget.RadioButton;
import android.widget.Toast;

import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseCrud;
import com.labmanuales.agrum.pilotolabmanuales.db.Usuario;


public class MenuFragmentCrearUsuario extends Fragment implements OnClickListener {


    private EditText nombre, telefono;
    private ImageView foto;
    private RadioButton interno, externo;
    private String tipoUsuario = "";
    private Button Btnagregar;
    private int request_code = 1;
    private DatabaseCrud database;

    public MenuFragmentCrearUsuario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.menu_fragment_crear_usuario, container, false);
        database = new DatabaseCrud(container.getContext());
        inicializarComponentes(rootview);
        return rootview;
    }

    @Override
    public void onPause() {
        Log.i("FragmentCrearUsuario", "onPause");
        database.releaseHelper();
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("FragmentCrearUsuario", "onStop");
        database.releaseHelper();
        super.onStop();
    }

    private void inicializarComponentes(final View view) {
        nombre = (EditText)view.findViewById(R.id.cmpNombreUsuario);
        telefono = (EditText)view.findViewById(R.id.cmpTelefonoUsuario);
        interno = (RadioButton)view.findViewById(R.id.radioButtonUsuarioInterno);
        interno.setOnClickListener(this);
        externo = (RadioButton)view.findViewById(R.id.radioButtonUsuarioExterno);
        externo.setOnClickListener(this);
        foto = (ImageView)view.findViewById(R.id.imageViewCargarUsuario);
        foto.setOnClickListener(this);
        Btnagregar= (Button)view.findViewById(R.id.btnAgregarUsuario);
        Btnagregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.i("FragmentCrearUsuario", "onClick");

        switch (view.getId()) {
            case R.id.btnAgregarUsuario:
                if(nombre.getText().toString().trim().length() > 0 && telefono.getText().toString().trim().length() > 0 && tipoUsuario !="") {
                    agregarUsuario(
                            nombre.getText().toString(),
                            telefono.getText().toString(),
                            tipoUsuario,
                            String.valueOf(foto.getTag())
                    );
                    Toast.makeText(view.getContext(),"Usuario Agregado", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(view.getContext(),"Por favor complete los campos", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.imageViewCargarUsuario:
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

            case R.id.radioButtonUsuarioInterno:
                    tipoUsuario = "Interno";
                    externo.setChecked(false);
                break;

            case R.id.radioButtonUsuarioExterno:
                    tipoUsuario = "Externo";
                    interno.setChecked(false);
                break;

        }
    }

    public void agregarUsuario(String nombre, String telefono,String tipo, String foto){
        Usuario nuevo = new Usuario(nombre,telefono,tipo,foto,"No");
        database.crearUsuario(nuevo);
    }

    public void limpiarCampos(){
        nombre.getText().clear();
        telefono.getText().clear();
        tipoUsuario = "";
        interno.setChecked(false);
        externo.setChecked(false);
        foto.setImageResource(R.drawable.ic_user);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK && requestCode == request_code){
            foto.setImageURI(data.getData());
            foto.setTag(data.getData());
        }
    }

}