package Vistas;

import Model.*;
import Observer.*;
import javax.swing.table.DefaultTableModel;
import Repositorio.*;
import Servicio.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;

public class TablaUsuario extends javax.swing.JPanel implements Observador {

    private final DefaultTableModel modelo;
    private final IClienteService usuarioService = new ClienteService(new ClienteRepositorio());

    public TablaUsuario() {
        initComponents();
        modelo = (DefaultTableModel) tablaUsuario.getModel();
        EntidadObservableSingleton.getInstancia().agregarObservador(this);
        cargarUsuarios();
    }

    @Override
    public void actualizar() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        modelo.setRowCount(0);
        List<Cliente> lista = usuarioService.obtenerTodos();

        for (Cliente u : lista) {
            if (u.getRol() != null && u.getRol().equalsIgnoreCase("ROLE_USER")) {
                modelo.addRow(new Object[]{
                    u.getIdCliente(),
                    u.getNombreCliente(),
                    u.getRucCliente(),
                    u.getEmailCliente(),
                    u.getTelefonoCliente(),
                    u.getDireccionCliente(),
                    u.getRol(),
                    u.getFechaRegistro() != null ? u.getFechaRegistro().toLocalDate() : null
                });
            }
        }
    }

    //MÉTODOS REUTILIZABLES//
    //Método para validar la fecha
    private boolean validarCampos(String nombre, String apellido, String email, String telefono, String contrasena) {
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return false;
        }

        if (!email.contains("@")) {
            mostrarError("El email debe contener '@'.");
            return false;
        }

        if (!telefono.matches("9\\d{8}")) {
            mostrarError("El teléfono debe comenzar con 9 y tener 9 dígitos.");
            return false;
        }

        if (!contrasena.matches("u\\d{6}")) {
            mostrarError("La contraseña debe comenzar con 'u' y tener 6 números.");
            return false;
        }

        return true;
    }

    private boolean validarEdad(LocalDate fechaNacimiento) {
        int edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
        if (edad < 16 || edad > 70) {
            mostrarError("La edad debe estar entre 16 y 70 años.");
            return false;
        }
        return true;
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnVer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        btnVerTodos = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("INSTRUCTOR");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(797, 449));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnVer.setBackground(new java.awt.Color(18, 60, 109));
        btnVer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("Ver");
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        tablaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "RUC", "Correo", "Telefono", "Dirección", "Rol", "Fecha Registro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        btnVerTodos.setBackground(new java.awt.Color(18, 60, 109));
        btnVerTodos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerTodos.setText("Ver Todos");
        btnVerTodos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });

        btnSeleccionar.setBackground(new java.awt.Color(18, 60, 109));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("TABLA DE CLIENTES");

        botonRegresar.setText("Regresar");
        botonRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(318, 318, 318)
                .addComponent(jLabel1)
                .addGap(126, 126, 126)
                .addComponent(botonRegresar))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(btnSeleccionar)
                .addGap(149, 149, 149)
                .addComponent(btnVer)
                .addGap(124, 124, 124)
                .addComponent(btnVerTodos))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(botonRegresar)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnVer)
                    .addComponent(btnVerTodos)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        try {
            String input = JOptionPane.showInputDialog(this, "Ingrese el ID del cliente:");
            if (input != null && !input.trim().isEmpty()) {
                int id = Integer.parseInt(input.trim());
                Cliente u = usuarioService.buscarPorId(id);

                if (u == null) {
                    mostrarInfo("Cliente no encontrado.");
                    return;
                }

                modelo.setRowCount(0);
                modelo.addRow(new Object[]{
                    u.getIdCliente(),
                    u.getNombreCliente(),
                    u.getRucCliente(),
                    u.getEmailCliente(),
                    u.getTelefonoCliente(),
                    u.getDireccionCliente(),
                    u.getRol(),
                    u.getFechaRegistro() != null ? u.getFechaRegistro().toLocalDate() : null
                });
            }
        } catch (NumberFormatException e) {
            mostrarError("El ID debe ser numérico.");
        } catch (Exception e) {
            mostrarError("Error al buscar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVerActionPerformed
//----------------------------Mostrar Todos los Instructores-----------------------------------
    private void btnVerTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerTodosActionPerformed
        // TODO add your handling code here:
        cargarUsuarios();
    }//GEN-LAST:event_btnVerTodosActionPerformed
//----------------------------Llena los campos -----------------------------------
    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
        int fila = tablaUsuario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        /*txtNombre.setText(tablaUsuario.getValueAt(fila, 1).toString());
        txtRuc.setText(tablaUsuario.getValueAt(fila, 2).toString());
        txtFecha.setDate(Date.valueOf(tablaUsuario.getValueAt(fila, 3).toString()));
        txtEmail.setText(tablaUsuario.getValueAt(fila, 4).toString());
        txtTelefono.setText(tablaUsuario.getValueAt(fila, 5).toString());*/
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed

    private boolean contrasenaVisible = false;

//---------------------------Días del calendario--------------------------------  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
}
