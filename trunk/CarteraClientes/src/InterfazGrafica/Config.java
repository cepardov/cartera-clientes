/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import com.cepardov.Utilidades.BackUpSample;
import com.cepardov.Utilidades.FuncionesSQL;
import com.cepardov.Utilidades.SistemaOperativo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author cepardov
 */
public class Config extends javax.swing.JInternalFrame {
    FuncionesSQL data=new FuncionesSQL();
    SistemaOperativo so=new SistemaOperativo();
    Object[][] dtPrev;
    int fila;
    String nombre=null;
    String id;
    /**
     * Creates new form Config
     */
    public Config() {
        initComponents();
        this.loadComboTemas();
        so.Sistema();
        this.getComboEstado();
        this.updateTabla();
        this.txtnombre.setText("");
        this.dir.setText("Dicho respaldo se guardara en \""+so.getUserDir()+so.getSepDir()+"\"");
        
    }
    
    public void detectComboTema(){
        String Skin=this.cbskin.getSelectedItem().toString();
    }
    
    public void loadComboTemas(){
        Login l=new Login();
        this.cbskin.setSelectedItem(l.Skin);
        this.cbtema.setSelectedItem(l.Tema);
        if("Predeterminado".equals(l.Skin)){
            this.cbtema.setEnabled(false);
        }else{
            this.cbtema.setEnabled(true);
        }
    }
    
    private void getComboEstado() {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM estado");
            modeloCombo.addElement("Seleccione");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbestado.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updateTabla(){  
        String[] columNames = {"ID","Nombre Estado"};  
        dtPrev = data.getEstado();
        DefaultTableModel datos = new DefaultTableModel(dtPrev,columNames);                        
        tabla.setModel(datos); 
        TableColumn columna = tabla.getColumn("ID");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        cbestado = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dir = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbskin = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbtema = new javax.swing.JComboBox();

        setClosable(true);
        setTitle("Configuracion de aplicación");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipos de Estado"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Configuracion Actual"));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregar Opción"));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnombre)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("General", jPanel1);

        jButton4.setText("Respaldar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setText("Esta Función le permitirá realiazar una copia de seguridad de su base de datos la cual se encontrará");

        jLabel2.setText("en la ruta de instalación de la aplicación.");

        jLabel3.setText("Para comenzar con el respaldo presione el botón respaldar...");

        dir.setText("Directorio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(261, 261, 261))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(dir))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dir)
                .addGap(35, 35, 35)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(169, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Respaldo de Información", jPanel5);

        jLabel4.setText("Los esquemas de colores tendrán efecto en el próximo inicio de sesión");

        cbskin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Predeterminado", "AutumnSkin", "BusinessBlackSteelSkin", "BusinessBlueSteelSkin", "BusinessSkin", "CremeCoffeeSkin", "CremeSkin", "EmeraldDuskSkin", "FieldOfWheatSkin", "FindingNemoSkin", "GreenMagicSkin", "MagmaSkin", "MangoSkin", "MistAquaSkin", "ModerateSkin", "NebulaBrickWallSkin", "NebulaSkin", "OfficeBlue2007Skin", "OfficeSilver2007Skin", "RavenGraphiteGlassSkin", "RavenGraphiteSkin", "RavenSkin", "SaharaSkin" }));
        cbskin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbskinItemStateChanged(evt);
            }
        });

        jLabel5.setText("Decoración de Ventanas");

        jLabel6.setText("Decoración de Botones");

        cbtema.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Predeterminado", "SubstanceAquaTheme", "SubstanceBarbyPinkTheme", "SubstanceBottleGreenTheme", "SubstanceBrownTheme", "SubstanceCharcoalTheme", "SubstanceCremeTheme", "SubstanceDarkVioletTheme", "SubstanceDesertSandTheme", "SubstanceEbonyTheme", "SubstanceJadeForestTheme", "SubstanceLightAquaTheme", "SubstanceLimeGreenTheme", "SubstanceNegatedTheme", "SubstanceOliveTheme", "SubstanceOrangeTheme", "SubstancePurpleTheme", "SubstanceRaspberryTheme", "SubstanceSaturatedTheme", "SubstanceSepiaTheme", "SubstanceSteelBlueTheme", "SubstanceSunGlareTheme", "SubstanceSunsetTheme", "SubstanceTerracottaTheme" }));
        cbtema.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbtemaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbtema, 0, 267, Short.MAX_VALUE)
                            .addComponent(cbskin, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbskin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbtema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(269, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Temas", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String resultado=null;
        String nombre=this.txtnombre.getText();
        
        
        if(this.txtnombre.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Debe Ingresar un nombre de estado...", "Error de datos", JOptionPane.ERROR_MESSAGE);
            this.txtnombre.setText("");
        } else {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
                    Statement st = conexion.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM estado WHERE nombre='"+nombre+"'");
                    while (rs.next()) {
                        resultado=rs.getObject("nombre").toString().toUpperCase();
                    }
                    rs.close();
                    System.out.println("result="+resultado);
                } catch (SQLException se) {
                    System.out.println(se);
                } catch (ClassNotFoundException e) {
                    System.out.println(e);
                }
                if(resultado!=null){
                    if(resultado.equals(nombre.toUpperCase())){
                    JOptionPane.showMessageDialog(null, "El estado "+nombre+" ya ha sido ingresada...", "Error de datos", JOptionPane.ERROR_MESSAGE);
                    this.txtnombre.setText("");
                    }else{
                         data.addEstado(nombre);
                         }
                }else{
                     data.addEstado(nombre);
                }
                this.getComboEstado();
                this.updateTabla();
                this.txtnombre.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if ( JOptionPane.showConfirmDialog(new JFrame(), 
       "Esta Usted seguro de eliminar el estado \""+nombre+"\"?\n\nEs posible que sea necesaria al momento realizar una transacción.", 
       "Confirmar Operación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) { 
        data.delEstado(nombre);
        this.updateTabla();
        this.getComboEstado();
        this.txtnombre.setText("");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        fila = tabla.rowAtPoint(evt.getPoint());
        if (fila > -1){
            this.txtnombre.setText(String.valueOf(tabla.getValueAt(fila, 1)));
            id=String.valueOf(tabla.getValueAt(fila, 0));
            
        }
    }//GEN-LAST:event_tablaMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here
        data.updateEstado(id,this.txtnombre.getText());
        this.updateTabla();
        this.getComboEstado();
        this.txtnombre.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        new BackUpSample().CrearBackup("localhost", "3306", "root", "", "BDSis",so.getUserDir()+"backup.sql");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbskinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbskinItemStateChanged
        // TODO add your handling code here:
        String Skin=this.cbskin.getSelectedItem().toString();
        if("Predeterminado".equals(Skin)){
            this.cbtema.setSelectedItem("Predeterminado");
            this.cbtema.setEnabled(false);
            data.updateTema("Predeterminado");
        }else{
            this.cbtema.setEnabled(true);
        }
        data.updateSkin(Skin);
    }//GEN-LAST:event_cbskinItemStateChanged

    private void cbtemaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbtemaItemStateChanged
        // TODO add your handling code here:
        String Tema=this.cbtema.getSelectedItem().toString();
        data.updateTema(Tema);
    }//GEN-LAST:event_cbtemaItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbestado;
    private javax.swing.JComboBox cbskin;
    private javax.swing.JComboBox cbtema;
    private javax.swing.JLabel dir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
