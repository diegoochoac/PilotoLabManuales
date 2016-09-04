package com.labmanuales.agrum.pilotolabmanuales.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.labmanuales.agrum.pilotolabmanuales.R;

import java.sql.SQLException;

/**
 * Created by diego on 3/09/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "laboresmanuales.db";
    private static final int DATABASE_VERSION = 1;

    //Objeto Dao qye se utiliza para acceder a la tabla usuario
    private Dao<Cultivo,Integer> cultivoDao;
    private Dao<Usuario, Integer> usuarioDao;


    public DatabaseHelper(Context context) {
        //R.raw.ormlite_config
        super(context, DATABASE_NAME, null, DATABASE_VERSION,R.raw.ormlite_config);
    }

    /**
     * Metodo invocado cuando la base de datos es creada. Usualmente se hacen llamadas a los metodos
     * createTable para crear las tablas que almacenaran los datos
     * @param db
     * @param source
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onCreate()");
            TableUtils.createTable(source,Cultivo.class);
            TableUtils.createTable(source,Usuario.class);

        }catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getSimpleName(),"Imposible crear base de datos",ex);
            throw  new RuntimeException(ex);
        }
    }

    /**
     * Este metodo es invocado cuando la aplicacion es actualizada y tiene un numero de version
     * superio. permite el ajuste a los mtados para alinearse con la nueva version
     * @param db
     * @param source
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion, int newVersion) {
        try {
            Log.i(DatabaseHelper.class.getSimpleName(), "onUpgrade()");
            TableUtils.dropTable(source,Cultivo.class, true);
            TableUtils.dropTable(source,Usuario.class, true);
            onCreate(db, source);
        } catch (SQLException ex) {
            Log.e(DatabaseHelper.class.getSimpleName(),"Imposible actualizar base de datos",ex);
            throw  new RuntimeException(ex);
        }

    }

    //<editor-fold desc="Metodos Get">

    public Dao<Cultivo, Integer> getCultivoDao()  throws SQLException{
        if(cultivoDao==null){
            cultivoDao = getDao(Cultivo.class);
        }
        return cultivoDao;
    }

    public Dao<Usuario, Integer> getUsuarioDao() throws  SQLException{
        if(usuarioDao==null){
            usuarioDao = getDao(Usuario.class);
        }
        return usuarioDao;
    }

    //</editor-fold>



}
