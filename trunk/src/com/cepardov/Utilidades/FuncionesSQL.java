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
    
    public int getUsuario(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Usuario");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        return posid;
    }
    
    public void updateClave(String clave, String usuario){
       try {                
            PreparedStatement pstm = conn.getConnection().prepareStatement("update Usuario "+
            "set clave=?"+
            "where usuario=?");            
            pstm.setString(1, clave);                 
            pstm.setString(2, usuario);
            pstm.execute();
            pstm.close();            
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
      }
   }
    
    public void updateClienteEstado(String rutCliente, String estado){
       try {                
            PreparedStatement pstm = conn.getConnection().prepareStatement("update Cliente "+
            "set estado=?"+
            "where rutCliente=?");            
            pstm.setString(1, estado);                 
            pstm.setString(2, rutCliente);
            pstm.execute();
            pstm.close();            
       }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se);
      }
   }
   
    public void updateFinanciamiento(int idFinanciamiento, String nombre){
       try {                
            PreparedStatement pstm = conn.getConnection().prepareStatement("update Financiamiento "+
            "set nombre=? where idFinanciamiento=?");                             
            pstm.setString(1, nombre);
            pstm.setInt(2, idFinanciamiento);
            pstm.execute();
            pstm.close();            
       }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
      }
   }
    
    public void addUsuario(String rut, String nombre, String apellido, String usuario, String clave, String pregunta, String respuesta){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Usuario (rut,nombre,apellido,usuario,clave,pregunta,respuesta) values(?,?,?,?,?,?,?)");
            pstm.setString(1, rut);
            pstm.setString(2, nombre);
            pstm.setString(3, apellido);
            pstm.setString(4, usuario);
            pstm.setString(5, clave);
            pstm.setString(6, pregunta);
            pstm.setString(7, respuesta);
            pstm.execute();
            pstm.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Revise los datos ingresados no se ha podido guardar la solicitud.\nContacte con Administrador de la aplicación.\n"+e);
        }
    }
    
    public void addHistoria(String rutCliente, String fecha, String observacion){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into historiacliente (rutCliente,fecha,observacion) values(?,?,?)");
            pstm.setString(1, rutCliente);
            pstm.setString(2, fecha);
            pstm.setString(3, observacion);
            pstm.execute();
            pstm.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Revise los datos ingresados no se ha podido guardar la solicitud.\nContacte con Administrador de la aplicación.\n"+e);
        }
    }
    
    public void addEjecutivo(int idFinanciamiento, String nombre, String paterno, String telefono, String email){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into ejecutivo (idFinanciamiento,nombre,paterno,telefono,email) values(?,?,?,?,?)");
            pstm.setInt(1, idFinanciamiento);
            pstm.setString(2, nombre);
            pstm.setString(3, paterno);
            pstm.setString(4, telefono);
            pstm.setString(5, email);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Se ha registrado exitosamente el ejecutivo", "Ingreso Ejecutivos de Credito", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Revise los datos ingresados no se ha podido guardar la solicitud.\nContacte con Administrador de la aplicación.\n"+e);
        }
    }
    
    public void addFinanciamiento(String nombre){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Financiamiento (nombre) values(?)");
            pstm.setString(1, nombre);
            pstm.execute();
            pstm.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Revise los datos ingresados no se ha podido guardar la solicitud.\nContacte con Administrador de la aplicación.\n"+e);
        }
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
    
    public void addAgendamiento(String rutCliente, String nombre, String paterno, String materno, String tipo, String observacion, String fechahora){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Agenda (rutCliente,nombre,paterno,materno,tipo,observacion,fecha) values(?,?,?,?,?,?,?)");
            pstm.setString(1, rutCliente);
            pstm.setString(2, nombre);
            pstm.setString(3, paterno);
            pstm.setString(4, materno);
            pstm.setString(5, tipo);
            pstm.setString(6, observacion);
            pstm.setString(7, fechahora);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Operacion realizada...");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void addCliente(String rutCliente, String nombre, String paterno, String materno, String Estado, String direccion, String ciudad, String telefono, String email, String fechaIngreso){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Cliente (rutCliente,nombre,paterno,materno,estado,direccion, ciudad,telefono,email,fechaIngreso) values(?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, rutCliente);
            pstm.setString(2, nombre);
            pstm.setString(3, paterno);
            pstm.setString(4, materno);
            pstm.setString(5, Estado);
            pstm.setString(6, direccion);
            pstm.setString(7, ciudad);
            pstm.setString(8, telefono);
            pstm.setString(9, email);
            pstm.setString(10, fechaIngreso);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Operacion realizada...");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se ha podido procesar su solicitud por alguna de estas razónes:\n\t- El cliente ya ha sido ingresado(a).\n\t- Si ha modificado la ficha del cliente presione boton \"Corregir\".", "¡ups! Algo salio mal durante el almacenado de datos...", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void addCotizacion(String rutCliente, int idMarca, int idModelo, int idFinanciamiento, int idEjecutivo, String tipo, String neto, String descuento, String iva, String total, String observaciones, String fecha){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("insert into Cotizacion (rutCliente,idMarca,idModelo,idFinanciamiento,idEjecutivo,tipo,neto,descuento,iva,total,observaciones,fecha) values(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, rutCliente);
            pstm.setInt(2, idMarca);
            pstm.setInt(3, idModelo);
            pstm.setInt(4, idFinanciamiento);
            pstm.setInt(5, idEjecutivo);
            pstm.setString(6, tipo);
            pstm.setString(7, neto);
            pstm.setString(8, descuento);
            pstm.setString(9, iva);
            pstm.setString(10, total);
            pstm.setString(11, observaciones);
            pstm.setString(12, fecha);
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Operacion realizada...");
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "No se ha podido procesar su solicitud por alguna de estas razónes:\n\t- El cliente ya ha sido ingresado(a).\n\t- Si ha modificado la ficha del cliente presione boton \"Corregir\".", "¡ups! Algo salio mal durante el almacenado de datos...", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void updateCliente(String rutCliente, String nombre, String paterno, String materno, String Estado, String direccion, String ciudad, String telefono, String email){
       try {            
            PreparedStatement pstm = conn.getConnection().prepareStatement("update Cliente " +
            "set rutCliente = ? ," +
            "nombre = ? ," +
            "paterno = ? ," +
            "materno = ? ," +
            "estado = ? ," + 
            "direccion = ? ," +
            "ciudad = ? ," +
            "telefono = ? ," + 
            "email = ? " +
            "where rutCliente = ?");            
            pstm.setString(1, rutCliente);
            pstm.setString(2, nombre);
            pstm.setString(3, paterno);
            pstm.setString(4, materno);
            pstm.setString(5, Estado);
            pstm.setString(6, direccion);
            pstm.setString(7, ciudad);
            pstm.setString(8, telefono);
            pstm.setString(9, email);
            pstm.setString(10, rutCliente);
            pstm.execute();
            pstm.close();  
            JOptionPane.showMessageDialog(null, "Trabajo realizado exitosamente.");
         }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
      }
   }
    
   public void updateCotizacion(String idCotizacion, int idMarca, int idModelo, int idFinanciamiento, int idEjecutivo, String tipo, String neto, String descuento, String iva, String total, String observaciones, String rutCliente){
       try {            
            PreparedStatement pstm = conn.getConnection().prepareStatement("update Cotizacion " +
            "set idMarca = ? ," +
            "idModelo = ? ," +
            "idFinanciamiento = ? ," +
            "idEjecutivo = ? ," +
            "tipo = ? ," + 
            "neto = ? ," +
            "descuento = ? ," +
            "iva = ? ," + 
            "total = ? ," +
            "observaciones = ? " +
            "where idCotizacion = ?");            
            pstm.setInt(1, idMarca);
            pstm.setInt(2, idModelo);
            pstm.setInt(3, idFinanciamiento);
            pstm.setInt(4, idEjecutivo);
            pstm.setString(5, tipo);
            pstm.setString(6, neto);
            pstm.setString(7, descuento);
            pstm.setString(8, iva);
            pstm.setString(9, total);
            pstm.setString(10, observaciones);
            pstm.setString(11, idCotizacion);
            pstm.execute();
            pstm.close();  
            JOptionPane.showMessageDialog(null, "Trabajo realizado exitosamente.");
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
    
    public Object [][] getHistorialCliente(String rutCliente){
        int posid = 0;
        System.out.println("ejecutando comando para "+rutCliente);
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM historiacliente");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        Object[][] data = new String[posid][2];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT fecha,observacion FROM historiacliente WHERE rutCliente='"+rutCliente+"' ORDER BY fecha");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estFecha = res.getString("fecha");
                String estObservacion = res.getString("observacion");
                data[increment][0] = estFecha;
                data[increment][1] = estObservacion;
                increment++;
            }
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        return data;
    }
    
    public Object [][] getCotizacionCliente(String rutCliente){
        int posid = 0;
        System.out.println("ejecutando comando para "+rutCliente);
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Cotizacion");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        Object[][] data = new String[posid][12];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT idCotizacion, idMarca,idModelo,idFinanciamiento,idEjecutivo,tipo,neto,descuento,iva,total,observaciones,fecha FROM Cotizacion WHERE rutCliente='"+rutCliente+"'");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estId = res.getString("idCotizacion");
                String estFecha = res.getString("fecha");
                String estMarca = res.getString("idMarca");
                String estModelo = res.getString("idModelo");
                String estFinanciamiento = res.getString("idFinanciamiento");
                String estEjecutivo = res.getString("idEjecutivo");
                String estTipo = res.getString("tipo");
                String estNeto = res.getString("neto");
                String estDesc = res.getString("descuento");
                String estIva = res.getString("iva");
                String estTotal = res.getString("total");
                String estObservacion = res.getString("observaciones");
                data[increment][0] = estId;
                data[increment][1] = estFecha;
                data[increment][2] = estMarca;
                data[increment][3] = estModelo;
                data[increment][4] = estFinanciamiento;
                data[increment][5] = estEjecutivo;
                data[increment][6] = estTipo;
                data[increment][7] = estNeto;
                data[increment][8] = estDesc;
                data[increment][9] = estIva;
                data[increment][10] = estTotal;
                data[increment][11] = estObservacion;
                increment++;
            }
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        return data;
    }
    
    public Object [][] getNotificaciones(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Cita");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][3];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT nombre,observacion,fecha FROM Cita ORDER BY fecha");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estNombre = res.getString("nombre");
                String estObservacion = res.getString("observacion");
                String estFecha = res.getString("fecha");
                data[increment][0] = estNombre;
                data[increment][1] = estObservacion;
                data[increment][2] = estFecha;
                increment++;
            }
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }
    
    public Object [][] getFinanciamiento(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Financiamiento");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][2];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT idFinanciamiento,nombre FROM Financiamiento ORDER BY nombre");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estRut = res.getString("idFinanciamiento");
                String estNombre = res.getString("nombre");
                data[increment][0] = estRut;
                data[increment][1] = estNombre;
                increment++;
            }
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        return data;
    }
 
    public Object [][] getEjecutivo(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM ejecutivo");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][2];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT idEjecutivo,idFinanciamiento,nombre,paterno,telefono,email FROM ejecutivo ORDER BY idEjecutivo");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                int estIdejecutivo = res.getInt("idEjecutivo");
                int estIdfinanciamiento = res.getInt("idFinanciamiento");
                String estNombre = res.getString("nombre");
                String estApellido = res.getString("paterno");
                String estTelefono = res.getString("telefono");
                String estEmail = res.getString("email");
                data[increment][0] = estIdejecutivo;
                data[increment][1] = estIdfinanciamiento;
                data[increment][2] = estNombre;
                data[increment][3] = estApellido;
                data[increment][4] = estTelefono;
                data[increment][5] = estEmail;
                increment++;
            }
            res.close();
            }catch(SQLException se){
                JOptionPane.showMessageDialog(null, se);
        }
        return data;
    }
    
    public Object [][] BuscarCliente(String nombre, String apellido){
        int posid = 0;
        try{
            System.out.println("Funcion SQL");
            System.out.println("Nombre: "+nombre);
            System.out.println("Apellido: "+apellido);
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Cliente");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][5];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT rutCliente,nombre,paterno,telefono,fechaingreso FROM Cliente WHERE nombre='"+nombre+"' OR paterno='"+apellido+"'");
            //pstm.setString(1, nombre);
            //pstm.setString(2, apellido);
            ResultSet res = pstm.executeQuery();
            pstm.execute();
            int increment = 0;
            while(res.next()){
                String estRut = res.getString("rutCliente");
                String estNombre = res.getString("nombre");
                String estPaterno = res.getString("paterno");
                String estTelefono = res.getString("telefono");
                String estFechaingreso = res.getString("fechaingreso");
                data[increment][0] = estRut;
                data[increment][1] = estNombre;
                data[increment][2] = estPaterno;
                data[increment][3] = estTelefono;
                data[increment][4] = estFechaingreso;
                increment++;
            }
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }
    
    public Object [][] getCita(){
        int posid = 0;
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT count(1) as total FROM Cita");
            ResultSet res = pstm.executeQuery();
            res.next();
            posid = res.getInt("total");
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        Object[][] data = new String[posid][4];
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("SELECT idCita,nombre,observacion,fecha FROM `Cita` WHERE `fecha` =  '2013-10-06 08:00:00' ORDER BY idCita");
            ResultSet res = pstm.executeQuery();
            int increment = 0;
            while(res.next()){
                String estId = res.getString("idCita");
                String estNombre = res.getString("nombre");
                String estObservacion = res.getString("observacion");
                String estFecha = res.getString("fecha");
                data[increment][0] = estId;
                data[increment][1] = estNombre;
                data[increment][2] = estObservacion;
                data[increment][3] = estFecha;
                increment++;
            }
            res.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
        }
        return data;
    }
    
    public void delNotifiacion(String dato,String fecha){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("delete from Cita where nombre='"+dato+"'and fecha='"+fecha+"'");
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void delCotizacion(String idCotizacion){
        try{
            PreparedStatement pstm = conn.getConnection().prepareStatement("DELETE FROM Cotizacion WHERE idCotizacion='"+idCotizacion+"'");
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
