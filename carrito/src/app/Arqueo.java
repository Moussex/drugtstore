/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package app;

import static app.venta1.getConection;
import static app.venta1.lblArqueo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Misaky
 */
public class Arqueo extends javax.swing.JInternalFrame {
    
    DefaultTableModel dtm2 = new DefaultTableModel();
    
    public static final String URL = "jdbc:mysql://localhost:3306/products";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "misionTic2022";
    
    PreparedStatement ps;
    ResultSet rs;

    /**
     * Creates new form Arqueo
     */
    public Arqueo() {
        initComponents();
        String []  nombresColumnas1 = {"Articulo","Precio","Fecha"};//Indica el nombre de las columnas en la tabla
        dtm2.setColumnIdentifiers(nombresColumnas1);
        tabla2.setModel(dtm2);
        }
    
    public void buscarFecha(String fecha){
    Connection con = null;
    ResultSet rs = null;   
    int contador2 = 1; // Dedicado para acomular en número de registros que hay en la tabla
    String [] registros2 = new String[3];                                   
        try
        {
            con = getConection();
            ps = con.prepareStatement( "SELECT * FROM ventas WHERE fecha LIKE '%"+fecha+"%'");
            rs = (ResultSet) 
            ps.executeQuery();
            while(rs.next())
            {
                registros2[0] = rs.getString("articulo");
                registros2[1]=rs.getString("precio");
                registros2[2]=rs.getString("fecha");
                dtm2.addRow(registros2);
                contador2++;
            }                      
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null,"Error al conectar. "+e.getMessage());
        }
         finally
        {
            try
            {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }
       
public void sumatoriaarqueo(){    
 double sum2 = 0;
 double pre2;
        
       int cantfilas2 = dtm2.getRowCount();
        for (int i = 0; i < cantfilas2; i++) {
            pre2 = Double.parseDouble((String) dtm2.getValueAt(i, 1));
            sum2 = sum2 + pre2;
            lblArqueo.setText(String.valueOf(sum2));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla2 = new javax.swing.JTable();
        txtFecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Arqueo");

        tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tabla2);

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese Fecha:");

        jLabel2.setText("dd/mm/aaaa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
     buscarFecha(txtFecha.getText());   // TODO add your handling code here:
     sumatoriaarqueo();
    }//GEN-LAST:event_txtFechaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla2;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables
}
