/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author cepardov
 */
public class FuncionesSystem {
    
    public String fechahora(){
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        return date.format(now)+" "+hour.format(now);
    }
    
}
