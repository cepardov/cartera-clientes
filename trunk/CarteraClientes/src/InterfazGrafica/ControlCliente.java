/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package InterfazGrafica;

import com.cepardov.Utilidades.FuncionesSQL;
import com.cepardov.Utilidades.FuncionesSystem;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author cepardov
 */
public class ControlCliente extends javax.swing.JInternalFrame {

    FuncionesSystem fs = new FuncionesSystem();
    FuncionesSQL data = new FuncionesSQL();
    String titleframe = "Centro de Control Cliente";
    Object[][] dtPrev;
    Object[][] dtPrev2 = null;
    String idcotizacion;
    int fila;
    int datoFinanciamiento=0;
    int datoEjecutivo=0;
    int datoModelo=0;
    int datoMarca = 0;

    /**
     * Creates new form NuevoCliente
     */
    public ControlCliente() {
        initComponents();
        this.lblfecha.setText("");
        this.btnupdate.setVisible(false);
        this.getComboMarcas();
        this.getComboCredito();
        this.updateClearTabla();
        this.getComboEstado();
    }

    private void updateTablaHistoria(String rutCliente) {
        String[] columNames = {"Fecha", "Observación"};
        dtPrev = data.getHistorialCliente(rutCliente);
        DefaultTableModel datos = new DefaultTableModel(dtPrev, columNames);
        tablahistoria.setModel(datos);
        TableColumn columna = tablahistoria.getColumn("Fecha");
    }

    private void updateTablaAgenda(String rutCliente) {
        String[] columNames = {"ID", "Fecha", "Tipo", "Observación"};
        dtPrev = data.getAgendamiento(rutCliente);
        DefaultTableModel datos = new DefaultTableModel(dtPrev, columNames);
        tablaAgenda.setModel(datos);
        TableColumn columna = tablaAgenda.getColumn("Fecha");
    }

    private void updateClearTabla() {
        String[] columNames = {"Sin Datos"};
        DefaultTableModel datos = new DefaultTableModel(dtPrev2, columNames);
        tablahistoria.setModel(datos);
        tablacotizacion.setModel(datos);
        tablaAgenda.setModel(datos);
        TableColumn columna = tablahistoria.getColumn("Sin Datos");
    }

    private void updateTablaCotizacion(String rutCliente) {
        String[] columNames = {"ID", "Fecha", "Marca", "Modelo", "Credito", "Ejecutivo", "Tipo", "Neto", "Desc.", "IVA", "Total", "Obaservaciones"};
        dtPrev = data.getCotizacionCliente(rutCliente);
        DefaultTableModel datos = new DefaultTableModel(dtPrev, columNames);
        tablacotizacion.setModel(datos);
        TableColumn columna = tablacotizacion.getColumn("Fecha");
    }

    public void calculo() {
        int valor = Integer.parseInt(this.txtNeto.getText());
        int desc = Integer.parseInt(this.txtDesc.getText());
        int piva = Integer.parseInt(this.txtIva.getText());
        int neto = valor - desc;
        int iva = neto * piva / 100;
        int total = neto + iva;

        this.txtTotal.setText(Integer.toString(total));
    }

