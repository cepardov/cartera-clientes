/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author PODEROSO
 */
public class ajFrame {
    private static JFileChooser fileChoose = new JFileChooser();
    /**
     * Carga un reporte donde recibe solamente el sql y el nombre del reporte sin la extension
     * @param sql
     * @param iReport_jasper 
     */
    public static void cargarReporte(String sql, String iReport_jasper)
    {
        generaReportes g = new generaReportes();
        g.generaReportes(sql, iReport_jasper);
    }
    /**
     * Carga un reporte con el sql y el nombre del reporte sin la extension; incluye el uso de parametros, para su uso es la siguiente:<br><br>
     * [posicion][nameParam(0), value(1)] - Donde (0) es la posicion del parametro y el (1) para su valor
     * 
     * @param sql
     * @param iReport_jasper
     * @param ve_parameters - Se utiliza para dar valores a un reporte que requiera de recibir valores.
     */
    public static void cargarReporte(String sql, String iReport_jasper, String[][]ve_parameters)
    {
        generaReportes g = new generaReportes();
        g.generaReportes_ve(sql, iReport_jasper, ve_parameters);
    }
    /**
     * Coloca a un jTextfield la ubicacion del archivo donde decidimos guardar.
     * @param field - Cuadro de texto que recibe la ubicacion
     * @param nameExtension - Nombre asignado para referirse a la extension
     * @param extension - Extension de filtro para el archivo. Ej.: "xls"
     * @param titleDialog - Nombre para el titulo del Buscador
     */
    public static void setFileSaveDir(JTextField field, String nameExtension, String extension, String titleDialog)
    {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(nameExtension, extension);
        fileChoose.setFileFilter(filter);
        fileChoose.setDialogTitle(titleDialog);
        //agregar una ubicacion predeterminada
        //fileChoose.setCurrentDirectory(new File("e:/carpeta/"));
        //int result = fileChoose.showOpenDialog(this);
        int result = fileChoose.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChoose.getSelectedFile();
            field.setText(String.valueOf(file)+".xls");

        }
    }
    /**
     * Retorna en una cadena de texto la ubicacion del archivo con su extension
     * @param nameExtension
     * @param extension
     * @return
     */
    public static String getFileSaveDir(String nameExtension, String extension)
    {
        String tmp = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(nameExtension, extension);
        fileChoose.setFileFilter(filter);
        fileChoose.setDialogTitle("Guardar como...");
        int result = fileChoose.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
            tmp = String.valueOf(new File(fileChoose.getSelectedFile().toString()))+"."+extension;
        return tmp;
    }
    public static String getFileOpenDir(String nameExtension, String extension)
    {
        String tmp = "";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(nameExtension, extension);
        fileChoose.setFileFilter(filter);
        fileChoose.setDialogTitle("Guardar como...");
        int result = fileChoose.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
            tmp = String.valueOf(new File(fileChoose.getSelectedFile().toString()));
        return tmp;
    }
}
