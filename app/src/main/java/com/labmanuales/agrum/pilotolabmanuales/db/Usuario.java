package com.labmanuales.agrum.pilotolabmanuales.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by diego on 3/09/16.
 */
public class Usuario implements Serializable {

    public static final String ID = "usuario_id";
    public static final String NOMBRE = "nombre_usuario";

    @DatabaseField(generatedId = true, columnName = ID)
    private int usuarioId;

    @DatabaseField (columnName = NOMBRE)
    private String usuarioNombre;

    @DatabaseField
    private String usuarioTelefono;

    @DatabaseField
    private String usuarioTipo;

    @DatabaseField
    private String usuarioFoto;

    public Usuario() {
    }

    public Usuario(String usuarioNombre, String usuarioTelefono, String usuarioTipo, String usuarioFoto) {
        this.usuarioNombre = usuarioNombre;
        this.usuarioTelefono = usuarioTelefono;
        this.usuarioTipo = usuarioTipo;
        this.usuarioFoto = usuarioFoto;
    }

    //<editor-fold desc="Metodos Get-Set">
    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(String usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }

    public String getUsuarioFoto() {
        return usuarioFoto;
    }

    public void setUsuarioFoto(String usuarioFoto) {
        this.usuarioFoto = usuarioFoto;
    }
    //</editor-fold>
}