    private void getComboMarcas() {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM marca");
            modeloCombo.addElement("Seleccione");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbMarcas.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getComboEstado() {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM estado");
            modeloCombo.addElement("Pendiente");
            modeloCombo.addElement("Cotización");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbEstado.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getComboModelo(int marca) {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM modelo WHERE idMarca='" + marca + "'");
            modeloCombo.addElement("Seleccione");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbModelo.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getComboCredito() {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM financiamiento");
            modeloCombo.addElement("Seleccione");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbCredito.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getComboEjecutivo(int credito) {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ejecutivo WHERE idFinanciamiento='" + credito + "'");
            modeloCombo.addElement("Seleccione");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre"));
            }
            rs.close();
            this.cbEjecutivo.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Escritorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clear() {
        this.txtnombre.setText("");
        this.txtpaterno.setText("");
        this.txtmaterno.setText("");
        this.txtdireccion.setText("");
        this.txtciudad.setText("");
        this.txtemail.setText("");
        this.txttelefono.setText("");
        this.cbEstado.setSelectedItem("Sin Informar");
        this.lblfecha.setText("");
        this.setTitle(titleframe);
    }

    public void getCliente(String rut) throws ClassNotFoundException, SQLException {
        System.out.println("rut=" + rut);
        Class.forName("com.mysql.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE rutCliente='" + rut + "'");
        while (rs.next()) {
            this.txtnombre.setText(rs.getObject("nombre").toString());
            this.txtpaterno.setText(rs.getObject("paterno").toString());
            this.txtmaterno.setText(rs.getObject("materno").toString());
            this.cbEstado.setSelectedItem(rs.getObject("estado"));
            this.txtdireccion.setText(rs.getObject("direccion").toString());
            this.txtciudad.setText(rs.getObject("ciudad").toString());
            this.txttelefono.setText(rs.getObject("telefono").toString());
            this.txtemail.setText(rs.getObject("email").toString());
            this.lblfecha.setText("Fecha de Ingreso: " + rs.getObject("fechaIngreso").toString());
        }
        rs.close();
        if (this.lblfecha.getText().isEmpty()) {
            this.btnupdate.setVisible(false);
            this.btnsave.setVisible(true);
            this.lblfecha.setText("Cliente no encontrado!");
            this.btnsavecotiza.setEnabled(false);
            this.btnmodifcotiza.setEnabled(false);
            this.btndelcotiza.setEnabled(false);
        } else {
            this.btnsave.setVisible(false);
            this.btnupdate.setVisible(true);
            this.btnsavecotiza.setEnabled(true);
            this.btnmodifcotiza.setEnabled(true);
            this.btndelcotiza.setEnabled(true);
            this.setTitle(titleframe + " [" + this.txtnombre.getText() + " " + this.txtpaterno.getText() + "] - Estado [" + this.cbEstado.getSelectedItem().toString() + "]");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        txtnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpaterno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtmaterno = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtciudad = new javax.swing.JTextField();
        lblfecha = new javax.swing.JLabel();
        txtrutCliente = new javax.swing.JFormattedTextField();
        btnsave = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablahistoria = new javax.swing.JTable();
        tabCotiazcion = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        cbMarcas = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        cbModelo = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtIva = new javax.swing.JFormattedTextField();
        txtNeto = new javax.swing.JFormattedTextField();
        txtDesc = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        cbFinancia = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablacotizacion = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        cbCredito = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cbEjecutivo = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtobs = new javax.swing.JTextArea();
        btnsavecotiza = new javax.swing.JButton();
        btnmodifcotiza = new javax.swing.JButton();
        btndelcotiza = new javax.swing.JButton();
        tabAgenda = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        cbTipoAgendamiento = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtfechaAgenda = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        cbHora = new javax.swing.JComboBox();
        cbMinutos = new javax.swing.JComboBox();
        txtobservacionAgenda = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaAgenda = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Centro de Control Clientes");
        setFrameIcon(null);
        setMaximumSize(new java.awt.Dimension(1024, 710));
        setMinimumSize(new java.awt.Dimension(104, 100));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Personales"));

        jLabel1.setText("RUT o ROL");

        jLabel2.setText("Nombre o Razón social");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel5.setText("Dirección");

        jLabel6.setText("Telefono");

        jLabel7.setText("Estado");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sin Informar", "Cotización", "En Espera...", "Rechazado", "Aprobado" }));

        jLabel8.setText("E-Mail");

        jLabel9.setText("Ciudad");

        lblfecha.setText("Ingresado el:");

        try {
            txtrutCliente.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-A")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtrutCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtrutClienteFocusLost(evt);
            }
        });

        btnsave.setText("Guardar");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnupdate.setText("Corregir");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial Cliente"));

        tablahistoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Fecha", "Observaciones"
            }
        ));
        jScrollPane5.setViewportView(tablahistoria);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel3)
                                .addGap(53, 53, 53))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(107, 107, 107)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel5)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(txtdireccion)))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel9))
                                .addGap(63, 87, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtmaterno)
                                    .addComponent(txtciudad))
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txttelefono))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnsave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnupdate)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnBuscar)
                    .addComponent(lblfecha)
                    .addComponent(txtrutCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtmaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnupdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tab.addTab("Cliente", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ingreso Cotización"));

        jLabel10.setText("Marca");

        cbMarcas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMarcasItemStateChanged(evt);
            }
        });

        jLabel11.setText("Modelo");

        jLabel12.setText("Neto");

        jLabel13.setText("Desc.");

        jLabel14.setText("IVA");

        jLabel15.setText("Total");

        txtTotal.setEditable(false);
        txtTotal.setText("0");

        txtIva.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtIva.setText("19");
        txtIva.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIvaFocusLost(evt);
            }
        });

        txtNeto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtNeto.setText("0");
        txtNeto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNetoFocusLost(evt);
            }
        });

        txtDesc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtDesc.setText("0");
        txtDesc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescFocusLost(evt);
            }
        });

        jLabel16.setText("Condiciones de Venta");

        cbFinancia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Contado", "Credito", "Otros" }));
        cbFinancia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbFinanciaItemStateChanged(evt);
            }
        });

        jLabel24.setText("%");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jLabel24)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotal))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbFinancia, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 81, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(cbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbFinancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Vehículos Cotizados"));

        tablacotizacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tablacotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablacotizacionMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablacotizacion);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Financiamiento"));

        cbCredito.setEnabled(false);
        cbCredito.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCreditoItemStateChanged(evt);
            }
        });

        jLabel17.setText("Credito");

        jLabel18.setText("Ejecutivo");

        cbEjecutivo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        cbEjecutivo.setEnabled(false);

        jLabel19.setText("Observaciones");

        txtobs.setColumns(20);
        txtobs.setRows(5);
        jScrollPane3.setViewportView(txtobs);

        btnsavecotiza.setText("Guardar");
        btnsavecotiza.setEnabled(false);
        btnsavecotiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsavecotizaActionPerformed(evt);
            }
        });

        btnmodifcotiza.setText("Modificar");
        btnmodifcotiza.setEnabled(false);
        btnmodifcotiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodifcotizaActionPerformed(evt);
            }
        });

        btndelcotiza.setText("Eliminar");
        btndelcotiza.setEnabled(false);
        btndelcotiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndelcotizaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbEjecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsavecotiza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmodifcotiza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btndelcotiza)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(cbEjecutivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsavecotiza)
                            .addComponent(btnmodifcotiza)
                            .addComponent(btndelcotiza))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );

        javax.swing.GroupLayout tabCotiazcionLayout = new javax.swing.GroupLayout(tabCotiazcion);
        tabCotiazcion.setLayout(tabCotiazcionLayout);
        tabCotiazcionLayout.setHorizontalGroup(
            tabCotiazcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabCotiazcionLayout.setVerticalGroup(
            tabCotiazcionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabCotiazcionLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tab.addTab("Cotizaciones", tabCotiazcion);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Agendamiento de Cliente"));

        cbTipoAgendamiento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Comunicarse con...", "Citar a...", "Llegada de Unidad", "Entrega de Unidad", "Facturación", "Solicitud de...", "Otro," }));

        jLabel20.setText("Tipo");

        jLabel21.setText("Fecha");

        jLabel22.setText("a las");

        cbHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "H", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        cbMinutos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        jLabel23.setText("Observación");

        jButton4.setText("Confirmar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoAgendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel21)
                        .addGap(3, 3, 3)
                        .addComponent(txtfechaAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22)
                        .addGap(2, 2, 2)
                        .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtobservacionAgenda, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbTipoAgendamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20)
                        .addComponent(jLabel21))
                    .addComponent(txtfechaAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(cbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtobservacionAgenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Notificaciones del Cliente"));

        tablaAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tipo", "Fecha", "Observación"
            }
        ));
        jScrollPane4.setViewportView(tablaAgenda);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout tabAgendaLayout = new javax.swing.GroupLayout(tabAgenda);
        tabAgenda.setLayout(tabAgendaLayout);
        tabAgendaLayout.setHorizontalGroup(
            tabAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tabAgendaLayout.setVerticalGroup(
            tabAgendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabAgendaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        tab.addTab("Agendamiento", tabAgenda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        // TODO add your handling code here:
        this.txtrutCliente.setBackground(Color.white);
        this.txtnombre.setBackground(Color.white);
        this.txttelefono.setBackground(Color.white);
        if (this.txtrutCliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "RUT o ROL no se ha introducido o no se ha escrito correctamente...\nEl formato de ingreso son solo numeros y k ejemplo:\n99999999k", "¡ups! Algo salio mal durante la verificacion de datos...", JOptionPane.ERROR_MESSAGE);
            this.txtrutCliente.setBackground(Color.getHSBColor(254, 46, 38));
        } else if (this.txtnombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "El campo NOMBRE o RAZÓN SOCIAL es obligatorio para registrar el cliente\nCorrija e intente nuevamente.", "¡ups! Algo salio mal durante la verificacion de datos...", JOptionPane.ERROR_MESSAGE);
            this.txtnombre.setBackground(Color.getHSBColor(254, 46, 38));
        } else if (this.txttelefono.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Es necesario mantener algún lazo de comunicación con el cliente, por esta razón debe ingrezar un numero de contacto\nSi no desea agregar un numero ingrese el numero 0 (cero)", "¡ups! Algo salio mal durante la verificacion de datos...", JOptionPane.WARNING_MESSAGE);
            this.txttelefono.setBackground(Color.getHSBColor(254, 46, 38));
        } else if (this.cbEstado.getSelectedItem().toString().equals("Seleccione")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un estado para el cliente", "¡ups! Algo salio mal durante la verificacion de datos...", JOptionPane.WARNING_MESSAGE);
            this.txttelefono.setBackground(Color.getHSBColor(254, 46, 38));
        } else {
            String rutCliente = this.txtrutCliente.getText();
            String nombre = this.txtnombre.getText();
            String paterno = this.txtpaterno.getText();
            String materno = this.txtmaterno.getText();
            String Estado = this.cbEstado.getSelectedItem().toString();
            String direccion = this.txtdireccion.getText();
            String ciudad = this.txtciudad.getText();
            String telefono = this.txttelefono.getText();
            String email = this.txtemail.getText();
            String fechaIngreso = fs.fechahora();
            
            if (Estado.equals("Seleccione")) {
                Estado="Pendiente";
            }
            
            data.addCliente(rutCliente, nombre, paterno, materno, Estado, direccion, ciudad, telefono, email, fechaIngreso);
        }
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        this.clear();
        String rutCliente = this.txtrutCliente.getText();
        try {
            this.getCliente(rutCliente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.updateClearTabla();
        this.updateTablaHistoria(rutCliente);
        this.updateTablaCotizacion(rutCliente);
        this.updateTablaAgenda(rutCliente);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
        // TODO add your handling code here:
        String rutCliente = this.txtrutCliente.getText();
        String nombre = this.txtnombre.getText();
        String paterno = this.txtpaterno.getText();
        String materno = this.txtmaterno.getText();
        String Estado = this.cbEstado.getSelectedItem().toString();
        String direccion = this.txtdireccion.getText();
        String ciudad = this.txtciudad.getText();
        String telefono = this.txttelefono.getText();
        String email = this.txtemail.getText();

        data.updateCliente(rutCliente, nombre, paterno, materno, Estado, direccion, ciudad, telefono, email);
    }//GEN-LAST:event_btnupdateActionPerformed

    private void txtrutClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtrutClienteFocusLost
        // TODO add your handling code here:
        this.clear();
        String rutCliente = this.txtrutCliente.getText();
        try {
            this.getCliente(rutCliente);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControlCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.updateClearTabla();
        this.updateTablaHistoria(rutCliente);
        this.updateTablaCotizacion(rutCliente);
        this.updateTablaAgenda(rutCliente);
    }//GEN-LAST:event_txtrutClienteFocusLost

    private void cbMarcasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMarcasItemStateChanged
        // TODO add your handling code here:
        String marca = this.cbMarcas.getSelectedItem().toString();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT idMarca FROM marca WHERE nombre='" + marca + "'");
            while (rs.next()) {
                datoMarca = Integer.parseInt(rs.getObject("idMarca").toString());
            }
            rs.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        this.getComboModelo(datoMarca);
    }//GEN-LAST:event_cbMarcasItemStateChanged

    private void txtNetoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNetoFocusLost
        // TODO add your handling code here:
        this.calculo();
    }//GEN-LAST:event_txtNetoFocusLost

    private void txtDescFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescFocusLost
        // TODO add your handling code here:
        this.calculo();
    }//GEN-LAST:event_txtDescFocusLost

    private void txtIvaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIvaFocusLost
        // TODO add your handling code here:
        this.calculo();
    }//GEN-LAST:event_txtIvaFocusLost

    private void cbFinanciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbFinanciaItemStateChanged
        // TODO add your handling code here:
        if (this.cbFinancia.getSelectedItem() == "Credito") {
            this.getComboCredito();
            this.cbCredito.setEnabled(true);
            this.cbEjecutivo.setEnabled(true);
        } else {
            this.cbCredito.setEnabled(false);
            this.cbEjecutivo.setEnabled(false);
        }
    }//GEN-LAST:event_cbFinanciaItemStateChanged

    private void cbCreditoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCreditoItemStateChanged
        // TODO add your handling code here:
        String idFinanciamiento=this.cbCredito.getSelectedItem().toString();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM financiamiento WHERE nombre='"+idFinanciamiento+"'");
            while (rs.next()) {
                datoFinanciamiento=Integer.parseInt(rs.getObject("idFinanciamiento").toString());
            }
            rs.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        this.getComboEjecutivo(datoFinanciamiento);
    }//GEN-LAST:event_cbCreditoItemStateChanged

    private void btnsavecotizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsavecotizaActionPerformed
        // TODO add your handling code here:
        
        
        String rutCliente = this.txtrutCliente.getText();
        String idMarca = this.cbMarcas.getSelectedItem().toString();
        String idModelo = this.cbModelo.getSelectedItem().toString();
        String idFinanciamiento = this.cbCredito.getSelectedItem().toString();
        String idEjecutivo = this.cbEjecutivo.getSelectedItem().toString();
        String tipo = this.cbFinancia.getSelectedItem().toString();
        String neto = this.txtNeto.getText();
        String descuento = this.txtDesc.getText();
        String iva = this.txtIva.getText();
        String total = this.txtTotal.getText();
        String observaciones = this.txtobs.getText();
        String fecha = fs.fechahora();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM modelo WHERE nombre='"+idModelo+"'");
            while (rs.next()) {
                datoModelo=Integer.parseInt(rs.getObject("idModelo").toString());
            }
            rs.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
        
        //id combo ejecutivo
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ejecutivo WHERE nombre='"+idEjecutivo+"'");
            while (rs.next()) {
                datoEjecutivo=Integer.parseInt(rs.getObject("idEjecutivo").toString());
            }
            rs.close();
        } catch (SQLException se) {
            System.out.println(se);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }

        data.addCotizacion(rutCliente, datoMarca, datoModelo, datoFinanciamiento, datoEjecutivo, tipo, neto, descuento, iva, total, observaciones, fecha);
        this.updateTablaCotizacion(rutCliente);

        data.addHistoria(rutCliente, fecha, "Se crea cotizacion " + tipo + " vehiculo " + this.cbMarcas.getSelectedItem().toString() + " " + this.cbModelo.getSelectedItem().toString());
        this.updateTablaHistoria(rutCliente);

        data.updateClienteEstado(rutCliente, "Cotización");
        this.btnBuscarActionPerformed(evt);
    }//GEN-LAST:event_btnsavecotizaActionPerformed

    private void tablacotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablacotizacionMouseClicked
        // TODO add your handling code here:
        fila = tablacotizacion.rowAtPoint(evt.getPoint());
        if (fila > -1) {
            idcotizacion = String.valueOf(tablacotizacion.getValueAt(fila, 0));
            
            String getComboMarca=String.valueOf(tablacotizacion.getValueAt(fila, 2));
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM marca WHERE idMarca='"+getComboMarca+"'");
            while (rs.next()) {
                this.getComboMarcas();
                this.cbMarcas.setSelectedItem(rs.getObject("nombre").toString());
            }
            rs.close();
            } catch (SQLException se) {
                System.out.println(se);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            //this.cbMarcas.setSelectedIndex(Integer.parseInt(String.valueOf(tablacotizacion.getValueAt(fila, 2))));
            
            String getComboModelo=String.valueOf(tablacotizacion.getValueAt(fila, 3));
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM modelo WHERE idModelo='"+getComboModelo+"'");
            while (rs.next()) {
                this.cbModelo.setSelectedItem(rs.getObject("nombre").toString());
            }
            rs.close();
            } catch (SQLException se) {
                System.out.println(se);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            //}this.cbModelo.setSelectedIndex(Integer.parseInt(String.valueOf(tablacotizacion.getValueAt(fila, 3))));
            
            this.txtNeto.setText(String.valueOf(tablacotizacion.getValueAt(fila, 7)));
            this.txtDesc.setText(String.valueOf(tablacotizacion.getValueAt(fila, 8)));
            this.txtIva.setText(String.valueOf(tablacotizacion.getValueAt(fila, 9)));
            this.txtTotal.setText(String.valueOf(tablacotizacion.getValueAt(fila, 10)));
            this.cbFinancia.setSelectedItem(String.valueOf(tablacotizacion.getValueAt(fila, 6)));
            
            String getComboCredito=String.valueOf(tablacotizacion.getValueAt(fila, 4));
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM financiamiento WHERE idFinanciamiento='"+getComboCredito+"'");
            while (rs.next()) {
                //this.getComboCredito();
                this.cbCredito.setSelectedItem(rs.getObject("nombre").toString());
            }
            rs.close();
            } catch (SQLException se) {
                System.out.println(se);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            //this.cbCredito.setSelectedIndex(Integer.parseInt(String.valueOf(tablacotizacion.getValueAt(fila, 4))));
            
            String getComboEjecutivo=String.valueOf(tablacotizacion.getValueAt(fila, 5));
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/BDSis", "root", "");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ejecutivo WHERE idEjecutivo='"+getComboEjecutivo+"'");
            while (rs.next()) {
                this.cbEjecutivo.setSelectedItem(rs.getObject("nombre").toString());
            }
            rs.close();
            } catch (SQLException se) {
                System.out.println(se);
            } catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            //this.cbEjecutivo.setSelectedIndex(Integer.parseInt(String.valueOf(tablacotizacion.getValueAt(fila, 5))));
            
            this.txtobs.setText(String.valueOf(tablacotizacion.getValueAt(fila, 11)));
            System.out.println("IdCotizacion Selected= " + idcotizacion);
        }
    }//GEN-LAST:event_tablacotizacionMouseClicked

    private void btnmodifcotizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodifcotizaActionPerformed
        // TODO add your handling code here:
        String idCotizacion = this.idcotizacion;
        String rutCliente = this.txtrutCliente.getText();
        int idMarca = this.cbMarcas.getSelectedIndex();
        int idModelo = this.cbModelo.getSelectedIndex();
        int idFinanciamiento = this.cbCredito.getSelectedIndex();
        int idEjecutivo = this.cbEjecutivo.getSelectedIndex();
        String tipo = this.cbFinancia.getSelectedItem().toString();
        String neto = this.txtNeto.getText();
        String descuento = this.txtDesc.getText();
        String iva = this.txtIva.getText();
        String total = this.txtTotal.getText();
        String observaciones = this.txtobs.getText();

        data.updateCotizacion(idCotizacion, idMarca, idModelo, idFinanciamiento, idEjecutivo, tipo, neto, descuento, iva, total, observaciones, rutCliente);
        this.updateTablaCotizacion(rutCliente);
    }//GEN-LAST:event_btnmodifcotizaActionPerformed

    private void btndelcotizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndelcotizaActionPerformed
        // TODO add your handling code here:
        String idCotizacion = this.idcotizacion;
        String rutCliente = this.txtrutCliente.getText();

        data.delCotizacion(idCotizacion);
        this.updateTablaCotizacion(rutCliente);
    }//GEN-LAST:event_btndelcotizaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String rutCliente = this.txtrutCliente.getText();
        String nombre = this.txtnombre.getText();
        String paterno = this.txtpaterno.getText();
        String materno = this.txtmaterno.getText();
        String tipo = this.cbTipoAgendamiento.getSelectedItem().toString();
        String observacion = this.txtobservacionAgenda.getText();

        String hora = this.cbHora.getSelectedItem().toString();
        String minutos = this.cbMinutos.getSelectedItem().toString();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date fecha = this.txtfechaAgenda.getDate();
        String fechahora = formato.format(fecha) + " " + hora + ":" + minutos + ":00";

        String fechaoperacion = fs.fechahora();

        data.addAgendamiento(rutCliente, nombre, paterno, materno, tipo, observacion, fechahora);
        data.addHistoria(rutCliente, fechaoperacion, "Agenda: " + tipo + " el " + fechahora + " con Obs: " + observacion);

        this.updateTablaAgenda(rutCliente);
        this.updateTablaHistoria(rutCliente);
    }//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btndelcotiza;
    private javax.swing.JButton btnmodifcotiza;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnsavecotiza;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox cbCredito;
    private javax.swing.JComboBox cbEjecutivo;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbFinancia;
    private javax.swing.JComboBox cbHora;
    private javax.swing.JComboBox cbMarcas;
    private javax.swing.JComboBox cbMinutos;
    private javax.swing.JComboBox cbModelo;
    private javax.swing.JComboBox cbTipoAgendamiento;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JTabbedPane tab;
    private javax.swing.JPanel tabAgenda;
    private javax.swing.JPanel tabCotiazcion;
    private javax.swing.JTable tablaAgenda;
    private javax.swing.JTable tablacotizacion;
    private javax.swing.JTable tablahistoria;
    private javax.swing.JFormattedTextField txtDesc;
    private javax.swing.JFormattedTextField txtIva;
    private javax.swing.JFormattedTextField txtNeto;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtciudad;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtemail;
    private com.toedter.calendar.JDateChooser txtfechaAgenda;
    private javax.swing.JTextField txtmaterno;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextArea txtobs;
    private javax.swing.JTextField txtobservacionAgenda;
    private javax.swing.JTextField txtpaterno;
    private javax.swing.JFormattedTextField txtrutCliente;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}