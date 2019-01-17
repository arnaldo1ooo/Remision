/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing.Ivan
 */
public class ModificarEmpresa extends javax.swing.JInternalFrame {

    /**
     * Creates new form ModificarProducto
     */
    public ModificarEmpresa() {
        initComponents();
        setResizable(false);
        DesactivarCampos();
        txt_buscar.requestFocus();
    }

    public void DesactivarCampos() {
        txtCodigo.setEnabled(false);
        boxEmpresa.setEnabled(false);
        txtRfc.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtCalle.setEnabled(false);
        txtNumero.setEnabled(false);
        txtColonia.setEnabled(false);
        txtMunicipio.setEnabled(false);
        boxEstado.setEnabled(false);
        txtCp.setEnabled(false);
        btn_guardar.setEnabled(false);
        btncancelar.setEnabled(false);
    }

    //metodo donde crea la tabla de productos 
    DefaultTableModel m;

    public void TablaEmpresa(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Id", "Empresa", "RFC", "Telefono", "Email", "Calle", "Numero", "Colonia", "Municipio", "Estado", "C.P"};
        String reg[] = new String[11];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

        Connection con;
        con = Conexion.Conexion.GetConnection();
        sentence = "CALL SP_EmpresasSel('%" + val + "%')";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("idEmpresas");
                reg[1] = rs.getString("Nombre");
                reg[2] = rs.getString("RFC");
                reg[3] = rs.getString("Telefono");
                reg[4] = rs.getString("Email");
                reg[5] = rs.getString("Calle");
                reg[6] = rs.getString("Numero");
                reg[7] = rs.getString("Colonia");
                reg[8] = rs.getString("Municipio");
                reg[9] = rs.getString("EntidadFederativa");
                reg[10] = rs.getString("CP");
                m.addRow(reg);//agrega el registro a la tabla
            }
            TablaSqlEmpresa.setModel(m);//asigna a la tabla el modelo creado

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    //metodo donde obtiene los datos de la tabla para despues modificarlos
    String id_act = "";

