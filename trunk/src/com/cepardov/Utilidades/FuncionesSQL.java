/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cepardov
 */
public class FuncionesSQL {
    Conect conn;
    
    public FuncionesSQL(){
        conn = new Conect();
    }
    
    public void addMarca(String nombre){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Marca (nombre) values(?)");
            pstm.setString(1, nombre);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Operacion realizada...");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void addCita(String nombre, String observacion, String fechahora){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Cita (nombre,observacion,fecha) values(?,?,?)");
            pstm.setString(1, nombre);
            pstm.setString(2, observacion);
            pstm.setString(3, fechahora);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Operacion realizada...");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public Object [][] getMarca(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Marca");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][2];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT idMarca,nombre FROM Marca ORDER BY idMarca");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estId = res.getString("idMarca");
                String estNombre = res.getString("nombre");
                data[increment][0] = estId;
                data[increment][1] = estNombre;
                increment++;
            }
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }
}
