/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

/**
 *
 * @author cepardov
 */
public class DetalleAplicacion {
    SistemaOperativo SO=new SistemaOperativo();
    
    private String Nombre="Nombre Aplicacion";
    private String Stable="1";
    private String Cambios="0";
    private String RevisionGoogle=" r16 ";
    private String Estado="RC1";
    private String NombreDesarrollador="Cristian Pardo Velásquez - E-Mail: cepardov@gmail.com";
    private String Año="2013";
    private String Licencia="Pruebas Beta 2";

    public SistemaOperativo getSO() {
        return SO;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getStable() {
        return Stable;
    }

    public String getCambios() {
        return Cambios;
    }

    public String getRevisionGoogle() {
        return RevisionGoogle;
    }

    public String getEstado() {
        return Estado;
    }

    public String getNombreDesarrollador() {
        return NombreDesarrollador;
    }

    public String getAño() {
        return Año;
    }

    public String getLicencia() {
        return Licencia;
    }
    
    public String version(){
        return getStable()+"."+getCambios()+" - "+getRevisionGoogle()+" "+getEstado();
    }
    public String copyright(){
        return "©"+getAño()+" "+getNombreDesarrollador();
    }
}

