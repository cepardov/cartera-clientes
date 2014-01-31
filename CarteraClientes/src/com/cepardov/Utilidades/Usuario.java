package com.cepardov.Utilidades;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author cepardov <cepardov@gmail.com>
 */

public class Usuario{
	private String nombre;
	private String apellido;
        private String usuario;

	/** Crea un nueva instancia de la clase usuario */
	public Usuario(){
        }
        public Usuario verificarUsuario(String usuario, String clave){
        Usuario u=null;
        Connection cn=null;
        PreparedStatement pr=null;
        ResultSet rs=null;
        try{
            Conect c=new Conect();
            cn=c.getConnection();
            String sql="SELECT * FROM usuario WHERE usuario=? AND clave=?";
            pr=cn.prepareStatement(sql);
            pr.setString(1, usuario);
            pr.setString(2, clave);
            rs=pr.executeQuery();
            while(rs.next()){
                u=new Usuario();
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setUsuario(rs.getString("usuario"));
                break;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            u=null;
        }finally{
            try{
                rs.close();
                pr.close();
                rs.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return u;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

        
   
}