package com.cepardov.Utilidades;

import java.awt.HeadlessException;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

public class EjecutarReporte {
    public static final String DRIVER="com.mysql.jdbc.Driver";
        public static final String RUTA="jdbc:mysql://localhost/BDSis";
        public static final String USER="root";
        public static final String PASSWORD=null;
	public static Connection CONEXION;
        
        /**
         *Crea mediante una libreria jasper report un reporte
         * @param Template Nombre de archivo .jasper
         * @param Param Parametro del archivo jasper o filtro
         */
    public void startReport(String Template, String Param, String Desde,String Hasta){

        try{
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA,USER,PASSWORD);
            javax.swing.JOptionPane.showMessageDialog(null,"Petición en ejecución espere por favor...");
            
            String template=Template+".jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);

            Map param=new HashMap();
            param.put("Estado",Param);
            param.put("Desde",Desde);
            param.put("Hasta",Hasta);

            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Visor de Reportes");
            visor.setVisible(true);

        }catch(ClassNotFoundException | SQLException | HeadlessException | JRException se){
            JOptionPane.showMessageDialog(null, "Disculpe las molestias, se ha producido un error en una de nuestras herramientas:\n"
                    + "\tNombre de Modulo: "+this.getClass().getName()+"\n\tDescripción de error: "+se, "Error de ejecución", JOptionPane.ERROR_MESSAGE);
        }
    }
}
