/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.Statement; 
import javax.swing.JOptionPane; 
/**
 *
 * @author cepardov
 */
public class Conectar {
    Connection conexion; 
    Statement sentencia; 

 public void PrepararBaseDatos() { 
        try{ 
            String controlador="sun.jdbc.odbc.JdbcOdbcDriver"; 
            Class.forName (controlador).newInstance(); 
        } 
        catch (Exception e) { 
            JOptionPane.showMessageDialog(null, "Error al cargar el Controlador"); 
        } 
        try { 
            String DSN="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+"BDSis.mdb"; 
            String user=""; 
            String password=""; 
            conexion=DriverManager.getConnection(DSN,user,password); 
        } 
        catch (Exception e) { 
            JOptionPane.showMessageDialog(null,"Error al realizar la conexion "+e); 
        } 
        try { 
            sentencia=conexion.createStatement( 
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_READ_ONLY); 
        } 
        catch (Exception e) { 
            JOptionPane.showMessageDialog(null,"Error al crear el objeto sentencia "+e); 
        } 
     } 
}
