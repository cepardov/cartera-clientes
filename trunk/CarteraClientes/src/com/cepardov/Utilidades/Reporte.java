/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

import java.awt.Frame;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author cepardov
 */
public class Reporte {
    String cn;
    Conect conn=new Conect();
    public Reporte(){
        
    }
    
    public void mostrarReporte(){
        try {
            String master = "report1.jasper";
            if(master == null){
                System.out.println("eroor master");
            }
            
            JasperReport masterReport=null;
            try {
                masterReport = (JasperReport) JRLoader.loadObject("/home/cepardov/report1.jasper");
            } catch (Exception e){
                System.out.println("error1:"+e);
            }
            
            Map parametro=new HashMap();
            JasperPrint p=JasperFillManager.fillReport(masterReport, parametro, conn.getConnection());
            JasperViewer view=new JasperViewer(p, false);
            view.setTitle("Prueba");
            view.setExtendedState(Frame.MAXIMIZED_BOTH);
            view.setVisible(true);
        } catch (Exception j){
            System.out.println("Reroor: "+j.getMessage());
        }
        
    }
}
