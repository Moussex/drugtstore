
package app;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Misaky
 */
public class venta1 extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();

    int cantfilas;
    double pre = 0;

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
            //   JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return con;
    }

    //Metodo Fecha - hora
    public static String fechaActual() {

        Date fecha = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY - HH:mm:ss");
        String hora =formatoFecha.format(fecha);
        return (hora);
    }

    /**
     * Creates new form venta1
     */
    //CONSTRUCTOR DEL FORMULARIO
    public venta1() {
        initComponents();
        //establecedor de la fecha
        lblFecha.setText(fechaActual());
        //creacion arreglo
        String[] titulo = new String[]{"Codigo de Barras", "Articulo", "Precio"};
        //creacion tabla para facturacion productos
        dtm.setColumnIdentifiers(titulo);
        tblDatos.setModel(dtm);
    }

    void anexar() {
        dtm.addRow(new Object[]{
            txtCod_bar.getText(), lbl_Articulo.getText(), lblVr_venta.getText()
        });
        //sumatoria de valores de los productos
        double sum = 0;
        cantfilas = dtm.getRowCount();
        for (int i = 0; i < cantfilas; i++) {
            pre = Double.parseDouble((String) dtm.getValueAt(i, 2));
            sum = sum + pre;
            lblTotal.setText(String.valueOf(sum));
        }
    }

    void eliminar() {
        int fila = tblDatos.getSelectedRow();
        dtm.removeRow(fila);

        double sum = 0;
        cantfilas = dtm.getRowCount();
        for (int i = 0; i < cantfilas; i++) {
            pre = Double.parseDouble((String) dtm.getValueAt(i, 2));
            sum = sum + pre;
            lblTotal.setText(String.valueOf(sum));
        }

    }

    void limpiarTabla() {
        double sum = 0;
        int filas = dtm.getRowCount();
        for (int i = 0; i < filas; i++) {
            dtm.removeRow(0);
        }
        lblTotal.setText(String.valueOf(sum));
    }

    void totalizar() {
         lblFecha.setText(fechaActual());
        int numfact = 0;
        cantfilas = dtm.getRowCount();
        //creacion arreglo bidimensional para cargue de datos venta    
        String listaproductos[][] = new String[cantfilas][3];
        for (int i = 0; i < cantfilas; i++) {
            for (int j = 0; j < 3; j++) {
                listaproductos[i][j] = (String) dtm.getValueAt(i, j);
            }
            numfact = numfact++;
        }

        //conexion para cargue de venta a la tabla ventas      
        Connection con = null;
        try {
            for (int i = 0; i < cantfilas; i++) {
                con = getConection();
                ps = con.prepareStatement("INSERT INTO ventas(codigodebarras,articulo,precio,factura,fecha)VALUES(?,?,?,?,?)");
                ps.setString(1, listaproductos[i][0]);
                ps.setString(2, listaproductos[i][1]);
                ps.setString(3, listaproductos[i][2]);
                ps.setString(4, lblFactura.getText());
                ps.setString(5, lblFecha.getText());
                ps.executeUpdate();
                con.close();

            }
            JOptionPane.showMessageDialog(null, "Venta guardada");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay nada que guardar");
            System.err.println(e);
        }

    }
    
    void devolucion(){
        double dev=0;
        double rest=0;
        double dif=0;
        dev = Double.parseDouble(lblTotal.getText());
        rest = Double.parseDouble(txtPagacon.getText());
        dif= rest-dev;
        lblVueltas.setText(String.valueOf(dif));
    }
    
    void buscar(){
         Connection con = null;

        try {
            con = getConection();
            ps = con.prepareStatement("SELECT * FROM products WHERE cod_bar = ?");
            ps.setString(1, txtCod_bar.getText());
            rs = ps.executeQuery();

            if (rs.next()) {
                txtcodproducto.setText(rs.getString("codproducto"));
                txtCod_bar.setText(rs.getString("cod_bar"));
                lbl_Articulo.setText(rs.getString("articulo"));
                lblVr_venta.setText(rs.getString("vr_venta"));
            } else {
                JOptionPane.showMessageDialog(null, "Codigo de barras no existe");
            }

        } catch (HeadlessException | SQLException e) {

            System.err.println(e);
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

        jLabel4 = new javax.swing.JLabel();
        ventanaPrincipal = new javax.swing.JPanel();
        txtcodproducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCod_bar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDatos = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jbtnAnexar = new javax.swing.JButton();
        jbtnEliminar = new javax.swing.JButton();
        jbtnlimpiarTabla = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbl_Articulo = new javax.swing.JLabel();
        btnSeleccionar = new javax.swing.JButton();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        lblVr_venta = new javax.swing.JLabel();
        txtPagacon = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblVueltas = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblFactura = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnArqueo = new javax.swing.JButton();
        lblArqueo = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtcodproducto.setEnabled(false);
        txtcodproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodproductoActionPerformed(evt);
            }
        });

        jLabel1.setText("Codigo Barras");

        txtCod_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCod_barActionPerformed(evt);
            }
        });
        txtCod_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCod_barKeyPressed(evt);
            }
        });

        jLabel2.setText("Articulo");

        jLabel9.setText("Valor Venta");

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo de Barras", "Articulo", "Precio"
            }
        ));
        jScrollPane1.setViewportView(tblDatos);

        lblTotal.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTotal.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setText("TOTAL:");

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblFechaComponentAdded(evt);
            }
        });

        jbtnAnexar.setText("Anexar");
        jbtnAnexar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAnexarActionPerformed(evt);
            }
        });

        jbtnEliminar.setText("Eliminar");
        jbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminarActionPerformed(evt);
            }
        });

        jbtnlimpiarTabla.setText("Limpiar Tabla");
        jbtnlimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnlimpiarTablaActionPerformed(evt);
            }
        });

        jLabel6.setText("Fecha y Hora:");

        lbl_Articulo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 296, Short.MAX_VALUE)
        );

        lblVr_venta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtPagacon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagaconActionPerformed(evt);
            }
        });
        txtPagacon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagaconKeyPressed(evt);
            }
        });

        jLabel7.setText("Paga con:");

        jLabel8.setText("Vueltas:");

        lblVueltas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout ventanaPrincipalLayout = new javax.swing.GroupLayout(ventanaPrincipal);
        ventanaPrincipal.setLayout(ventanaPrincipalLayout);
        ventanaPrincipalLayout.setHorizontalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addComponent(txtcodproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(26, 26, 26)))
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCod_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(lbl_Articulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblVr_venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ventanaPrincipalLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(btnSeleccionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                    .addGap(17, 17, 17)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDesktopPane2)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnEliminar)
                                    .addComponent(jbtnAnexar)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbtnlimpiarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addGap(53, 53, 53)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPagacon, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVueltas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 19, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ventanaPrincipalLayout.setVerticalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(btnSeleccionar)
                    .addComponent(lbl_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLimpiar)
                    .addComponent(jLabel9)
                    .addComponent(lblVr_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcodproducto)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jbtnAnexar)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnEliminar)
                            .addComponent(txtPagacon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnlimpiarTabla)
                            .addComponent(jLabel8))
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblVueltas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 15, Short.MAX_VALUE)))
                .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel5.setText("Factura N°:");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnArqueo.setText("Arqueo");
        btnArqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArqueoActionPerformed(evt);
            }
        });

        lblArqueo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblArqueo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnArqueo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArqueo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(101, 101, 101)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblArqueo, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)))
                    .addComponent(jLabel5)
                    .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArqueo))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        totalizar();
        limpiarTabla();
        limpiarCajas();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Seleccionar seleccionar = new Seleccionar();
        jDesktopPane2.add(seleccionar);
        seleccionar.setVisible(true);
        seleccionar.setMaximizable(true);
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void jbtnlimpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnlimpiarTablaActionPerformed
        limpiarTabla();// TODO add your handling code here:
    }//GEN-LAST:event_jbtnlimpiarTablaActionPerformed

    private void jbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminarActionPerformed
        eliminar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnEliminarActionPerformed

    private void jbtnAnexarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAnexarActionPerformed
        anexar();
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAnexarActionPerformed

    private void lblFechaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblFechaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblFechaComponentAdded

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       buscar();

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void txtCod_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCod_barActionPerformed
     buscar();        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod_barActionPerformed

    private void txtcodproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodproductoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiarCajas();
        limpiarTabla();
        lblArqueo.setText(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnArqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArqueoActionPerformed
        // TODO add your handling code here:
        Arqueo arqueo = new Arqueo();
        jDesktopPane2.add(arqueo);
        arqueo.setVisible(true);
        arqueo.setMaximizable(true);

    }//GEN-LAST:event_btnArqueoActionPerformed

    private void txtPagaconKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagaconKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagaconKeyPressed

    private void txtPagaconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagaconActionPerformed
       devolucion(); // TODO add your handling code here:
    }//GEN-LAST:event_txtPagaconActionPerformed

    private void txtCod_barKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCod_barKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCod_barKeyPressed

    private void limpiarCajas() {

        txtcodproducto.setText(null);
        txtCod_bar.setText(null);
        lbl_Articulo.setText(null);
        lblVr_venta.setText(null);
        txtPagacon.setText(null);
        lblVueltas.setText(null);

    }

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
            java.util.logging.Logger.getLogger(venta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(venta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(venta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(venta1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new venta1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArqueo;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnAnexar;
    private javax.swing.JButton jbtnEliminar;
    private javax.swing.JButton jbtnlimpiarTabla;
    public static javax.swing.JLabel lblArqueo;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblTotal;
    public static javax.swing.JLabel lblVr_venta;
    private javax.swing.JLabel lblVueltas;
    public static javax.swing.JLabel lbl_Articulo;
    private javax.swing.JTable tblDatos;
    public static javax.swing.JTextField txtCod_bar;
    private javax.swing.JTextField txtPagacon;
    public static javax.swing.JTextField txtcodproducto;
    public static javax.swing.JPanel ventanaPrincipal;
    // End of variables declaration//GEN-END:variables

}
