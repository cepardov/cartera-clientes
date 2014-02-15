/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import com.cepardov.Utilidades.AreaNotificacion;
import com.cepardov.Utilidades.EjecutarReporte;
import com.cepardov.Utilidades.FuncionesSQL;
import com.cepardov.Utilidades.FuncionesSystem;
import com.cepardov.Utilidades.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;

/**
 *
 * @author cepardov
 */
public class Escritorio extends javax.swing.JFrame {
    AreaNotificacion notifi=new AreaNotificacion(this);
    FuncionesSystem fs = new FuncionesSystem();
    FuncionesSQL data = new FuncionesSQL();
    String usuario = null;
    Object[][] dtPrev;
    Timer timer;
    String fechaHoraAnterior;
    String fechaHoraActual;

    /**
     * Creates new form Escritorio
     */
    public Escritorio() {
        initComponents();
    }
    
    public Escritorio(String nombre, String Apellido, String Usuario) {
        initComponents();
        this.PanelBloq.setVisible(false);
        this.usuario = Usuario;
        notifi.MensajeTrayIcon("Bienvenido (a) " + nombre + " " + Apellido, TrayIcon.MessageType.INFO);
        setState(JFrame.MAXIMIZED_BOTH);
        timer = new Timer();        
        timer.schedule(new RemindTask(), 0, 60000);
        fechaHoraAnterior = fs.fechahora();
        this.lbltiempo.setText(fs.fechahora());
        this.lblultimacita.setText("");
        this.lblultimaobs.setText("");
        this.lblobservacion.setText("");
        this.lblcita.setText("");
        
    }
    
