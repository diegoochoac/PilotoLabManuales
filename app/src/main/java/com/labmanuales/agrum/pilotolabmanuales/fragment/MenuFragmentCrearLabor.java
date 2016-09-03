package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.labmanuales.agrum.pilotolabmanuales.R;
import com.labmanuales.agrum.pilotolabmanuales.db.DatabaseHelper;

/**
 * Created by diego on 3/09/16.
 */
public class MenuFragmentCrearLabor extends Fragment implements OnClickListener {

    private DatabaseHelper databaseHelper = null;

    public MenuFragmentCrearLabor() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.menu_fragment_crear_labor, container, false);
        inicializarComponentes(rootview);
        return rootview;
    }

    private void inicializarComponentes(View view) {
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


    @Override
    public void onClick(View view) {

    }
}
