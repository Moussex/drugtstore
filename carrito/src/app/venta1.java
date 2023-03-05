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
    int pre = 0;

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
        String hora = formatoFecha.format(fecha);
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
        //establece en la etiqueta Total la variable totalizadora de la venta 
        //para que pueda ser manipulada en caso que se guarde la venta sin anexar productos
        //ademas para indicar que en esa etiqueta va un valor
        int sum =0;
        lblTotal.setText(String.valueOf(sum));
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
        int sum = 0;
        cantfilas = dtm.getRowCount();
        for (int i = 0; i < cantfilas; i++) {
            pre = Integer.parseInt((String) dtm.getValueAt(i, 2));
            sum = sum + pre;
            lblTotal.setText(String.valueOf(sum));
            
        }
    }

    void eliminar() {
        
        int fila = tblDatos.getSelectedRow();
        dtm.removeRow(fila);

        int sum = 0;
        cantfilas = dtm.getRowCount();
        for (int i = 0; i < cantfilas; i++) {
            pre = Integer.parseInt((String) dtm.getValueAt(i, 2));
            sum = sum + pre;
            lblTotal.setText(String.valueOf(sum));
        }

    }

    void limpiarTabla() {
        int sum = 0;
        int filas = dtm.getRowCount();
        for (int i = 0; i < filas; i++) {
            dtm.removeRow(0);
        }
        lblTotal.setText(String.valueOf(sum));
    }

    void totalizar() {
        lblFecha.setText(fechaActual());
        int anu = 0;
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
                ps = con.prepareStatement("INSERT INTO ventas(codigodebarras,articulo,precio,formadepago,fecha)VALUES(?,?,?,?,?)");
                ps.setString(1, listaproductos[i][0]);
                ps.setString(2, listaproductos[i][1]);
                ps.setString(3, listaproductos[i][2]);
                ps.setString(4, (String) jcomboxPagaCon.getSelectedItem());
                ps.setString(5, lblFecha.getText());
                ps.executeUpdate();
                con.close();

            }
            anu = Integer.parseInt(lblTotal.getText());
            if (anu > 0) {
                JOptionPane.showMessageDialog(null, "Venta guardada");
            } else {
                JOptionPane.showMessageDialog(null,"No ha anexado ningun producto");
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "No hay nada que guardar");
            System.err.println(e);
        }

    }

    void devolucion() {
        String formpag = (String) jcomboxPagaCon.getSelectedItem();
        if (formpag == "Efectivo") {

            int dev = 0;
            int rest = 0;
            int dif = 0;
            dev = Integer.parseInt(lblTotal.getText());
            rest = Integer.parseInt(txtPagacon.getText());
            dif = rest - dev;
            if (dev > rest){
                 JOptionPane.showMessageDialog(null, "No le alcanza para el valor a pagar","ERROR",0);
            }else{
            lblVueltas.setText(String.valueOf(dif));}
        } else {
            JOptionPane.showMessageDialog(null, "No es un pago en efectivo");
        }
    }

    void buscar() {
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
        jcomboxPagaCon = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblFactura = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnArqueo = new javax.swing.JButton();
        lblArqueo = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("                                                                                                     DROGUERIA SALUD+    VENTAS");

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

        lblTotal.setBackground(new java.awt.Color(255, 255, 255));
        lblTotal.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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

        lblVr_venta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVr_venta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtPagacon.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        txtPagacon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        lblVueltas.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        lblVueltas.setForeground(new java.awt.Color(255, 0, 0));
        lblVueltas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVueltas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jcomboxPagaCon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Nequi", "Davivienda", "Bold", "Redeban" }));
        jcomboxPagaCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboxPagaConActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ventanaPrincipalLayout = new javax.swing.GroupLayout(ventanaPrincipal);
        ventanaPrincipal.setLayout(ventanaPrincipalLayout);
        ventanaPrincipalLayout.setHorizontalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPagacon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcomboxPagaCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDesktopPane2)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jbtnlimpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jbtnEliminar)
                                                    .addComponent(jbtnAnexar))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                                        .addGap(71, 71, 71)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(69, 69, 69))
                                                    .addComponent(lblVueltas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        ventanaPrincipalLayout.setVerticalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtCod_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(btnSeleccionar)
                            .addComponent(lbl_Articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar)
                            .addComponent(jLabel9)
                            .addComponent(lblVr_venta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtcodproducto)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jcomboxPagaCon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPagacon, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbtnAnexar)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(jbtnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbtnlimpiarTabla)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addComponent(lblVueltas, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGap(0, 0, Short.MAX_VALUE)
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
                .addGap(71, 71, 71)
                .addComponent(btnCancelar)
                .addGap(28, 28, 28)
                .addComponent(btnGuardar)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblArqueo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)))
                    .addComponent(jLabel5)
                    .addComponent(lblFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnArqueo))
                .addGap(21, 21, 21))
        );

        ventanaPrincipal.getAccessibleContext().setAccessibleName("\n");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        totalizar();
        limpiarTabla();
        limpiarCajas();
        jcomboxPagaCon.setSelectedItem("Efectivo");

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
        jcomboxPagaCon.setSelectedItem("Efectivo");
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

    private void jcomboxPagaConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboxPagaConActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboxPagaConActionPerformed

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
    private javax.swing.JComboBox<String> jcomboxPagaCon;
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
