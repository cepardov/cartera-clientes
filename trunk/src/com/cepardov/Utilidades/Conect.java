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
          JOptionPane.showMessageDialog(null,"Lo sentimos hemos experimentado un fallo, compruebe lo siguente:\n\n\t- La licencia de usuario ha exirado: Contacte a soporte enviando un mail a cepardov@gmail.com\n\t-No es posible conectar con base de datos: inicie el servicio de base de datos de su sistema.\n\nCodigo de error:\n"+ se, "¡ups! Algo inesperado ha pasado", JOptionPane.ERROR_MESSAGE);
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
