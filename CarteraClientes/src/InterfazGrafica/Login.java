/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import com.cepardov.Utilidades.DetalleAplicacion;
import com.cepardov.Utilidades.FuncionesSQL;
import com.cepardov.Utilidades.SistemaOperativo;
import com.cepardov.Utilidades.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author cepardov
 */
public class Login extends javax.swing.JFrame {
    //Se instancia las Clases datos y informacion de plataforma
    FuncionesSQL data=new FuncionesSQL();
    SistemaOperativo so=new SistemaOperativo();
    DetalleAplicacion app=new DetalleAplicacion();
    int intentos=3;
    
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setTitle("Inicio de Sesión - "+app.getNombre());
        this.lblVersion.setText("V"+app.version());
        setLocationRelativeTo(null);//Setea posición de ventana a 0,0 centro
        this.comprobarUsuario();//Se comprueba si existe base de datos y al menos un usuario
        so.Sistema();//Se carga información sobre la plataforma de sistema operativo
    }
    //Aquí se comprueba si existe un usuario en base de datos, si no es asi se muestra ventana
    //de registro de usuario.
    public final void comprobarUsuario(){
        System.out.println("Comprobando si existen usuarios en base de datos...");
        if(data.getUsuario()==0){
            PrimeraEjecucion p=new PrimeraEjecucion();
            p.setVisible(true);
        } else {
            System.out.println("Existen en base de datos "+data.getUsuario()+" usuario (s) disponibles.");
        }
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("resources/login.png"));
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtclave = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblVersion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de sesión");
        setIconImage(getIconImage());
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jLabel2.setText("Usuario");

        jLabel3.setText("Contraseña");

        txtusuario.setText("cepardov");
        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });

        txtclave.setText("003170527");

        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/login.png"))); // NOI18N
        btnLogin.setText("Iniciar Sesión");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblVersion.setText("Version");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(txtclave))))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblVersion)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblVersion)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogin)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        txtusuario.getAccessibleContext().setAccessibleName("");
        txtusuario.getAccessibleContext().setAccessibleDescription("Nombre de Usuario");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        if (intentos==0){
            dispose();
            JOptionPane.showMessageDialog(null, "Inicio de sesión denegado!\nContacte con administrador del sistema...", "Error de Inicio de sesión.", JOptionPane.ERROR_MESSAGE);
        }else if(this.txtusuario.getText().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Nombre de usuario no es indicado.\nIngrese su nombre de usuario y contraseña para ingresar al sistema.", "Error de Inicio de sesión.", JOptionPane.ERROR_MESSAGE);
        } else if (this.txtclave.getText().toString().isEmpty()){
            JOptionPane.showMessageDialog(null, "Contraseña no es indicado.\nIngrese su contraseña y nombre de usuario para ingresar al sistema.", "Error de Inicio de sesión.", JOptionPane.WARNING_MESSAGE);
        } else {
            //Extrae información ingresada-
            String usuario=this.txtusuario.getText();
            String clave=this.txtclave.getText();

            Usuario u=new Usuario();
            u=u.verificarUsuario(usuario, clave);
            
            if(u==null){
                intentos=intentos-1;
                System.out.println("Error inicio de sesión");
                JOptionPane.showMessageDialog(this, "El nombre de usuario y/o contraseña no son validos.\nIntentos restantes "+intentos);
            }else if(u!=null){
                Escritorio es=new Escritorio(u.getNombre(),u.getApellido(),u.getUsuario());
                es.setState(JFrame.MAXIMIZED_BOTH);
                es.setTitle(app.getNombre()+" - "+u.getNombre()+" "+u.getApellido());
                es.setVisible(true);
                dispose();
            }
        }
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyTyped

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblVersion;
    private javax.swing.JPasswordField txtclave;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
