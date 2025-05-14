/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectout1cperales;

/**
 *
 * @author Meu
 */
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DetallePedidoDialog extends javax.swing.JDialog {

    /**
     * Creates new form DetallePedidoDialog
     */
    private Connection connection;
     private int codigoPedido;

     // Componentes de la interfaz
    private JTextField tfCodigo, tfCodCliente, tfCodEmpleado, tfFechaPedido, tfFechaEntrega, tfFechaEnvio,
            tfCargo, tfDireccion, tfCodCiudad, tfCodPostal, tfCodEmpreEnvio;
    
    public DetallePedidoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public DetallePedidoDialog(Frame parent, Connection connection, int codigoPedido) {
        super(parent, "Detalle del Pedido", true);
        this.connection = connection;
        this.codigoPedido = codigoPedido;

        // Configuración de la ventana
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(12, 2, 10, 10));

        // Inicializar los JTextField
        tfCodigo = new JTextField();
        tfCodCliente = new JTextField();
        tfCodEmpleado = new JTextField();
        tfFechaPedido = new JTextField();
        tfFechaEntrega = new JTextField();
        tfFechaEnvio = new JTextField();
        tfCargo = new JTextField();
        tfDireccion = new JTextField();
        tfCodCiudad = new JTextField();
        tfCodPostal = new JTextField();
        tfCodEmpreEnvio = new JTextField();

        // Configurar los JTextField como no editables
        tfCodigo.setEditable(false);
        tfCodCliente.setEditable(false);
        tfCodEmpleado.setEditable(false);
        tfFechaPedido.setEditable(false);
        tfFechaEntrega.setEditable(false);
        tfFechaEnvio.setEditable(false);
        tfCargo.setEditable(false);
        tfDireccion.setEditable(false);
        tfCodCiudad.setEditable(false);
        tfCodPostal.setEditable(false);
        tfCodEmpreEnvio.setEditable(false);

        // Agregar los JLabel y JTextField
        add(new JLabel("Código Pedido:"));
        add(tfCodigo);
        add(new JLabel("Compañia del cliente:"));
        add(tfCodCliente);
        add(new JLabel("Nombre del empleado::"));
        add(tfCodEmpleado);
        add(new JLabel("Fecha Pedido:"));
        add(tfFechaPedido);
        add(new JLabel("Fecha Entrega:"));
        add(tfFechaEntrega);
        add(new JLabel("Fecha Envío:"));
        add(tfFechaEnvio);
        add(new JLabel("Cargo:"));
        add(tfCargo);
        add(new JLabel("Dirección:"));
        add(tfDireccion);
        add(new JLabel("Ciudad:"));
        add(tfCodCiudad);
        add(new JLabel("Código Postal:"));
        add(tfCodPostal);
        add(new JLabel("Empresa de envío:"));
        add(tfCodEmpreEnvio);

        // Cargar los datos
        loadPedidoDetails();

        setVisible(true);
    }
    
    private void loadPedidoDetails() {
        try {
            // Consulta SQL adaptada para obtener todos los campos
            String query = "SELECT p.codigo,\n" + 
                            "c.nomCompania,\n" +
                            "e.nombre,\n" +
                            "p.fechaPedido,\n" +
                            "p.fechaEntrega,\n" +
                            "p.fechaEnvio,\n" +
                            "p.cargo,\n" +
                            "p.direccion,\n" +
                            "ciu.nombre,\n" +
                            "p.codPostal,\n" +
                            "ce.nombre FROM Pedidos p " +
                            "JOIN Clientes c ON c.codigo = p.codCliente " +
                            "JOIN Empleados e ON p.codEmpleado = e.codigo "  +
                            "JOIN Ciudades ciu ON ciu.codigo = p.codCiudad " +
                            "JOIN CompEnvios ce ON ce.codigo = p.codEmpreEnvio " +
                            "WHERE p.codigo = ? ";
            System.out.println(query);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(codigoPedido));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Rellenar los campos con los datos del ResultSet
                tfCodigo.setText(String.valueOf(rs.getInt("codigo")));
                tfCodCliente.setText(rs.getString("nomCompania"));
                tfCodEmpleado.setText(rs.getString("nombre"));
                tfFechaPedido.setText(rs.getString("fechaPedido"));
                tfFechaEntrega.setText(rs.getString("fechaEntrega"));
                tfFechaEnvio.setText(rs.getString("fechaEnvio"));
                tfCargo.setText(String.valueOf(rs.getDouble("cargo")));
                tfDireccion.setText(rs.getString("direccion"));
                tfCodCiudad.setText(rs.getString(9));
                tfCodPostal.setText(rs.getString("codPostal"));
                tfCodEmpreEnvio.setText(rs.getString(11));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los detalles del pedido.", "Error", JOptionPane.ERROR_MESSAGE);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(DetallePedidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetallePedidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetallePedidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetallePedidoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetallePedidoDialog dialog = new DetallePedidoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