    public void BuscaCita() throws ClassNotFoundException, SQLException {
        //Consulta y setea hora actual del sistema en variable local fechaHora desde clase funciones de sistema
        fechaHoraAnterior = fechaHoraActual;
        this.lbltiempo.setText(fechaHoraAnterior);
        fechaHoraActual = fs.fechahora();
        
        System.out.println("anterior=" + fechaHoraAnterior);
        System.out.println("actual=" + fechaHoraActual);
        
        this.lblultimacita.setText(this.lblcita.getText());
        this.lblultimaobs.setText(this.lblobservacion.getText());
        //Busca nuevas citas
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM  `agenda` WHERE `fecha` BETWEEN  '" + fechaHoraAnterior + "' AND '" + fechaHoraActual + "'");
        while (rs.next()) {
            this.lblcita.setText(rs.getObject("tipo").toString() + " " + rs.getObject("nombre").toString() + " " + rs.getObject("paterno").toString() + " " + rs.getObject("materno").toString() + " el " + rs.getObject("fecha").toString());
            this.lblobservacion.setText("Observación: " + rs.getObject("observacion"));
            notifi.MensajeTrayIcon(lblcita.getText(), TrayIcon.MessageType.INFO);
        }        
        rs.close();
    }
    
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/icon.png"));
        
        
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        p = new javax.swing.JDesktopPane();
        Panel = new javax.swing.JPanel();
        lbltiempo = new javax.swing.JLabel();
        lblcita = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblobservacion = new javax.swing.JLabel();
        lblultimacita = new javax.swing.JLabel();
        lblultimaobs = new javax.swing.JLabel();
        btnhistarial = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        PanelBloq = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        btnDesBloq = new javax.swing.JButton();
        MenuBar = new javax.swing.JMenuBar();
        btnArchivo = new javax.swing.JMenu();
        MenuCliente = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        btnReportes = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        btnHerramientas = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        btnBuscaCliente = new javax.swing.JMenuItem();
        selCambiaClave = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(54, 54, 54));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(730, 300));

        p.setAutoscrolls(true);

        Panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Notificaciones Pendientes"));

        lbltiempo.setText("Hora");

        lblcita.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        lblcita.setText("No se encontraron nuevas notificaciones...");
        lblcita.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel1.setText("Última Actualización: ");

        lblobservacion.setForeground(new java.awt.Color(255, 39, 0));
        lblobservacion.setText("Observacion");

        lblultimacita.setText("jLabel2");

        lblultimaobs.setText("jLabel3");

        btnhistarial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/btnhistorial.png"))); // NOI18N
        btnhistarial.setText("Ver Historial de Notificaciones");
        btnhistarial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhistarialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(lblcita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblobservacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbltiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 120, Short.MAX_VALUE))
                    .addComponent(lblultimacita, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblultimaobs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnhistarial)))
                .addContainerGap())
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbltiempo)
                    .addComponent(jLabel1))
                .addGap(5, 5, 5)
                .addComponent(lblcita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblobservacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblultimacita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblultimaobs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnhistarial)
                .addContainerGap())
        );

        Panel.setBounds(10, 10, 660, 260);
        p.add(Panel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        PanelBloq.setBorder(javax.swing.BorderFactory.createTitledBorder("Pantalla Bloqueada"));

        jLabel2.setText("Ingrese sus credenciales de inicio de sesión para desbloquear.");

        jLabel3.setText("Usuario");

        jLabel4.setText("Contraseña");

        btnDesBloq.setText("Desbloquear");
        btnDesBloq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesBloqActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBloqLayout = new javax.swing.GroupLayout(PanelBloq);
        PanelBloq.setLayout(PanelBloqLayout);
        PanelBloqLayout.setHorizontalGroup(
            PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBloqLayout.createSequentialGroup()
                .addGroup(PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelBloqLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(PanelBloqLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(txtClave))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBloqLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDesBloq)
                .addGap(55, 55, 55))
        );
        PanelBloqLayout.setVerticalGroup(
            PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBloqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelBloqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDesBloq)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newArchivo.png"))); // NOI18N
        btnArchivo.setText("Achivo ");

        MenuCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        MenuCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newControl.png"))); // NOI18N
        MenuCliente.setText("Centro Control Clientes");
        MenuCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuClienteActionPerformed(evt);
            }
        });
        btnArchivo.add(MenuCliente);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newLockIcon.png"))); // NOI18N
        jMenuItem10.setText("Bloquear");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        btnArchivo.add(jMenuItem10);
        btnArchivo.add(jSeparator1);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newCloseIcon.png"))); // NOI18N
        jMenuItem5.setText("Cerrar");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        btnArchivo.add(jMenuItem5);

        MenuBar.add(btnArchivo);

        btnReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newReportes.png"))); // NOI18N
        btnReportes.setText("Reportes ");

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/newEstadoCli.png"))); // NOI18N
        jMenuItem6.setText("Estado Clientes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        btnReportes.add(jMenuItem6);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/ver.png"))); // NOI18N
        jMenuItem9.setText("Lista Clientes");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        btnReportes.add(jMenuItem9);

        MenuBar.add(btnReportes);

        btnHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/tool-box-icon.png"))); // NOI18N
        btnHerramientas.setText("Herramientas ");

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/mantenedor.png"))); // NOI18N
        jMenu4.setText("Mantenedores");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Travel-BMV-icon.png"))); // NOI18N
        jMenuItem3.setText("Vehículos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem3);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/credit.png"))); // NOI18N
        jMenu6.setText("Credito");

        jMenuItem4.setText("Financiamiento");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem4);

        jMenuItem7.setText("Ejecutivos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem7);

        jMenu4.add(jMenu6);

        btnHerramientas.add(jMenu4);

        btnBuscaCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        btnBuscaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N
        btnBuscaCliente.setText("Buscar Cliente");
        btnBuscaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscaClienteActionPerformed(evt);
            }
        });
        btnHerramientas.add(btnBuscaCliente);

        selCambiaClave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        selCambiaClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/password-icon.png"))); // NOI18N
        selCambiaClave.setText("Cambiar Contraseña");
        selCambiaClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selCambiaClaveActionPerformed(evt);
            }
        });
        btnHerramientas.add(selCambiaClave);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/settings-icon.png"))); // NOI18N
        jMenuItem8.setText("Configuraciones");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        btnHerramientas.add(jMenuItem8);

        MenuBar.add(btnHerramientas);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/FAQ-icon.png"))); // NOI18N
        jMenu3.setText("Ayuda ");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/about.png"))); // NOI18N
        jMenuItem1.setText("Acerca de...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        MenuBar.add(jMenu3);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelBloq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 253, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelBloq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(p, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        MantenedorFianaciamiento mf = new MantenedorFianaciamiento();
        p.add(mf);
        mf.show();
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        MantenedorVehiculos mv = new MantenedorVehiculos();
        p.add(mv);
        mv.show();
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    private void MenuClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuClienteActionPerformed
        // TODO add your handling code here:
        ControlCliente newc = new ControlCliente();
        p.add(newc);
        newc.show();
    }//GEN-LAST:event_MenuClienteActionPerformed
    
    private void selCambiaClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selCambiaClaveActionPerformed
        // TODO add your handling code here:
        editarclave edc = new editarclave(usuario);
        p.add(edc);
        edc.show();
    }//GEN-LAST:event_selCambiaClaveActionPerformed
    
    private void btnhistarialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhistarialActionPerformed
        // TODO add your handling code here:
        HistorialNotificaciones hn = new HistorialNotificaciones();
        p.add(hn);
        hn.show();
    }//GEN-LAST:event_btnhistarialActionPerformed
    
    private void btnBuscaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscaClienteActionPerformed
        // TODO add your handling code here:
        BuscarCliente bc = new BuscarCliente();
        p.add(bc);
        bc.show();
    }//GEN-LAST:event_btnBuscaClienteActionPerformed
    
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        MantenedorEjecutivos me = new MantenedorEjecutivos();
        p.add(me);
        me.show();
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Acerca ac = new Acerca();
        ac.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        Reportes r = new Reportes();
        p.add(r);
        r.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        Config conf = new Config();
        p.add(conf);
        conf.show();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        EjecutarReporte er=new EjecutarReporte();
        er.startReport("listaClientes", "");
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        this.bloquear();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void btnDesBloqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesBloqActionPerformed
        // TODO add your handling code here:
        if(this.txtUsuario.getText().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nombre de usuario no es indicado.\nIngrese su nombre de usuario y contraseña para ingresar al sistema.", "Error de Inicio de sesión.", JOptionPane.ERROR_MESSAGE);
        } else if (this.txtClave.getText().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Contraseña no es indicado.\nIngrese su contraseña y nombre de usuario para ingresar al sistema.", "Error de Inicio de sesión.", JOptionPane.WARNING_MESSAGE);
        } else {
            //Extrae información ingresada-
            String usuario=this.txtUsuario.getText();
            String clave=this.txtClave.getText();

            Usuario u=new Usuario();
            u=u.verificarUsuario(usuario, clave);
            
            if(u==null){
                System.out.println("Error inicio de sesión");
                JOptionPane.showMessageDialog(this, "El nombre de usuario y/o contraseña no son validos.");
            }else if(u!=null){
                this.desbloquear();
            }
        }
        
    }//GEN-LAST:event_btnDesBloqActionPerformed
    
    public void bloquear(){
        this.MenuBar.setEnabled(false);
        this.Panel.setEnabled(false);
        this.btnArchivo.setEnabled(false);
        this.btnReportes.setEnabled(false);
        this.btnHerramientas.setEnabled(false);
        this.btnhistarial.setEnabled(false);
        this.PanelBloq.setVisible(true);
        this.p.setVisible(false);
    }
    
    public void desbloquear(){
        this.txtClave.setText("");
        this.MenuBar.setEnabled(true);
        this.Panel.setEnabled(true);
        this.btnArchivo.setEnabled(true);
        this.btnReportes.setEnabled(true);
        this.btnHerramientas.setEnabled(true);
        this.btnhistarial.setEnabled(true);
        this.PanelBloq.setVisible(false);
        this.p.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Escritorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Escritorio().setVisible(true);
            }
        });
    }

    class RemindTask extends TimerTask {

        public void run() {
            
            try {
                BuscaCita();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JMenuItem MenuCliente;
    private javax.swing.JPanel Panel;
    private javax.swing.JPanel PanelBloq;
    private javax.swing.JMenu btnArchivo;
    private javax.swing.JMenuItem btnBuscaCliente;
    private javax.swing.JButton btnDesBloq;
    private javax.swing.JMenu btnHerramientas;
    private javax.swing.JMenu btnReportes;
    private javax.swing.JButton btnhistarial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblcita;
    private javax.swing.JLabel lblobservacion;
    private javax.swing.JLabel lbltiempo;
    private javax.swing.JLabel lblultimacita;
    private javax.swing.JLabel lblultimaobs;
    public javax.swing.JDesktopPane p;
    private javax.swing.JMenuItem selCambiaClave;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
