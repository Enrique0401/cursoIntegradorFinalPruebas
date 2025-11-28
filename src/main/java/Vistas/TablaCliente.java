package Vistas;

import Builder.*;
import Model.*;
import Observer.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import Repositorio.*;
import Servicio.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import javax.swing.JOptionPane;

public class TablaCliente extends javax.swing.JPanel implements Observador {

    private DefaultTableModel modelo;
    private final IClienteService usuarioService = new ClienteService(new ClienteRepositorio());

    public TablaCliente() {
        initComponents();
        modelo = (DefaultTableModel) tablaUsuario.getModel();
        EntidadObservableSingleton.getInstancia().agregarObservador(this);
        cargarUsuarios();
        this.noVer.setVisible(false);
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
                    u.getDireccionCliente(),
                    u.getTelefonoCliente(),
                    u.getEmailCliente(),
                    u.getContrasenaCliente(),
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

    private void limpiarCampos() {
        txtNombre.setText("");
        txtRuc.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtContrasena.setText("");
        txtFecha.setDate(null);
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
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        txtTelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        txtContrasena = new javax.swing.JPasswordField();
        btnVerTodos = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        txtFecha = new com.toedter.calendar.JDateChooser();
        ver = new javax.swing.JLabel();
        noVer = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("INSTRUCTOR");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(797, 449));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnEliminar.setBackground(new java.awt.Color(30, 45, 60));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(30, 45, 60));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Fecha de Nacimiento");

        btnVer.setBackground(new java.awt.Color(30, 45, 60));
        btnVer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("Ver");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel2.setText("RUC");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setText("Email");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Telefono");

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Contraseña");

        txtNombre.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtRuc.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtEmail.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnAgregar.setBackground(new java.awt.Color(30, 45, 60));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Añadir");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        txtTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        tablaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Nombre", "RUC", "Dirección", "Telefono", "Email", "Contraseña", "Rol", "FecReg"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        txtContrasena.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        btnVerTodos.setBackground(new java.awt.Color(30, 45, 60));
        btnVerTodos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVerTodos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerTodos.setText("Ver Todos");
        btnVerTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerTodosActionPerformed(evt);
            }
        });

        btnSeleccionar.setBackground(new java.awt.Color(30, 45, 60));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(255, 255, 255));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        txtFecha.setDateFormatString("yyyy-MM-dd");

        ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ojo.png"))); // NOI18N
        ver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verMouseClicked(evt);
            }
        });

        noVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ojo_cerrado.png"))); // NOI18N
        noVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noVerMouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel7.setText("Dirección");

        txtDireccion.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnSeleccionar)
                                .addGap(46, 46, 46)
                                .addComponent(btnVer)
                                .addGap(64, 64, 64)
                                .addComponent(btnVerTodos))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(noVer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(7, 7, 7)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addComponent(jLabel3))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(35, 35, 35)
                                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                        .addGap(34, 34, 34)
                                                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(162, 162, 162)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 44, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(ver, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(noVer))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnVer)
                    .addComponent(btnVerTodos))
                .addGap(24, 24, 24))
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

