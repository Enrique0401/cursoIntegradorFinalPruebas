package Vistas;

import Model.*;
import Observer.*;
import javax.swing.table.DefaultTableModel;
import Repositorio.*;
import Servicio.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;

public class TablaSeguimientos extends javax.swing.JPanel implements Observador {

    private DefaultTableModel modelo;
    private final ISeguimientoService seguimientoService = new SeguimientoService(new SeguimientoRepositorio());
    private final IProyectoService proyectoService = new ProyectoService(new ProyectoRepositorio());

    public TablaSeguimientos() {
        initComponents();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel()); 
        
        modelo = (DefaultTableModel) tablaUsuario.getModel();
        EntidadObservableSingleton.getInstancia().agregarObservador(this);

        cargarSeguimientos();
        cargarComboProyectos();
        configurarSpinner();
        configurarAreaTexto();
    }

    @Override
    public void actualizar() {
        cargarSeguimientos();
        cargarComboProyectos();
    }

    private void cargarSeguimientos() {
        modelo.setRowCount(0);
        
        // Obtenemos todos los seguimientos
        List<Seguimiento> lista = seguimientoService.obtenerTodos();

        for (Seguimiento s : lista) {
            String nombreProyecto = "Desconocido";
            Proyectos p = proyectoService.buscarPorId(s.getIdProyecto());
            
            if (p != null) {
                nombreProyecto = p.getNombre();
            }

            modelo.addRow(new Object[]{
                nombreProyecto,
                s.getFechaAvance() != null ? s.getFechaAvance().toLocalDate() : null,
                s.getDescripcion(),
                s.getPorcentajeAvance() + "%"
            });
        }
    }

    // --- NUEVO: CARGAR PROYECTOS EN EL COMBO ---
    private void cargarComboProyectos() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        // Obtener lista de proyectos de la BD
        List<Proyectos> listaProyectos = proyectoService.obtenerTodos();

        // Añadir opción por defecto
        model.addElement(new ItemProyecto(-1, "-- Seleccione un Proyecto --"));

        for (Proyectos p : listaProyectos) {
            // Guardamos ID y Nombre en el objeto auxiliar
            model.addElement(new ItemProyecto(p.getIdProyecto(), p.getNombre()));
        }

        jComboBox1.setModel(model);
    }

    // --- NUEVO: CONFIGURAR LIMITES DEL SPINNER ---
    private void configurarSpinner() {
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 100, 1);
        jSpinner1.setModel(modeloSpinner);

        javax.swing.JFormattedTextField txt = ((javax.swing.JSpinner.DefaultEditor) jSpinner1.getEditor()).getTextField();
        txt.setEditable(false);
        txt.setBackground(java.awt.Color.WHITE);
    }

    // --- 3. CONFIGURAR TEXT AREA (Max 300 caracteres) ---
    private void configurarAreaTexto() {
        jTextArea1.setLineWrap(true);       // Salto de línea automático
        jTextArea1.setWrapStyleWord(true);  // No cortar palabras

        // Inicializar contador
        jLabel7.setText("0/300");

        jTextArea1.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent evt) {
                // Si llega a 300, bloquea la escritura
                if (jTextArea1.getText().length() >= 300) {
                    evt.consume();
                    java.awt.Toolkit.getDefaultToolkit().beep();
                }
            }

            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                // Actualiza el contador al escribir o borrar
                jLabel7.setText(jTextArea1.getText().length() + "/300");
            }
        });
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void mostrarInfo(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    // Sincronizar el avance en la tabla de proyectos
    // Método para sincronizar el porcentaje en la tabla de Proyectos
    private void actualizarProgresoProyecto(int idProyecto, int nuevoPorcentaje) {
        try {
            Proyectos p = proyectoService.buscarPorId(idProyecto);
            if (p != null) {
                p.setProgreso(nuevoPorcentaje);
                
                // Lógica de estados automática
                if (nuevoPorcentaje == 100) p.setEstado("Finalizado");
                else if (nuevoPorcentaje > 0) p.setEstado("Progreso");
                else p.setEstado("Pendiente");
                
                proyectoService.actualizar(p);
            }
        } catch (Exception e) {
            System.err.println("Error al sincronizar proyecto: " + e.getMessage());
        }
    }

    private void limpiarFormulario() {
        jTextArea1.setText("");
        jSpinner1.setValue(0);
        if (jComboBox1.getItemCount() > 0) jComboBox1.setSelectedIndex(0);
        jLabel7.setText("0/300");
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        guardarBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("INSTRUCTOR");

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(32767, 53767));
        setPreferredSize(new java.awt.Dimension(797, 449));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 53367));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("GESTION DE SEGUIMIENTOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Listado de seguimientos");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Porcentaje (%)");

        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Proyecto");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        guardarBtn.setBackground(new java.awt.Color(18, 60, 109));
        guardarBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        guardarBtn.setForeground(new java.awt.Color(255, 255, 255));
        guardarBtn.setText("Guardar seguimiento");
        guardarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        tablaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Proyecto", "Fecha del Avance", "Descripción", "Porcentaje"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaUsuario);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Descipción del avance");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Cantidad de caracteres");

        jLabel7.setText("jLabel7");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addComponent(botonRegresar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(botonRegresar)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardarBtn)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel7)))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        try {
            // 1. OBTENER SELECCIÓN
            Object seleccionado = jComboBox1.getSelectedItem();

            // --- PROTECCIÓN ANTI-ERROR ---
            // Si está vacío o es un texto (el error que te sale), detenemos todo.
            if (seleccionado == null || seleccionado instanceof String) {
                JOptionPane.showMessageDialog(this, "⚠️ Espera a que carguen los proyectos o selecciona uno válido.");
                return;
            }

            // Ahora sí es seguro convertirlo, porque sabemos que no es un String
            ItemProyecto itemProyecto = (ItemProyecto) seleccionado;

            // 2. VALIDAR QUE NO SEA EL PROYECTO "VACÍO" (-1)
            if (itemProyecto.getId() == -1) {
                mostrarError("⚠️ Debes seleccionar un proyecto de la lista.");
                return;
            }

            // 3. RESTO DE TU LÓGICA (Esto igual que antes)
            String descripcion = jTextArea1.getText().trim();
            if (descripcion.isEmpty()) {
                mostrarError("⚠️ La descripción no puede estar vacía.");
                return;
            }

            int idProyecto = itemProyecto.getId();
            int porcentaje = (Integer) jSpinner1.getValue();
            
            // Crear y guardar
            Seguimiento nuevoSeguimiento = new Seguimiento();
            nuevoSeguimiento.setIdProyecto(idProyecto);
            nuevoSeguimiento.setDescripcion(descripcion);
            nuevoSeguimiento.setPorcentajeAvance(porcentaje);
            nuevoSeguimiento.setFechaAvance(LocalDateTime.now());

            if (seguimientoService.registrar(nuevoSeguimiento)) {
                mostrarInfo("✅ Seguimiento guardado.");
                
                // Actualizar proyecto padre
                actualizarProgresoProyecto(idProyecto, porcentaje);
                
                limpiarFormulario();
                EntidadObservableSingleton.getInstancia().notificarObservadores();
            } else {
                mostrarError("❌ No se pudo guardar SEGUIMIENTO.");
            }

        } catch (Exception e) {
            System.err.println("Error en botón guardar: " + e.getMessage()); // Muestra error en consola sin romper el programa
        }
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed

//----------------------------Llena los campos -----------------------------------
    private boolean contrasenaVisible = false;

//---------------------------Días del calendario--------------------------------  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
}

// --- CLASE AUXILIAR PARA EL COMBOBOX DE PROYECTOS ---
class ItemProyecto {

    private int id;
    private String nombre;

    public ItemProyecto(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
