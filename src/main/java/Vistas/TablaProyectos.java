package Vistas;

import EditarProyecto.EditarCategoria;
import EditarProyecto.EditarProgreso;
import EditarProyecto.EditarProyecto;
import Model.*;
import Observer.*;
import javax.swing.table.DefaultTableModel;
import Repositorio.*;
import Servicio.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

public class TablaProyectos extends javax.swing.JPanel implements Observador {

    private DefaultTableModel modelo;
    private final IClienteService usuarioService = new ClienteService(new ClienteRepositorio());
    private final IProyectoService proyectoService = new ProyectoService(new ProyectoRepositorio());

    public TablaProyectos() {
        initComponents();
        modelo = (DefaultTableModel) tablaUsuario.getModel();
        EntidadObservableSingleton.getInstancia().agregarObservador(this);
        
        // 1. Cargar la lista de clientes en el combo (Solo ROLE_USER)
        cargarComboClientes();
        
        // 2. Cargar la tabla con todos los proyectos al inicio
        cargarProyectos(-1);
    }

    private void cargarComboClientes() {
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        
        // Opción por defecto para ver todo
        modeloCombo.addElement(new ItemCliente(-1, "Todos los clientes"));
        
        // Obtenemos todos los usuarios
        List<Cliente> listaClientes = usuarioService.obtenerTodos();
        
        for (Cliente c : listaClientes) {
            // FILTRO: Solo añadimos si tienen el rol de usuario
            if (c.getRol() != null && c.getRol().equalsIgnoreCase("ROLE_USER")) {
                // Creamos nuestro objeto especial que guarda ID y Nombre
                modeloCombo.addElement(new ItemCliente(c.getIdCliente(), c.getNombreCliente()));
            }
        }
        
        comboClientes.setModel(modeloCombo);
    }

    @Override
    public void actualizar() {
        botonFiltrarActionPerformed(null);
    }

    private void cargarProyectos(int idClienteFiltro) {
        modelo.setRowCount(0); // Limpiar tabla
        
        // Traemos TODOS los proyectos de la base de datos
        List<Proyectos> lista = proyectoService.obtenerTodos();

        for (Proyectos p : lista) {
            // LÓGICA DE FILTRADO:
            // Si idClienteFiltro es -1, mostramos todo.
            // Si no, mostramos solo si el ID del cliente coincide.
            if (idClienteFiltro == -1 || p.getIdCliente() == idClienteFiltro) {
                modelo.addRow(new Object[]{
                    p.getIdProyecto(),
                    p.getNombre(),
                    p.getIdCliente(), // Mostramos ID del cliente para verificar
                    p.getCategoria(),
                    p.getEstado(),
                    p.getProgreso() + "%", // Añadimos el símbolo % visualmente
                    p.getFechaEntrega() != null ? p.getFechaEntrega().toLocalDate() : "Sin fecha"
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

    /*private void limpiarCampos() {
        txtNombre.setText("");
        txtRuc.setText("");
        txtEmail.setText("");
        txtTelefono.setText("");
        txtContrasena.setText("");
        txtFecha.setDate(null);
    }*/
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
        btnActualizar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        comboClientes = new javax.swing.JComboBox<>();
        botonFiltrar = new javax.swing.JButton();
        botonLimiar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("INSTRUCTOR");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(797, 449));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar.setBackground(new java.awt.Color(18, 60, 109));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btnActualizar.setText("Editar categoria");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Filtrar por cliente:");

        btnVer.setBackground(new java.awt.Color(18, 60, 109));
        btnVer.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVer.setForeground(new java.awt.Color(255, 255, 255));
        btnVer.setText("Editar progreso");
        btnVer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(18, 60, 109));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("Editar proyecto");
        btnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        tablaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Proyecto", "Cliente", "Categoria", "Estado", "Progreso", "Fecha de entrega"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos los clientes", "Item 2", "Item 3", "Item 4" }));
        comboClientes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboClientesActionPerformed(evt);
            }
        });

        botonFiltrar.setText("Filtrar");
        botonFiltrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFiltrarActionPerformed(evt);
            }
        });

        botonLimiar.setText("Limpiar");
        botonLimiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonLimiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimiarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("GESTION DE PROYECTOS");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonFiltrar)
                                .addGap(18, 18, 18)
                                .addComponent(botonLimiar)
                                .addGap(38, 415, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(botonRegresar)
                                .addGap(25, 25, 25))))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVer)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(botonRegresar))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonFiltrar)
                    .addComponent(botonLimiar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnVer))
                .addContainerGap(29, Short.MAX_VALUE))
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

