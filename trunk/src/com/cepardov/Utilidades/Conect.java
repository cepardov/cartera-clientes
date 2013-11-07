package com.cepardov.Utilidades;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conect {
   private final String basedatos = "BDSis";
   private final String usuario = "root";
   private final String contraseña = "";
   private final String url = "jdbc:mysql://localhost/"+basedatos;
   Connection conn = null;

   public Conect() {
      try{
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(url,usuario,contraseña);
         if (conn!=null){
             System.out.println("Usted se ha conectado a la base de datos "+basedatos+".");
             System.out.println("Como usuario: "+usuario);
         }
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null,"La aplicación no tiene conexión con base de datos.\nContacte con el administrador del sistema.\n\n"+ e, "Error en Inicio de sesión", JOptionPane.ERROR_MESSAGE);
         System.out.println("Error: "+e);
      }catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null, e);
         System.out.println(e);
      }
   }
   public Connection getConnection(){
      return conn;
   }

   public void desconectar(){
      conn = null;
      System.out.println("La conexion a la  base de datos "+basedatos+" a terminado");
   }
}
