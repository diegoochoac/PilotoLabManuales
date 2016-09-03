package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.labmanuales.agrum.pilotolabmanuales.R;

/**
 * Created by diego on 1/09/16.
 */
public class ActividadesFragment extends Fragment implements OnClickListener {


    public ActividadesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.actividades_fragment, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(View view) {
    }

    @Override
    public void onClick(View view) {

    }
}