    public void Buscar_editar(String val) {

        Connection con;
        con = Conexion.Conexion.GetConnection();
        String sentence = "";
        String idr = "", nom = "", rfc = "", tel = "", ema = "", cal = "", num = "", col = "", mun = "", est = "", cp = "";
        sentence = "CALL SP_ObtenerEmpresa(" + val + ")";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                idr = rs.getString("idEmpresas");
                nom = rs.getString("Nombre");
                rfc = rs.getString("RFC");
                tel = rs.getString("Telefono");
                ema = rs.getString("Email");
                cal = rs.getString("Calle");
                num = rs.getString("Numero");
                col = rs.getString("Colonia");
                mun = rs.getString("Municipio");
                est = rs.getString("EntidadFederativa");
                cp = rs.getString("CP");

                txtCodigo.setText(idr);
                boxEmpresa.addItem(nom);
                txtRfc.setText(rfc);
                txtTelefono.setText(tel);
                txtEmail.setText(ema);
                txtCalle.setText(cal);
                txtNumero.setText(num);
                txtColonia.setText(col);
                txtMunicipio.setText(mun);
                boxEstado.setSelectedItem(est);
                txtCp.setText(cp);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    //metodo donde llena los capos al seleccionar una fila
    String action = "Insertar";

    public void seleccionarfilamodificar() {
        int filasel;
        String id;
        try {
            filasel = TablaSqlEmpresa.getSelectedRow();
            if (filasel == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila","Advertencia",JOptionPane.WARNING_MESSAGE);
                txt_buscar.requestFocus();
            } else {

                //txtCodigo.setEnabled(true);
                //boxEmpresa.setEnabled(true);
                txtRfc.setEnabled(true);
                txtTelefono.setEnabled(true);
                txtEmail.setEnabled(true);
                txtCalle.setEnabled(true);
                txtNumero.setEnabled(true);
                txtColonia.setEnabled(true);
                txtMunicipio.setEnabled(true);
                boxEstado.setEnabled(true);
                txtCp.setEnabled(true);

                btn_guardar.setEnabled(true);
                btncancelar.setEnabled(true);

                txt_buscar.setText("");
                TablaSqlEmpresa.setVisible(false);
                action = "Modificar";
                m = (DefaultTableModel) TablaSqlEmpresa.getModel();
                id = (String) m.getValueAt(filasel, 0);

                Buscar_editar(id);
                TablaEmpresa("");
                txt_buscar.requestFocus();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
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

        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaSqlEmpresa = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        btnok = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRfc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtColonia = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        boxEmpresa = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        boxEstado = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        txtCp = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Modicar Empresa");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1127, 677));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, new java.awt.Color(0, 0, 0)), "Seleccionar empresa para modificar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Subheading", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 153, 204));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos40x40/40x40-BUSCAR.png"))); // NOI18N
        jLabel10.setText("  BUSCAR ");
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 102, 102)));
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 41, 140, 40));

        txt_buscar.setBackground(new java.awt.Color(0, 0, 0));
        txt_buscar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txt_buscar.setForeground(new java.awt.Color(0, 204, 204));
        txt_buscar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_buscar.setCaretColor(new java.awt.Color(0, 204, 204));
        txt_buscar.setDisabledTextColor(new java.awt.Color(0, 204, 204));
        txt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscarKeyReleased(evt);
            }
        });
        jPanel3.add(txt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 41, 570, 40));

        TablaSqlEmpresa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, new java.awt.Color(102, 102, 102)));
        TablaSqlEmpresa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TablaSqlEmpresa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Empresa", "RFC", "Telefono", "Email", "Calle", "Numero", "Colonia", "Municipio", "Estado", "C.P"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaSqlEmpresa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaSqlEmpresa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TablaSqlEmpresa.setOpaque(false);
        TablaSqlEmpresa.getTableHeader().setReorderingAllowed(false);
        TablaSqlEmpresa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaSqlEmpresaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaSqlEmpresa);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 97, 1059, 90));

        btnok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inventario/aceptar.png"))); // NOI18N
        btnok.setToolTipText("Eliminar Fila Seleccionada");
        btnok.setBorderPainted(false);
        btnok.setContentAreaFilled(false);
        btnok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnokActionPerformed(evt);
            }
        });
        jPanel3.add(btnok, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 200, 110, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, new java.awt.Color(0, 0, 0)), "Modificar datos de la empresa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel1.setText("Empresa:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 66, -1, -1));

        txtRfc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtRfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRfcKeyReleased(evt);
            }
        });
        jPanel1.add(txtRfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 94, 280, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel2.setText("Numero:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 221, 72, -1));

        txtNumero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 218, 81, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel3.setText("Colonia:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 221, -1, -1));

        txtColonia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtColonia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtColoniaKeyReleased(evt);
            }
        });
        jPanel1.add(txtColonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 218, 240, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inventario/empresa.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 32, 245, 287));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 0, 12))); // NOI18N

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos20x20/20x20-Guardar.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar Modificaciones");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos20x20/20X20-CANCEL.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Cancelar | Limpiar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btn_guardar)
                .addComponent(btncancelar))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(441, 280, -1, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel4.setText("RFC:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 97, -1, -1));

        boxEmpresa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(boxEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 63, 280, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel6.setText("Codigo:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 35, -1, -1));

        txtCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 32, 150, -1));

        jLabel7.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel7.setText("Telefono:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 128, -1, -1));

        txtTelefono.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        jPanel1.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 125, 280, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel8.setText("Email:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 159, -1, -1));

        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 156, 280, -1));

        jLabel9.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel9.setText("Calle:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 190, -1, -1));

        txtCalle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCalle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCalleKeyReleased(evt);
            }
        });
        jPanel1.add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 187, 280, -1));

        jLabel12.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel12.setText("Municipio:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 252, -1, -1));

        txtMunicipio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMunicipio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMunicipioKeyReleased(evt);
            }
        });
        jPanel1.add(txtMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 249, 281, -1));

        jLabel13.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel13.setText("Estado:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 281, -1, -1));

        boxEstado.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        boxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila", "Colima", "Distrito Federal", "Durango", "Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        boxEstado.setMinimumSize(new java.awt.Dimension(56, 25));
        jPanel1.add(boxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 281, 25));

        jLabel14.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel14.setText("C.P:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 314, -1, -1));

        txtCp.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtCp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCpKeyTyped(evt);
            }
        });
        jPanel1.add(txtCp, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 311, 81, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 193, -1, 370));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 58, 1091, 580));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inventario/bannermodempresa.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1111, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        //guarda los datos que se han modificado en los campos
        Connection con;
        con = Conexion.Conexion.GetConnection();
        String sentence = "";
        String msj = "";
        String cod, nom, rfc, tel, ema, cal, num, col, mun, est, cp;

        cod = txtCodigo.getText();
        Object combo = boxEmpresa.getSelectedItem();
        nom = String.valueOf(combo);
        rfc = txtRfc.getText();
        tel = txtTelefono.getText();
        ema = txtEmail.getText();
        cal = txtCalle.getText();
        num = txtNumero.getText();
        col = txtColonia.getText();
        mun = txtMunicipio.getText();
        Object combo2 = boxEstado.getSelectedItem();
        est = String.valueOf(combo2);
        cp = txtCp.getText();

        //si los datos son diferentes de vacios
        if (!cod.isEmpty() && !nom.isEmpty() && !rfc.isEmpty()
                && !tel.isEmpty() && !ema.isEmpty()
                && !cal.isEmpty() && !num.isEmpty()
                && !col.isEmpty() && !mun.isEmpty()
                && !est.isEmpty() && !cp.isEmpty()) {

            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar la empresa?", "Confirmación", JOptionPane.YES_OPTION);
            if (JOptionPane.YES_OPTION == confirmado) {

                sentence = "CALL SP_EmpresasUpd(" + cod + ",'" + nom + "',"
                        + "'" + rfc + "','" + tel + "','" + ema + "','" + cal
                        + "'," + num + ",'" + col + "','" + mun + "','" + est + "'," + cp + ")";
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Empresa modificada correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                try {
                    PreparedStatement pst = con.prepareStatement(sentence);
                    pst.executeUpdate();

                    txtCodigo.setText("");
                    boxEmpresa.removeAllItems();
                    txtRfc.setText("");
                    txtTelefono.setText("");
                    txtEmail.setText("");
                    txtCalle.setText("");
                    txtNumero.setText("");
                    txtColonia.setText("");
                    txtMunicipio.setText("");
                    boxEstado.setSelectedIndex(0);
                    txtCp.setText("");

                    TablaEmpresa("");
                    txtCodigo.setEnabled(false);
                    boxEmpresa.setEnabled(false);
                    txtRfc.setEnabled(false);
                    txtTelefono.setEnabled(false);
                    txtEmail.setEnabled(false);
                    txtCalle.setEnabled(false);
                    txtNumero.setEnabled(false);
                    txtColonia.setEnabled(false);
                    txtMunicipio.setEnabled(false);
                    boxEstado.setEnabled(false);
                    txtCp.setEnabled(false);

                    btn_guardar.setEnabled(false);
                    btncancelar.setEnabled(false);

                    TablaSqlEmpresa.setVisible(false);
                    txt_buscar.setText("");
                    txt_buscar.requestFocus();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                txt_buscar.setText("");
                TablaEmpresa("");
                JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Mensaje", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No dejar vacío ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed
    public void Limpiar() {
        txtCodigo.setText("");
        boxEmpresa.removeAllItems();
        txtRfc.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtCalle.setText("");
        txtNumero.setText("");
        txtColonia.setText("");
        txtMunicipio.setText("");
        boxEstado.setSelectedIndex(0);
        txtCp.setText("");

        txt_buscar.requestFocus();
        txtCodigo.setEnabled(false);
        boxEmpresa.setEnabled(false);
        txtRfc.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtEmail.setEnabled(false);
        txtCalle.setEnabled(false);
        txtNumero.setEnabled(false);
        txtColonia.setEnabled(false);
        txtMunicipio.setEnabled(false);
        boxEstado.setEnabled(false);
        txtCp.setEnabled(false);
    }
    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        //actualiza la tabla conforme a la letra que teclea
        if (txt_buscar.getText().trim().length() >= 1) {
            String filtro = txt_buscar.getText();
            TablaEmpresa(filtro);

            TablaSqlEmpresa.setVisible(true);

        } else {
            TablaSqlEmpresa.setVisible(false);
        }
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void TablaSqlEmpresaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaSqlEmpresaMouseClicked
        if (evt.getClickCount() == 2) {
            seleccionarfilamodificar();
        }
    }//GEN-LAST:event_TablaSqlEmpresaMouseClicked

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        seleccionarfilamodificar();
    }//GEN-LAST:event_btnokActionPerformed

    private void txtRfcKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRfcKeyReleased
        int longitud = txtRfc.getText().length();

        if (longitud > 20) {
            txtRfc.setText(txtRfc.getText().substring(0, 21));

        }
    }//GEN-LAST:event_txtRfcKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        int longitud = txtTelefono.getText().length();

        if (longitud > 15) {
            txtTelefono.setText(txtTelefono.getText().substring(0, 16));

        }
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        int longitud = txtEmail.getText().length();

        if (longitud > 45) {
            txtEmail.setText(txtEmail.getText().substring(0, 46));

        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCalleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCalleKeyReleased
        int longitud = txtCalle.getText().length();

        if (longitud > 60) {
            txtCalle.setText(txtCalle.getText().substring(0, 61));

        }
    }//GEN-LAST:event_txtCalleKeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        int longitud = txtNumero.getText().length();

        if (longitud > 9) {
            txtNumero.setText(txtNumero.getText().substring(0, 10));

        }
    }//GEN-LAST:event_txtNumeroKeyReleased

    private void txtColoniaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColoniaKeyReleased
        int longitud = txtColonia.getText().length();

        if (longitud > 45) {
            txtColonia.setText(txtColonia.getText().substring(0, 46));

        }
    }//GEN-LAST:event_txtColoniaKeyReleased

    private void txtMunicipioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMunicipioKeyReleased
        int longitud = txtMunicipio.getText().length();

        if (longitud > 45) {
            txtMunicipio.setText(txtMunicipio.getText().substring(0, 46));

        }
    }//GEN-LAST:event_txtMunicipioKeyReleased

    private void txtCpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpKeyReleased
        int longitud = txtCp.getText().length();

        if (longitud > 9) {
            txtCp.setText(txtCp.getText().substring(0, 10));

        }
    }//GEN-LAST:event_txtCpKeyReleased

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void txtCpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCpKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            Toolkit.getDefaultToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtCpKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaSqlEmpresa;
    private javax.swing.JComboBox boxEmpresa;
    private javax.swing.JComboBox boxEstado;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtCp;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRfc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
