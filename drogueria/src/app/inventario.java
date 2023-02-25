package app;

import com.mysql.cj.protocol.Resultset;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;

public class inventario extends javax.swing.JFrame {

    public static final String URL = "jdbc:mysql://localhost:3306/products";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "misionTic2022";

    PreparedStatement ps;
    ResultSet rs;

    public static Connection getConection() {
        Connection con = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    private void limpiarCajas() {

        txtcodproducto.setText(null);
        txtCod_bar.setText(null);
        txtArticulo.setText(null);
        txtPresentacion.setText(null);
        txtCant_producto.setText(null);
        txtEmpaque.setText(null);
        txtMarca.setText(null);
        txtVr_base.setText(null);
        txtVr_und.setText(null);
        txtVr_venta.setText(null);

    }

    public inventario() {
        initComponents();
        txtcodproducto.setVisible(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaVentas = new javax.swing.JInternalFrame();
        jTextField1 = new javax.swing.JTextField();
        ventanaPrincipal = new javax.swing.JPanel();
        txtcodproducto = new javax.swing.JTextField();
        txtArticulo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCod_bar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        txtPresentacion = new javax.swing.JTextField();
        lblPresentacion = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtCant_producto = new javax.swing.JTextField();
        txtEmpaque = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        txtVr_base = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtVr_und = new javax.swing.JTextField();
        txtVr_venta = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenulista = new javax.swing.JMenuItem();

        ventanaVentas.setVisible(true);

        jTextField1.setText("ventas");

        javax.swing.GroupLayout ventanaVentasLayout = new javax.swing.GroupLayout(ventanaVentas.getContentPane());
        ventanaVentas.getContentPane().setLayout(ventanaVentasLayout);
        ventanaVentasLayout.setHorizontalGroup(
            ventanaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaVentasLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(306, Short.MAX_VALUE))
        );
        ventanaVentasLayout.setVerticalGroup(
            ventanaVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaVentasLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txtcodproducto.setEnabled(false);
        txtcodproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodproductoActionPerformed(evt);
            }
        });

        txtArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArticuloActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo Barras");

