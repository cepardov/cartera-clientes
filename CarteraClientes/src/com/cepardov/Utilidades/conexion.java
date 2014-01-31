package com.cepardov.Utilidades;

/*
 * ConeccionBD.java
 *
 * Created on 4 de diciembre de 2006, 12:04 PM
 */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
import javax.swing.*;
/**
 *
 * @author Sistemas Corporativos I
 */

public class conexion
{
    
    //public final String CONTROLADOR_JDBC = "org.postgresql.Driver"; //Para la conexion con postgres
    public final String CONTROLADOR_JDBC = "com.mysql.jdbc.Driver"; //Para la conexion con postgres
    
    /**
     * Vector doble que recopila la informacion de la carpeta local del fichero de configure.xls o cualquier otro nombre que se le atribuya, <br><br>
     * en la cual se guarda las configuraciones correspondientes para que se pueda conectar a una base de datos.<hr>
     * Tener mucho cuidado a la hora de manipular los datos residentes en el archivo externo porque de otro modo podria provocar el mal funcionamiento del programa y tambien de la conexion.
     */
    
  

//    private static String usuario=Main.Configure[0][1];
//    private static String contra=Main.Configure[0][2];
//    private static String importancia="1";
//    private String baseActiva=Configure[0][3];

    private static String computadoraResidente = "localhost"; //computadora residente
    private static String usuario="root";
    private static String contra= "";
    private String baseActiva="BDSis";
    private static String puerto = ""; //
    private static String importancia="1";
    /** La conexion con la base de datos */
    private Connection conexion = null;

    Statement stmt;
    ResultSet rs;
    String codigo;

    public void setUsuario(String usuario)
    {
        this.usuario=usuario;
    }
    public String getUsuario()
    {
        return usuario;
    }
    public String getContra()
    {
        return contra;
    }
    public void setContra(String contra)
    {
        this.contra = contra;
    }
    public void setImportancia(String importancia) {
        this.importancia = importancia;
    }
    public String getImportancia() {
        return importancia;
    }


    /** Creates a new instance of ConeccionBD */
    public conexion() {
    }

    public boolean estaConectado()
    {
        if (conexion != null)
            return true;
        try
        {
           // Se registra el Driver de MySQL
           // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Class.forName( CONTROLADOR_JDBC );
            // Se obtiene una conexion con la base de datos. Hay que
            // cambiar el usuario "usuario" y la clave "" por las
            // adecuadas a la base de datos que estemos usando.
            //conexion  = DriverManager.getConnection("jdbc:postgresql://"+computadoraResidente+":"+puerto+"/"+baseActiva, usuario, contra);


            conexion  = DriverManager.getConnection("jdbc:mysql://"+computadoraResidente+"/"+baseActiva, usuario, contra);
            //conexion  = DriverManager.getConnection("jdbc:mysql://localhost/bdcyber","root","123");
            //conexion = DriverManager.getConnection(
            // "jdbc:mysql://localhost/supsys","root","");
            // "jdbc:mysql://localhost/rrhh","root","");
            System.out.println("pase conexion..................");
        } catch (Exception e)
        {
            System.out.println("error: "+e);
            conexion=null;
            return false;
        }
        return true;
    }

