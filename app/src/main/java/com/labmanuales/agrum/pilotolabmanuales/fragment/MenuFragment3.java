package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.view.View.OnClickListener;

import com.labmanuales.agrum.pilotolabmanuales.R;


public class MenuFragment3 extends Fragment implements OnClickListener {

    private Button BtnUsuarios, BtnTerreno;
    private ListView listview;


    Context thiscontext;

    public MenuFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment3, container, false);
        setHasOptionsMenu(true);        //habilitar el action Bar para tener botones
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }



    private void inicializarComponentes(View view) {

        listview = (ListView) view.findViewById(R.id.listViewfragment);

        BtnUsuarios = (Button)view.findViewById(R.id.btnUsuarios);
        BtnTerreno = (Button)view.findViewById(R.id.btnTerreno);
        BtnUsuarios.setOnClickListener(this);
        BtnTerreno.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View view) {
        Log.i("MenuFragment3", "onClick");

        switch (view.getId()) {
            case R.id.btnUsuarios:

                break;
            case R.id.btnTerreno:

                break;
        }
    }


}