        txtCod_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_barActionPerformed(evt);
            }
        });

        jLabel2.setText("Articulo");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtPresentacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPresentacionActionPerformed(evt);
            }
        });

        lblPresentacion.setText("Presentacion");

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtCant_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCant_productoActionPerformed(evt);
            }
        });

        txtEmpaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpaqueActionPerformed(evt);
            }
        });

        jLabel4.setText("Cantidad Producto");

        jLabel5.setText("Empaque");

        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        txtVr_base.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVr_baseActionPerformed(evt);
            }
        });

        jLabel6.setText("Marca");

        jLabel7.setText("Valor Base");

        jLabel8.setText("Valor Unidad");

        jLabel9.setText("Valor Venta");

        txtVr_und.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVr_undActionPerformed(evt);
            }
        });

        txtVr_venta.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        txtVr_venta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVr_venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVr_ventaActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1182, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout ventanaPrincipalLayout = new javax.swing.GroupLayout(ventanaPrincipal);
        ventanaPrincipal.setLayout(ventanaPrincipalLayout);
        ventanaPrincipalLayout.setHorizontalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addGap(115, 115, 115)
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmpaque, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtVr_und, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(183, 183, 183)
                                        .addComponent(btnLimpiar)))
                                .addGap(72, 72, 72))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnGuardar)
                                .addGap(451, 451, 451)
                                .addComponent(btnModificar)))
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtVr_base, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(btnEliminar)
                                .addGap(14, 14, 14)))
                        .addGap(20, 20, 20))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(44, 44, 44)))
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addComponent(txtCod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscar)
                                        .addGap(128, 128, 128)
                                        .addComponent(txtcodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                    .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                            .addComponent(lblPresentacion)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(178, 178, 178)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(29, 29, 29)
                                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                            .addGap(60, 60, 60)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtCant_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtVr_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(22, Short.MAX_VALUE))))
        );
        ventanaPrincipalLayout.setVerticalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtCod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)
                        .addComponent(jLabel7)
                        .addComponent(txtVr_base, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtcodproducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPresentacion)
                            .addComponent(txtPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCant_producto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtVr_und, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLimpiar)))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVr_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtEmpaque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Productos");

        jMenulista.setText("Lista");
        jMenulista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenulistaActionPerformed(evt);
            }
        });
        jMenu2.add(jMenulista);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtCod_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod_barActionPerformed

    private void txtArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtArticuloActionPerformed

    private void txtPresentacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPresentacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPresentacionActionPerformed

    private void txtCant_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCant_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCant_productoActionPerformed

    private void txtEmpaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpaqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpaqueActionPerformed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtVr_baseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVr_baseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVr_baseActionPerformed

    private void txtVr_undActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVr_undActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVr_undActionPerformed

    private void txtVr_ventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVr_ventaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVr_ventaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Connection con = null;
           
        if (existeproducto(txtCod_bar.getText()) == 0) {
            
            try {
                con = getConection();
                ps = con.prepareStatement("INSERT INTO products(cod_bar,articulo,presentacion,cant_producto,empaque,marca,vr_base,vr_unidad,vr_venta)VALUES(?,?,?,?,?,?,?,?,?)");
                ps.setString(1, txtCod_bar.getText());
                ps.setString(2, txtArticulo.getText());
                ps.setString(3, txtPresentacion.getText());
                ps.setString(4, txtCant_producto.getText());
                ps.setString(5, txtEmpaque.getText());
                ps.setString(6, txtMarca.getText());
                ps.setString(7, txtVr_base.getText());
                ps.setString(8, txtVr_und.getText());
                ps.setString(9, txtVr_venta.getText());

                int res = ps.executeUpdate();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro guardado");
                    limpiarCajas();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar Registro");
                    limpiarCajas();
                }
                con.close();
            } catch (HeadlessException | SQLException e) {

                System.err.println(e);
            }
        }else{
        JOptionPane.showMessageDialog(null, "El producto ya existe");
       limpiarCajas();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtcodproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodproductoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT * FROM products WHERE cod_bar = ?");
            ps.setString(1, txtCod_bar.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                txtcodproducto.setText(rs.getString("codproducto"));
                txtCod_bar.setText(rs.getString("cod_bar"));
                txtArticulo.setText(rs.getString("articulo"));
                txtPresentacion.setText(rs.getString("presentacion"));
                txtCant_producto.setText(rs.getString("cant_producto"));
                txtEmpaque.setText(rs.getString("empaque"));
                txtMarca.setText(rs.getString("marca"));
                txtVr_base.setText(rs.getString("vr_base"));
                txtVr_und.setText(rs.getString("vr_unidad"));
                txtVr_venta.setText(rs.getString("vr_venta"));
            } else {
                JOptionPane.showMessageDialog(null, "Codigo de barras no existe");
            }

        } catch (HeadlessException | SQLException e) {

            System.err.println(e);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("UPDATE products SET cod_bar=?, articulo=?, presentacion=?, cant_producto=?, empaque=?, marca=?, vr_base=?, vr_unidad=?, vr_venta=? WHERE codproducto=?");
            ps.setString(1, txtCod_bar.getText());
            ps.setString(2, txtArticulo.getText());
            ps.setString(3, txtPresentacion.getText());
            ps.setString(4, txtCant_producto.getText());
            ps.setString(5, txtEmpaque.getText());
            ps.setString(6, txtMarca.getText());
            ps.setString(7, txtVr_base.getText());
            ps.setString(8, txtVr_und.getText());
            ps.setString(9, txtVr_venta.getText());
            ps.setString(10, txtcodproducto.getText());

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiarCajas();
            }
            con.close();
        } catch (HeadlessException | SQLException e) {

            System.err.println(e);
        }


    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("DELETE FROM products WHERE codproducto=?");
            ps.setInt(1, Integer.parseInt(txtcodproducto.getText()));

            int res = ps.executeUpdate();

            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiarCajas();
            }
            con.close();
        } catch (HeadlessException | SQLException e) {

            System.err.println(e);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jMenulistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenulistaActionPerformed
    ListarProductos listarproductos = new ListarProductos();
        jDesktopPane1.add(listarproductos);
        listarproductos.setVisible(true);
        listarproductos.setMaximizable(true);
          
       
    }//GEN-LAST:event_jMenulistaActionPerformed

    private void agregarVentanaPanel(JInternalFrame ventana) {
        if (ventanaPrincipal.getComponentCount() > 0) {
            ventanaPrincipal.removeAll();
        }
        ventanaPrincipal.add(ventana);
    }

    public int existeproducto(String codibar) {
         Connection con = null;
             
        String sql="SELECT count(codproducto) FROM products WHERE cod_bar = ? ";
       
        try {
            con = getConection();
            ps = con.prepareStatement( sql );
            ps.setString(1, codibar);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
            return 1;
            
        } catch (HeadlessException | SQLException e) {

            System.err.println(e);
        
            return 1;
        }

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
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenulista;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblPresentacion;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtCant_producto;
    private javax.swing.JTextField txtCod_bar;
    private javax.swing.JTextField txtEmpaque;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPresentacion;
    private javax.swing.JTextField txtVr_base;
    private javax.swing.JTextField txtVr_und;
    private javax.swing.JTextField txtVr_venta;
    private javax.swing.JTextField txtcodproducto;
    private javax.swing.JPanel ventanaPrincipal;
    private javax.swing.JInternalFrame ventanaVentas;
    // End of variables declaration//GEN-END:variables

   
}