    public boolean borrarRegistro(String tabla, String condicion){
        int resultado;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta.
            JOptionPane optionPane=new JOptionPane();
	    Object[] opciones={"Si","No"};
	    //Dialogo modal SI_NO
	    int ret=optionPane.showOptionDialog(null,"Desea Borrar? ","Pregunta",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,opciones,opciones[0]);
	    //Si la opcion escogida es Si
	    if(ret==JOptionPane.YES_OPTION)
		resultado = s.executeUpdate("delete from "+tabla+" where "+condicion);
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean borrarRegistroSinPreguntar(String tabla, String condicion){
        int resultado;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            resultado = s.executeUpdate("delete from "+tabla+" where "+condicion);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean executeQuery(String sql)
    {
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            s.executeUpdate(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public void ExecuteQuery(String sql)
    {
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            s.executeQuery(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public boolean abmRegistro(String abm_name, String valores, String modalidad){
        int resultado;
        try
        {
            // Se crea un Statement, para realizar la consulta
           Statement s = conexion.createStatement();
           System.out.println("select "+abm_name+"('"+modalidad+"',"+valores+")");
            // Se realiza la consulta.
     	   s.executeQuery("select "+abm_name+"('"+modalidad+"',"+valores+")");
        } catch (SQLException e)
        {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error\n"+e.getMessage() , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean insertarRegistro(String tabla, String campos, String valores){
        int resultado;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta.
     	   resultado = s.executeUpdate("insert into "+tabla+" ("+campos+") values ("+valores+")");
        } catch (Exception e)
        {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error\n"+e.getMessage() , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println("insert into "+tabla+" ("+campos+") values ("+valores+")");
            return false;
        }
        return true;
    }

     public boolean actualizarRegistro(String tabla, String campos, String criterio){
        int resultado;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();
            // Se realiza la consulta.
            resultado = s.executeUpdate("update "+tabla+" set "+campos+" where " +criterio);
        } catch (Exception e)
        {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error\n"+e.getMessage() , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    public ResultSet dameLista(String campos, String tabla, String condicion)
    {
        ResultSet rs = null;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            rs = s.executeQuery("select "+campos+" from "+tabla+" "+condicion);
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println("select "+campos+" from "+tabla+" "+condicion);
        }
        return rs;
    }

    public ResultSet dameLista(String sql)
    {
        ResultSet rs = null;
        try
        {
            // Se crea un Statement, para realizar la consulta
            Statement s = conexion.createStatement();

            // Se realiza la consulta. Los resultados se guardan en el
            // ResultSet rs
            System.out.print(sql);
            rs = s.executeQuery(sql);
        } catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion",
            JOptionPane.INFORMATION_MESSAGE);
            System.out.println(sql);
        }
        return rs;
    }
    /**
     * Carga un conjunto de combox
     * @param sql
     * @param combo
     */
    public void cargarCombo(String sql, JComboBox[]combo)
    {
        for (int i = 0; i < combo.length; i++)
            cargarCombo(sql, combo[i]);
    }
    /**
     * Carga un combox a partir de la consulta sql
     * @param sql
     * @param combo
     */
    public void cargarCombo(String sql, JComboBox combo){
        ResultSet rs = null;
        int contar=0;
        combo.removeAllItems();
        try{
            Statement s = conexion.createStatement();
            rs = s.executeQuery(sql);
            System.out.println(sql);
            while (rs.next())
            {
                String columnas[] = new String[2];
		columnas[0] = rs.getString(1);
		columnas[1] = rs.getString(2);
		combo.addItem (columnas);
                contar++;
            }
        } catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocurrio Un error: "+e.toString() , "Atencion1",
            JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (contar>0)
        {
            combo.setRenderer (new DefaultListCellRenderer()
            {
		  public java.awt.Component getListCellRendererComponent (
		    JList l,Object o,int i,boolean s, boolean f)
		  {
                     try{
		     return new JLabel (((String[])o)[1]);
                     }catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
                        JOptionPane.INFORMATION_MESSAGE);
                        return null;
                    }
		  }
		});
        }else
        //variante modificada...
        {
            //en caso de error probar lo siguiente
                //com_horarios.removeAllItems(); puede que no lleve porque siempre se le pasa datos vacios
                //com_horarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"",""}));
            //fin caso de error
            
            //prototipo de error 1
                combo.addItem(new String[]{"",""});
                combo.setRenderer (new DefaultListCellRenderer()
                {
                      public java.awt.Component getListCellRendererComponent (
                        JList l,Object o,int i,boolean s, boolean f)
                      {
                         try{
                         return new JLabel (((String[])o)[1]);
                         }catch(Exception e){
                            JOptionPane.showMessageDialog(null, "Ocurrio Un error" , "Atencion1",
                            JOptionPane.INFORMATION_MESSAGE);
                            return null;
                        }
                      }
                    });
            //prototipo de error 1
        }
        //fin modificacion
        return;
    }
    /**
     * Verifica si existe un usuario determinado en la base de datos
     * @param usu
     * @param clave
     * @return
     */
    public boolean existeUsuarioClave(String usu, String clave){
        ResultSet rs = null;
        try{
            System.out.println("select count(1) from usuarios where usuario = '"+usu+"' and contrasenha = md5('"+clave+"')");
            Statement s = conexion.createStatement();
            rs = s.executeQuery("select count(1) from usuarios where usuario = '"+usu+"' and contrasenha = md5('"+clave+"')");
               
             //rs = s.executeQuery("select count(1) from usuario where usuario = 'usu' and clave = 'clave'");

            rs.next();
            int cantidad = rs.getInt(1);

            if (cantidad == 0)
               return false;
            else
               return true;
        } catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }
    
    /** Cierra la conexion con la base de datos */
    public void cierraConexion()
    {
        try
        {
            conexion.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Cuenta la cantidad de columnas dentro de una consulta
     * @param sql
     * @return
     */
    public int countColumn(String sql)
    {
        ResultSet rs = dameLista(sql);
        int c=0;
        try
        {
            while (rs.next())
            {
                for (int i = 0; i < 100; i++) {
                    rs.getString(i+1);
                    c+=1;
                }
            }
        } catch (Exception e) {
        }
        return c;
    }
    /**
     * Cuenta la cantidad de filas que tiene una consulta sql
     * @param sql
     * @return
     */
    public int countFila(String sql)
    {
        ResultSet rs = dameLista(sql);
        int c=0;
        try
        {
            while (rs.next())
            {
              c+=1;
            }
        } catch (Exception e) {
        }
        return c;
    }
}