//----------------------------Agregar usuario-----------------------------------
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {

            String nombre = txtNombre.getText().trim();
            String ruc = txtRuc.getText().trim();
            String direccion = txtRuc.getText().trim();
            String email = txtEmail.getText().trim();
            String telefonoStr = txtTelefono.getText().trim();
            String contrasena = new String(txtContrasena.getPassword());

            if (!validarCampos(nombre, ruc, email, telefonoStr, contrasena)) {
                return;
            }

            java.util.Date utilDate = txtFecha.getDate();
            if (utilDate == null) {
                mostrarError("Debe seleccionar una fecha de nacimiento válida.");
                return;
            }

            LocalDate fechaNacimiento = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (!validarEdad(fechaNacimiento)) {
                return;
            }

            int telefono;
            try {
                telefono = Integer.parseInt(telefonoStr);
            } catch (NumberFormatException ex) {
                mostrarError("El teléfono debe ser numérico.");
                return;
            }

            Cliente nuevo = new ClienteBuilder()
                    .conNombreCliente(nombre)
                    .conRucCliente(ruc) // si lo tienes en el formulario
                    .conDireccionCliente(direccion) // si lo tienes en el formulario
                    .conTelefonoCliente(telefonoStr)
                    .conEmailCliente(email)
                    .conContrasenaCliente(contrasena)
                    .conRol("ROLE_USER") // asigna el rol por defecto
                    .conFechaRegistro(LocalDateTime.now())
                    .build();

            boolean fueExitoso = usuarioService.registrar(nuevo);

            if (fueExitoso) {
                EntidadObservableSingleton.getInstancia().notificarObservadores(); // Esto llama a actualizar() y a su vez carga los usuarios
                limpiarCampos();
                mostrarInfo("✅ Usuario agregado correctamente.");
            }
        } catch (Exception e) {
            mostrarError("❌ Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed
//--------------------------------Eliminar usuario------------------------------    
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            int fila = tablaUsuario.getSelectedRow();
            if (fila == -1) {
                mostrarError("Selecciona un usuario para eliminar.");
                return;
            }

            // Obtener ID del usuario seleccionado en la tabla
            int id = (int) tablaUsuario.getValueAt(fila, 0);

            // Eliminar desde el servicio/repositorio
            boolean fueEliminado = usuarioService.eliminar(id);

            if (fueEliminado) {
                // 🔔 Notificar a los observadores para actualizar la tabla
                EntidadObservableSingleton.getInstancia().notificarObservadores();
                limpiarCampos();
                mostrarInfo("✅ El usuario fue eliminado correctamente.");
            } else {
                mostrarError("❌ No se pudo eliminar el usuario.");
            }

        } catch (Exception e) {
            mostrarError("❌ Error al eliminar usuario: " + e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
//--------------------------Actualizar usuarios---------------------------------
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            int fila = tablaUsuario.getSelectedRow();
            if (fila == -1) {
                mostrarError("Selecciona un cliente para actualizar.");
                return;
            }

            int id = Integer.parseInt(modelo.getValueAt(fila, 0).toString());

            String nombre = txtNombre.getText().trim();
            String ruc = txtRuc.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String email = txtEmail.getText().trim();
            String contrasena = new String(txtContrasena.getPassword()).trim();
            java.util.Date utilDate = txtFecha.getDate(); // suponiendo que usas un JDateChooser para fecha

            if (nombre.isEmpty() || ruc.isEmpty() || email.isEmpty() || telefono.isEmpty() || contrasena.isEmpty()) {
                mostrarError("Todos los campos son obligatorios.");
                return;
            }

            if (utilDate == null) {
                mostrarError("Debe seleccionar una fecha de registro válida.");
                return;
            }

            if (telefono.length() != 9 || !telefono.matches("\\d+")) {
                mostrarError("El teléfono debe tener 9 dígitos numéricos.");
                return;
            }

            if (ruc.length() != 11 || !ruc.matches("\\d+")) {
                mostrarError("El RUC debe tener 11 dígitos numéricos.");
                return;
            }

            Cliente original = usuarioService.buscarPorId(id);
            if (original == null) {
                mostrarError("El cliente ya no existe.");
                return;
            }

            // 🔹 Actualizar campos según tu modelo real
            original.setNombreCliente(nombre);
            original.setRucCliente(ruc);
            original.setDireccionCliente(direccion);
            original.setTelefonoCliente(telefono);
            original.setEmailCliente(email);
            original.setContrasenaCliente(contrasena);
            original.setFechaRegistro(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            boolean actualizado = usuarioService.actualizar(original);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente.");
                cargarUsuarios(); // refresca tabla
                limpiarCampos();
            } else {
                mostrarError("No se pudo actualizar el cliente.");
            }

        } catch (Exception e) {
            mostrarError("❌ Error inesperado: " + e.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
//-------------------------------Ver usuario por ID-----------------------------
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
                    u.getDireccionCliente(),
                    u.getTelefonoCliente(),
                    u.getEmailCliente(),
                    u.getContrasenaCliente(),
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

        txtNombre.setText(tablaUsuario.getValueAt(fila, 1).toString());
        txtRuc.setText(tablaUsuario.getValueAt(fila, 2).toString());
        txtFecha.setDate(Date.valueOf(tablaUsuario.getValueAt(fila, 3).toString()));
        txtEmail.setText(tablaUsuario.getValueAt(fila, 4).toString());
        txtTelefono.setText(tablaUsuario.getValueAt(fila, 5).toString());
        txtContrasena.setText(tablaUsuario.getValueAt(fila, 9).toString());
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private boolean contrasenaVisible = false;

    private void noVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noVerMouseClicked
        txtContrasena.setEchoChar('•'); // Ocultar con puntitos
        contrasenaVisible = false;
        noVer.setVisible(false);
        ver.setVisible(true);
    }//GEN-LAST:event_noVerMouseClicked

    private void verMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verMouseClicked
        txtContrasena.setEchoChar((char) 0); // Mostrar texto plano
        contrasenaVisible = true;
        ver.setVisible(false);
        noVer.setVisible(true);
    }//GEN-LAST:event_verMouseClicked

//---------------------------Días del calendario--------------------------------  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton btnVerTodos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel noVer;
    private javax.swing.JTable tablaUsuario;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JLabel ver;
    // End of variables declaration//GEN-END:variables
}
