package com.cepardov.Utilidades;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
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

    public void startReport(String Template, String Param){

        try{
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA,USER,PASSWORD);
            javax.swing.JOptionPane.showMessageDialog(null,"Petición en ejecución espere por favor...");
            
            String template=Template+".jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);

            Map param=new HashMap();
            param.put("Estado",Param);

            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Visor de Reportes");
            visor.setVisible(true);

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }
}