//----------------------------Editar descripcción-----------------------------------
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int fila = tablaUsuario.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        try {
            int idProyecto = Integer.parseInt(tablaUsuario.getValueAt(fila, 0).toString());

            String descripcionActual = tablaUsuario.getValueAt(fila, 1).toString();

            EditarProyecto edit = new EditarProyecto(idProyecto, descripcionActual);
            edit.setVisible(true);
            edit.setLocationRelativeTo(null);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al leer la tabla: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

//--------------------------Editar la categoria---------------------------------
    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // 1. Verificar selección
        int fila = tablaUsuario.getSelectedRow();
        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        try {
            // 2. OBTENER DATOS DE LA TABLA
            // Columna 0 = ID (int)
            int idProyecto = Integer.parseInt(tablaUsuario.getValueAt(fila, 0).toString());

            // Columna 3 = Categoria (String)
            // Verifica si tu columna de categoría es la 3. Si no, ajusta este índice.
            String categoriaActual = tablaUsuario.getValueAt(fila, 3).toString();

            // 3. ABRIR LA VENTANA PASANDO LOS DATOS
            EditarCategoria cate = new EditarCategoria(idProyecto, categoriaActual);
            cate.setVisible(true);
            cate.setLocationRelativeTo(null);

        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error al leer datos: " + e.getMessage());
        }
    }//GEN-LAST:event_btnActualizarActionPerformed
//-------------------------------Editar el progreso-----------------------------
    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        // 1. Validar que haya una fila seleccionada
        int fila = tablaUsuario.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        try {
            // 2. Obtener los datos de la tabla (Asegúrate de que las columnas coincidan)
            // Columna 0 = ID del proyecto
            int idProyecto = Integer.parseInt(tablaUsuario.getValueAt(fila, 0).toString());

            // Columna 5 = Progreso actual (lo convertimos a entero)
            // Si en tu tabla está guardado como "50%", necesitarás limpiarlo, 
            // pero si es un número entero (50), esto funcionará:
            int progresoActual = Integer.parseInt(tablaUsuario.getValueAt(fila, 5).toString());

            // 3. Instanciar la ventana pasando los datos (SOLUCIÓN DEL ERROR)
            EditarProgreso progres = new EditarProgreso(idProyecto, progresoActual);
            progres.setVisible(true);
            progres.setLocationRelativeTo(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al leer datos de la tabla: " + e.getMessage());
        }
    }//GEN-LAST:event_btnVerActionPerformed

    private boolean contrasenaVisible = false;

    private void botonFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFiltrarActionPerformed
       // Obtener el cliente seleccionado del ComboBox
        ItemCliente item = (ItemCliente) comboClientes.getSelectedItem();
        
        if (item != null) {
            // Recargar la tabla usando el ID de ese cliente
            cargarProyectos(item.getId());
        }
    }//GEN-LAST:event_botonFiltrarActionPerformed

    private void botonLimiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimiarActionPerformed
        comboClientes.setSelectedIndex(0); // Seleccionar "Todos"
        cargarProyectos(-1); // Cargar todo
    }//GEN-LAST:event_botonLimiarActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void comboClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboClientesActionPerformed

//---------------------------Días del calendario--------------------------------  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFiltrar;
    private javax.swing.JButton botonLimiar;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
}

// Clase auxiliar para guardar ID y Nombre en el ComboBox
class ItemCliente {
    private int id;
    private String nombre;

    public ItemCliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    
    @Override
    public String toString() { return nombre; } 
}