/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package proyectout1cperales;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author Meu
 */
public class SeleccionarClienteDialog extends javax.swing.JDialog {

    /**
     * Creates new form SeleccionarClienteDialog
     */
    public SeleccionarClienteDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    private JTable tablaClientes;
    private JTextField buscadorTextField;
    private DefaultTableModel modeloTabla;
    private Connection con;
    private String nombreCompaniaSeleccionada;

    public SeleccionarClienteDialog(JFrame parent, Connection conexion) {
        super(parent, "Seleccionar Cliente", true);
        this.con = conexion;
        this.nombreCompaniaSeleccionada = null;

        setSize(800, 550);
        setLocationRelativeTo(parent);

        // Panel principal vertical
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        add(panelPrincipal);

        // === TABLA (con altura fija) ===
        modeloTabla = new DefaultTableModel(new String[]{"Compañía", "Contacto", "Cargo", "Dirección", "Ciudad", "CP", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setPreferredSize(new Dimension(580, 200)); // altura fija
        panelPrincipal.add(scrollPane);

        // === FORMULARIO DE BÚSQUEDA ===
        JPanel panelFormulario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelFormulario.add(new JLabel("Buscar:"));
        buscadorTextField = new JTextField(20);
        panelFormulario.add(buscadorTextField);
        panelPrincipal.add(panelFormulario);

        // === BOTONES ===
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton buscarButton = new JButton("Buscar");
        JButton reiniciarButton = new JButton("Reiniciar");
        JButton seleccionarButton = new JButton("Seleccionar");

        panelBotones.add(buscarButton);
        panelBotones.add(reiniciarButton);
        panelBotones.add(seleccionarButton);
        panelPrincipal.add(panelBotones);

        // === Eventos ===
        buscarButton.addActionListener(e -> cargarClientes(buscadorTextField.getText().trim()));
        reiniciarButton.addActionListener(e -> {
            buscadorTextField.setText("");
            cargarClientes("");
        });
        seleccionarButton.addActionListener(e -> {
            int filaSeleccionada = tablaClientes.getSelectedRow();
            if (filaSeleccionada != -1) {
                nombreCompaniaSeleccionada = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Seleccione un cliente", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        cargarClientes("");
    }

    private void cargarClientes(String filtro) {
        modeloTabla.setRowCount(0); // Limpiar tabla

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT c.nomCompania,\n" +
                            "c.nomContacto,\n" +
                            "ca.nombre,\n" +
                            "c.Direccion,\n" +
                            "ciu.nombre,\n" +
                            "c.codigoPostal,\n" +
                            "c.telefono FROM Clientes c " +
                            "JOIN Cargos ca ON ca.codigo = c.codCargo " +
                            "JOIN Ciudades ciu ON c.codCiudad = ciu.codigo";
            if (!filtro.isEmpty()) {
                sql += " WHERE nomCompania LIKE '%" + filtro + "%'";
            }
            ResultSet resul = stmt.executeQuery(sql);

           while (resul.next()) {
                String nomCompania = resul.getString("nomCompania");
                String nomContacto = resul.getString("nomContacto");
                String cargo = resul.getString("nombre");
                String direccion = resul.getString("direccion");
                String ciudad = resul.getString(5);
                String cp = resul.getString("codigoPostal");
                String telefono = resul.getString("telefono");

                modeloTabla.addRow(new Object[]{
                    nomCompania, nomContacto, cargo,
                    direccion, ciudad, cp, telefono
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getNombreCompaniaSeleccionada() {
        return nombreCompaniaSeleccionada;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
