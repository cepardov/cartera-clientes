/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

/**
 *
 * @author cepardov
 */
public class SistemaOperativo {
    String SO=null;
    String Arch=null;
    String Version=null;
    String Usuario=null;
    String Directorio=null;
    String JavaVer=null;
    String userDir=null;
    String sepDir=null;
    
    public void Sistema(){
        //System.get;
        SO=System.getProperties().getProperty("os.name");
        Arch=System.getProperties().getProperty("os.arch");
        Version=System.getProperties().getProperty("os.version");
        Usuario=System.getProperties().getProperty("user.name");
        Directorio=System.getProperties().getProperty("user.dir");
        JavaVer=System.getProperties().getProperty("java.version");
        userDir=System.getProperties().getProperty("user.home");
        sepDir=System.getProperties().getProperty("file.separator");
    }

    public String getSO() {
        return SO;
    }

    public String getArch() {
        return Arch;
    }

    public String getVersion() {
        return Version;
    }

    public String getUsuario() {
        return Usuario;
    }

    public String getDirectorio() {
        return Directorio;
    }

    public String getJavaVer() {
        return JavaVer;
    }

    public String getUserDir() {
        return userDir;
    }

    public String getSepDir() {
        return sepDir;
    }
    
    
    
}
