package Vistas;

import Model.*;
import Observer.*;
import Model.Proyectos;
import javax.swing.table.DefaultTableModel;
import Repositorio.*;
import Servicio.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TablaIncidencias extends javax.swing.JPanel implements Observador {

    private final DefaultTableModel modelo;
    private final IIncidenciaService incidenciaService = new IncidenciaService(new IncidenciaRepositorio());
    private final IProyectoService proyectoService = new ProyectoService(new ProyectoRepositorio());
    private List<ItemCombo> itemsCombo;

    public TablaIncidencias() {
        initComponents();
        modelo = (DefaultTableModel) tablaUsuario.getModel();
        EntidadObservableSingleton.getInstancia().agregarObservador(this);
        cargarIncidencias();
        comboBoxProyectos.addActionListener(e -> filtrarPorProyecto());
        cargarProyectosEnCombo();
    }

    @Override
    public void actualizar() {
        cargarIncidencias();
    }

    private void cargarIncidencias() {
        modelo.setRowCount(0);
        List<Incidencia> lista = incidenciaService.obtenerTodos();

        for (Incidencia i : lista) {

            // Traemos el proyecto usando el id
            Proyectos p = proyectoService.buscarPorId(i.getIdProyecto());

            modelo.addRow(new Object[]{
                i.getIdIncidencia(),
                p != null ? p.getNombre() : "Sin proyecto",
                i.getDescripcionIncidencia(),
                i.getFechaIncidencia() != null ? i.getFechaIncidencia().toLocalDate() : null,
                i.getEstadoInIncidencia(),});
        }
    }


    public class ItemCombo {

        private final int id;
        private final String nombre;

        public ItemCombo(int id, String nombre) {
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

    private void cargarProyectosEnCombo() {
        comboBoxProyectos.removeAllItems();
        itemsCombo = new ArrayList<>();

        // Primer item = ver todo
        ItemCombo todos = new ItemCombo(0, "-- Todos los proyectos --");
        itemsCombo.add(todos);
        comboBoxProyectos.addItem(todos.toString());

        // Cargar los proyectos reales
        List<Proyectos> lista = proyectoService.obtenerTodos();
        if (lista == null) {
            return;
        }

        for (Proyectos p : lista) {
            ItemCombo item = new ItemCombo(p.getIdProyecto(), p.getNombre());
            itemsCombo.add(item);
            comboBoxProyectos.addItem(item.toString());
        }
    }

    private int obtenerIdProyectoSeleccionado() {
        int idx = comboBoxProyectos.getSelectedIndex();
        if (idx < 0) {
            return 0;
        }
        return itemsCombo.get(idx).getId();
    }

    private void filtrarPorProyecto() {
        int idSeleccionado = obtenerIdProyectoSeleccionado();

        modelo.setRowCount(0);
        List<Incidencia> lista = incidenciaService.obtenerTodos();
        if (lista == null) {
            return;
        }

        for (Incidencia i : lista) {

            // si no es "todos" y no coincide el ID → saltar
            if (idSeleccionado != 0 && i.getIdProyecto() != idSeleccionado) {
                continue;
            }

            Proyectos p = proyectoService.buscarPorId(i.getIdProyecto());

            modelo.addRow(new Object[]{
                i.getIdIncidencia(),
                p != null ? p.getNombre() : "Sin proyecto",
                i.getDescripcionIncidencia(),
                i.getFechaIncidencia() != null ? i.getFechaIncidencia().toLocalDate() : null,
                i.getEstadoInIncidencia()
            });
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        comboBoxProyectos = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel15.setText("INSTRUCTOR");

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(797, 449));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tablaUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Proyecto", "Descripción", "Fecha", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("GESTIÓN DE INCIDENCIAS");

        botonRegresar.setText("Regresar");
        botonRegresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Filtrar por Proyecto:");

        jButton1.setText("Nueva Incidencia");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2)
                        .addGap(455, 455, 455)
                        .addComponent(botonRegresar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(comboBoxProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addComponent(botonRegresar))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
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

//----------------------------Llena los campos -----------------------------------
    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NuevaIncidencia nueInc = new NuevaIncidencia();
        nueInc.setVisible(true);
        nueInc.setLocationRelativeTo(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean contrasenaVisible = false;

//---------------------------Días del calendario--------------------------------  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar;
    private javax.swing.JComboBox<String> comboBoxProyectos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuario;
    // End of variables declaration//GEN-END:variables
// Método auxiliar para Test Unitario
    public int getCantidadItemsCombo() {
        return comboBoxProyectos.getItemCount();
    }
}
