/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventario;

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
public class ModificarUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form ModificarProducto
     */
    public ModificarUsuario() {
        initComponents();
        setResizable(false);
        txt_buscar.requestFocus();
        DesactivarCampos();
    }

    public void DesactivarCampos() {
        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        boxEstado.setEnabled(false);
        txtMunicipio.setEnabled(false);
        txtCalle.setEnabled(false);
        txtNumero.setEnabled(false);
        txtUsuario.setEnabled(false);
        btn_guardar.setEnabled(false);
        btncancelar.setEnabled(false);
    }

    //metodo donde crea la tabla de productos 
    DefaultTableModel m;

    public void TablaUsuario(String val) {//Realiza la consulta de los productos que tenemos en la base de datos
        String titles[] = {"Id", "Nombre", "Estado", "Municipio", "Calle", "Numero", "Usuario"};
        String reg[] = new String[7];
        String sentence = "";
        m = new DefaultTableModel(null, titles);

        Connection con;
        con = Conexion.Conexion.GetConnection();
        sentence = "CALL SP_UsuariosSel('%" + val + "%')";

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                reg[0] = rs.getString("us.idUsuarios");
                reg[1] = rs.getString("us.Nombre");
                reg[2] = rs.getString("us.Estado");
                reg[3] = rs.getString("us.Municipio");
                reg[4] = rs.getString("us.Calle");
                reg[5] = rs.getString("us.NumeroCasa");
                reg[6] = rs.getString("ua.Usuario");
                
                m.addRow(reg);//agrega el registro a la tabla
            }
            TablaSqlUsuario.setModel(m);//asigna a la tabla el modelo creado

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
        String id = "", nom = "", est = "", mun = "", cal = "", num = "", usu = "";
        sentence = "CALL SP_UsuariosSel('%" + val + "%')";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sentence);
            while (rs.next()) {
                id = rs.getString("us.idUsuarios");
                nom = rs.getString("us.Nombre");
                est = rs.getString("us.Estado");
                mun = rs.getString("us.Municipio");
                cal = rs.getString("us.Calle");
                num = rs.getString("us.NumeroCasa");
                usu = rs.getString("ua.Usuario");

                txtId.setText(id);
                txtNombre.setText(nom);
                boxEstado.setSelectedItem(est);
                txtMunicipio.setText(mun);
                txtCalle.setText(cal);
                txtNumero.setText(num);
                txtUsuario.setText(usu);

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
            filasel = TablaSqlUsuario.getSelectedRow();
            if (filasel == -1) {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila", "Advertencia", JOptionPane.WARNING_MESSAGE);
                txt_buscar.requestFocus();
            } else {
                //txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                boxEstado.setEnabled(true);
                txtMunicipio.setEnabled(true);
                txtCalle.setEnabled(true);
                txtNumero.setEnabled(true);
                txtUsuario.setEnabled(true);
                btn_guardar.setEnabled(true);
                btncancelar.setEnabled(true);

                txt_buscar.setText("");
                TablaSqlUsuario.setVisible(false);
                action = "Modificar";
                m = (DefaultTableModel) TablaSqlUsuario.getModel();
                id = (String) m.getValueAt(filasel, 0);
                //System.out.println("Id mandado para modificar"+id);
                Buscar_editar(id);
                TablaUsuario("");
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
        TablaSqlUsuario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        btnok = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        boxEstado = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Modicar Usuario");
        setToolTipText("");
        setPreferredSize(new java.awt.Dimension(1127, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, new java.awt.Color(0, 0, 0)), "Seleccionar usuario para modificar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Subheading", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N
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

        TablaSqlUsuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(153, 153, 153), null, new java.awt.Color(102, 102, 102)));
        TablaSqlUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TablaSqlUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Estado", "Municipio", "Calle", "Numero", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaSqlUsuario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        TablaSqlUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        TablaSqlUsuario.setOpaque(false);
        TablaSqlUsuario.getTableHeader().setReorderingAllowed(false);
        TablaSqlUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaSqlUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaSqlUsuario);

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
        jPanel3.add(btnok, new org.netbeans.lib.awtextra.AbsoluteConstraints(932, 193, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 102, 102), null, new java.awt.Color(0, 0, 0)), "Modificar datos del usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 14), new java.awt.Color(0, 0, 153))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 60, 70, 30));

        txtNombre.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 63, 280, -1));

        jLabel2.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel2.setText("Municipio:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 80, 40));

        txtMunicipio.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtMunicipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 128, 240, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel3.setText("Calle:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 50, 40));

        txtCalle.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtCalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 159, 240, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inventario/INDUSTRIAL SYSTEM USERS.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 0, 12))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos20x20/20x20-Guardar.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.setToolTipText("Guardar Modificaciones");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jPanel2.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 19, 98, -1));

        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos20x20/20X20-CANCEL.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.setToolTipText("Cancelar | Limpiar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 19, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel4.setText("Estado:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 60, 30));

        boxEstado.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        boxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche", "Chiapas", "Chihuahua", "Coahuila", "Colima", "Distrito Federal", "Durango", "Estado de México", "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "Michoacán", "Morelos", "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí", "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" }));
        jPanel1.add(boxEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 94, 240, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel6.setText("Id:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 35, -1, -1));

        txtId.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 32, 150, -1));

        jLabel7.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel7.setText("Numero:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 70, 40));

        txtNumero.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 110, -1));

        jLabel8.setFont(new java.awt.Font("Sitka Subheading", 1, 16)); // NOI18N
        jLabel8.setText("Usuario:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 220, 70, 40));

        txtUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 240, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 225, 790, 320));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 58, 1091, 570));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Inventario/BannerUsuario 1111x52.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void Limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        boxEstado.setSelectedIndex(0);
        txtMunicipio.setText("");
        txtCalle.setText("");
        txtNumero.setText("");
        txtUsuario.setText("");
        txt_buscar.requestFocus();

        txtId.setEnabled(false);
        txtNombre.setEnabled(false);
        boxEstado.setEnabled(false);
        txtMunicipio.setEnabled(false);
        txtCalle.setEnabled(false);
        txtNumero.setEnabled(false);
        txtUsuario.setEnabled(false);
        btn_guardar.setEnabled(false);
        btncancelar.setEnabled(false);
    }
    private void txt_buscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscarKeyReleased
        //actualiza la tabla conforme a la letra que teclea
        if (txt_buscar.getText().trim().length() >= 1) {
            String filtro = txt_buscar.getText();
            TablaUsuario(filtro);

            TablaSqlUsuario.setVisible(true);

        } else {
            TablaSqlUsuario.setVisible(false);
        }
    }//GEN-LAST:event_txt_buscarKeyReleased

    private void TablaSqlUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaSqlUsuarioMouseClicked
        if (evt.getClickCount() == 2) {
            seleccionarfilamodificar();
        }
    }//GEN-LAST:event_TablaSqlUsuarioMouseClicked

    private void btnokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnokActionPerformed
        seleccionarfilamodificar();
    }//GEN-LAST:event_btnokActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        //guarda los datos que se han modificado en los campos
        Connection con;
        con = Conexion.Conexion.GetConnection();
        String sentence = "";
        String msj = "";
        String cod, nom, est, mun, cal,  usu;
        
        cod = txtId.getText();
        nom = txtNombre.getText();
        Object combo = boxEstado.getSelectedItem();
        est = String.valueOf(combo);
        mun = txtMunicipio.getText();
        cal = txtCalle.getText();
        int  num = Integer.parseInt(txtNumero.getText());
        usu = txtUsuario.getText();

        //si los datos son diferentes de vacios
        if (!cod.isEmpty() && !nom.isEmpty() && !est.isEmpty() && !mun.isEmpty() && !cal.isEmpty()&& !usu.isEmpty()) {

            int confirmado = JOptionPane.showConfirmDialog(null, "¿Esta seguro de modificar el usuario?", "Confirmación", JOptionPane.YES_OPTION);
            if (JOptionPane.YES_OPTION == confirmado) {

                sentence = "CALL SP_UsuarioUpd(" + cod + ",'" + nom + "','" + est + "','" + mun + "','" + cal + "'," + num + ",'" + usu + "')";
                getToolkit().beep();
                JOptionPane.showMessageDialog(null, "Usuario modificado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

                try {
                    PreparedStatement pst = con.prepareStatement(sentence);
                    pst.executeUpdate();

                    txtId.setText("");
                    txtNombre.setText("");
                    boxEstado.setSelectedIndex(0);
                    txtMunicipio.setText("");
                    txtCalle.setText("");
                    txtNumero.setText("");
                    txtUsuario.setText("");

                    TablaUsuario("");
                    txtId.setEnabled(false);
                    txtNombre.setEnabled(false);
                    boxEstado.setEnabled(false);
                    txtMunicipio.setEnabled(false);
                    txtCalle.setEnabled(false);
                    txtNumero.setEnabled(false);
                    txtUsuario.setEnabled(false);

                    btn_guardar.setEnabled(false);
                    btncancelar.setEnabled(false);

                    TablaSqlUsuario.setVisible(false);
                    txt_buscar.setText("");
                    txt_buscar.requestFocus();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                txt_buscar.setText("");
                TablaUsuario("");
                JOptionPane.showMessageDialog(null, "Cancelado correctamente", "Información", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No dejar vacio ningun campo", "Advertencia", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_btn_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaSqlUsuario;
    private javax.swing.JComboBox boxEstado;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnok;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txt_buscar;
    // End of variables declaration//GEN-END:variables
}
