package com.labmanuales.agrum.pilotolabmanuales.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by diego on 3/09/16.
 */
public class Cultivo implements Serializable {

    public static final String ID = "cultivo_id";
    public static final String NOMBRE = "nombre_cultivo";

    @DatabaseField(generatedId = true, columnName = ID)
    private int cultivoId;

    @DatabaseField (columnName = NOMBRE)
    private String cultivoNombre;

    @DatabaseField
    private String cultivoArea;

    @DatabaseField
    private String cultivoPlantas;

    @DatabaseField
    private String cultivoFoto;


    public Cultivo() {
    }

    public Cultivo(String cultivoNombre, String cultivoArea, String cultivoPlantas, String cultivoFoto) {
        this.cultivoNombre = cultivoNombre;
        this.cultivoArea = cultivoArea;
        this.cultivoPlantas = cultivoPlantas;
        this.cultivoFoto = cultivoFoto;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getCultivoId() {
        return cultivoId;
    }

    public void setCultivoId(int cultivoId) {
        this.cultivoId = cultivoId;
    }

    public String getCultivoNombre() {
        return cultivoNombre;
    }

    public void setCultivoNombre(String cultivoNombre) {
        this.cultivoNombre = cultivoNombre;
    }

    public String getCultivoArea() {
        return cultivoArea;
    }

    public void setCultivoArea(String cultivoArea) {
        this.cultivoArea = cultivoArea;
    }

    public String getCultivoPlantas() {
        return cultivoPlantas;
    }

    public void setCultivoPlantas(String cultivoPlantas) {
        cultivoPlantas = cultivoPlantas;
    }

    public String getCultivoFoto() {
        return cultivoFoto;
    }

    public void setCultivoFoto(String cultivoFoto) {
        this.cultivoFoto = cultivoFoto;
    }

    //</editor-fold>
}
