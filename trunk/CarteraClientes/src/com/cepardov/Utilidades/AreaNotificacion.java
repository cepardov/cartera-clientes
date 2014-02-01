/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cepardov.Utilidades;
import InterfazGrafica.Acerca;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author cepardov
 */
public class AreaNotificacion {
    FuncionesSystem fs=new FuncionesSystem();
    DetalleAplicacion app=new DetalleAplicacion();
    SystemTray systemtray = SystemTray.getSystemTray();
    
    private JFrame miframe;
    public String name=app.getNombre();
    private PopupMenu popup = new PopupMenu();
    private Image image =new ImageIcon(getClass().getResource("icon.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image,name, popup);
    private Timer timer;    
    private boolean band;

 public AreaNotificacion( JFrame frame)
 {
     
    this.miframe = frame;
    //comprueba si SystemTray es soportado en el sistema
    if (SystemTray.isSupported())
    {
      //obtiene instancia SystemTray
      SystemTray systemtray = SystemTray.getSystemTray();
      //acciones del raton sobre el icono en la barra de tareas
      MouseListener mouseListener = new MouseListener() {
        public void mouseClicked(MouseEvent evt) {}
        public void mouseEntered(MouseEvent evt) {}
        public void mouseExited(MouseEvent evt) {}
        public void mousePressed(MouseEvent evt) {}
        public void mouseReleased(MouseEvent evt) {}
    };
    //ACCIONES DEL MENU POPUP
    ActionListener AboutListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {            
            Acerca about=new Acerca();
            about.setVisible(true);
        }
    };
    //Salir de aplicacion
    ActionListener exitListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {            
            System.exit(0);
        }
    };
    //Restaurar aplicacion
    ActionListener RestaurarListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {            
            miframe.setVisible(true);
            miframe.setExtendedState( JFrame.NORMAL );
            miframe.repaint();
            band = true;
        }
    };
    //Se crean los Items del menu PopUp y se añaden
    MenuItem AboutItem = new MenuItem("Acerca de..");
    AboutItem.addActionListener(AboutListener);
    popup.add(AboutItem);
    
    MenuItem SalirItem = new MenuItem("Cerrar Sesión");
    SalirItem.addActionListener(exitListener);
    popup.add(SalirItem);
/*
    MenuItem ItemRestaurar = new MenuItem("Mostrar");
    ItemRestaurar.addActionListener(RestaurarListener);
    popup.add(ItemRestaurar);
    trayIcon.setImageAutoSize(true);
    trayIcon.addMouseListener(mouseListener);
*/
    //Añade el TrayIcon al SystemTray
    try {
    systemtray.add(trayIcon);
    } catch (AWTException e) {
    System.err.println( "Error:" + e.getMessage() );
    }
    
  } else {
     System.err.println( "Error: SystemTray no es soportado" );
  }
    

    //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
     miframe.addWindowListener(new WindowAdapter(){
        @Override
        public void windowIconified(WindowEvent e){
           miframe.setVisible(true);//Se oculta JFrame
           //Se inicia una tarea cuando se minimiza
           MensajeTrayIcon("La aplicación seguirá funcionando en segundo plano", MessageType.INFO);
           band = false;
           timer = new Timer();           
           //timer.schedule(new MyTimerTask(),0, 100000 );
        }
    });
    }

    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo)
    {
        trayIcon.displayMessage(name, texto, tipo);
    }

    //clase interna que manejara una accion en segundo plano
    
    /*class MyTimerTask extends TimerTask {
        public void run() {
            if(band)//Termina Timer
                timer.cancel();
            else //realiza acción
                Suma();
        }
        //Una accion a realiza cuando la aplicacion a sido minimizada
        public void Suma()
        {
            MensajeTrayIcon("La Aplicación se esta ejecutando en segundo plano.", MessageType.INFO);
            MensajeTrayIcon("No existen notificaciones nuevas...", MessageType.INFO);
        }

    }*/
}
