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
public class TabActividadesFragment extends Fragment implements OnClickListener {


    public TabActividadesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.tab_fragment_actividades, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(View view) {
    }

    @Override
    public void onClick(View view) {

    }
}
