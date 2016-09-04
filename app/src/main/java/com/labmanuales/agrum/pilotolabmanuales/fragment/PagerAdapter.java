package com.labmanuales.agrum.pilotolabmanuales.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by diego on 1/09/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabCultivosFragment cultivostab = new TabCultivosFragment();
                return cultivostab;
            case 1:
                TabActividadesFragment actividadestab = new TabActividadesFragment();
                return actividadestab;
            case 2:
                TabUsuariosFragment usuariostab = new TabUsuariosFragment();
                return usuariostab;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}