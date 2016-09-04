package com.labmanuales.agrum.pilotolabmanuales.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by diego on 4/09/16.
 */
public class Labor implements Serializable {

    public static final String ID = "labor_id";
    public static final String NOMBRE = "nombre_labor";

    @DatabaseField(generatedId = true, columnName = ID)
    private int laborId;

    @DatabaseField (columnName = NOMBRE)
    private String laborNombre;

    public Labor() {
    }

    public Labor(String laborNombre) {

        this.laborNombre = laborNombre;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getLaborId() {
        return laborId;
    }

    public void setLaborId(int laborId) {
        this.laborId = laborId;
    }

    public String getLaborNombre() {
        return laborNombre;
    }

    public void setLaborNombre(String laborNombre) {
        this.laborNombre = laborNombre;
    }
    //</editor-fold>
}
