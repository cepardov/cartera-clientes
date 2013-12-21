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
      }catch(SQLException se){
          JOptionPane.showMessageDialog(null,"- La aplicación no tiene conexión con base de datos.\n- Inicie el servidor de base de datos de sus sitema.\nLa aplicacion que gestiona base de datos está detenida ejecute xampp y asegure que base de datos este corriendo.\nSi el problema persiste contacte con administrador.\n\nCodigo de error:\n"+ se, "¡ups! Algo inesperado ha pasado", JOptionPane.ERROR_MESSAGE);
         System.out.println("Error: "+se);
         System.exit(0);
      }catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null, e);
         System.out.println(e);
         System.exit(0);
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
