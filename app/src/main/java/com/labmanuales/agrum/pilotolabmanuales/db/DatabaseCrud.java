package com.labmanuales.agrum.pilotolabmanuales.db;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by diego on 4/09/16.
 */
public class DatabaseCrud {


    private DatabaseHelper databaseHelper = null;
    private Dao<Usuario, Integer> usuarioDao;
    private Dao<Cultivo,Integer> cultivoDao;
    private Dao<Labor,Integer> laborDao;

    Context thiscontext;

    public DatabaseCrud(Context context) {
        this.thiscontext = context;
        try {
            usuarioDao = getHelper().getUsuarioDao();
            cultivoDao = getHelper().getCultivoDao();
            laborDao = getHelper().getLaborDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private DatabaseHelper getHelper() {
        Log.i("DatabaseCrud", "DatabaseHelper");
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(thiscontext, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void releaseHelper() {
        Log.i("DatabaseCrud", "releaseHelper");

        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }


    //<editor-fold desc="CRUD Usuario">
    public int crearUsuario(Usuario nuevo){
        try {
            return usuarioDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarUsuario(Usuario actualizar)
    {
        try {
            return usuarioDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarUsuario(Usuario eliminar)
    {
        try {
            return usuarioDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Usuario> obtenerUsuarios()
    {
        try {
            return usuarioDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String composeJSONfromSQLiteUsuario(){
        List<Usuario> listaNoSincronizados = null;
        try {
            listaNoSincronizados = usuarioDao.queryBuilder().where().eq("updateState","No").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Gson gson = new GsonBuilder().create();
        return gson.toJson(listaNoSincronizados);
    }

    public int dbSyncCountUsuario() {
        int count = 0;
        try {
            return (int) usuarioDao.queryBuilder().where().eq("updateState","No").countOf();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void updateSyncStatusUsuario(String id, String status) {
        try {
            UpdateBuilder<Usuario,Integer> actualizar = usuarioDao.updateBuilder();
            actualizar.where().eq("usuario_id",id);
            actualizar.updateColumnValue("updateState",status);
            actualizar.update();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //</editor-fold>

    //<editor-fold desc="CRUD Cultivo">
    public int crearCultivo(Cultivo nuevo){
        try {
            return cultivoDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarCultivo(Cultivo actualizar)
    {
        try {
            return cultivoDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarCultivo(Cultivo eliminar)
    {
        try {
            return cultivoDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Cultivo> obtenerCutivos()
    {
        try {
            return cultivoDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //</editor-fold>

    //<editor-fold desc="CRUD Labor">
    public int crearLabor(Labor nuevo){
        try {
            return laborDao.create(nuevo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarLabor(Labor actualizar)
    {
        try {
            return laborDao.update(actualizar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarLabor(Labor eliminar)
    {
        try {
            return laborDao.delete(eliminar);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Labor> obtenerLabores()
    {
        try {
            return laborDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //</editor-fold>




}
