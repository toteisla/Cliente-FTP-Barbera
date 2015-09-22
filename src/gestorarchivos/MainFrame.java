package gestorarchivos;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int ANCHO = screenSize.width;
    private final int ALTO = screenSize.height;
    public MainFrame() {
        initComponents();
        this.setSize(ANCHO,ALTO-20);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        scrollSubidos = new javax.swing.JScrollPane();
        tablaSubidos = new javax.swing.JTable();
        scrollActualizados = new javax.swing.JScrollPane();
        tablaActualizados = new javax.swing.JTable();
        scrollBorrados = new javax.swing.JScrollPane();
        tablaBorrados = new javax.swing.JTable();
        barraTotal = new javax.swing.JProgressBar();
        Estado = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTiempo = new javax.swing.JLabel();
        botonBorrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AFT Software Group - Gestion de Archvios Pinturas Barberá");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        boton.setText("Actualizar");
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });

        tablaSubidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Proveedor", "Familia", "Tipo", "Ficha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollSubidos.setViewportView(tablaSubidos);
        tablaSubidos.getColumnModel().getColumn(0).setMinWidth(85);
        tablaSubidos.getColumnModel().getColumn(0).setPreferredWidth(85);
        tablaSubidos.getColumnModel().getColumn(0).setMaxWidth(85);
        tablaSubidos.getColumnModel().getColumn(1).setMinWidth(1);
        tablaSubidos.getColumnModel().getColumn(1).setPreferredWidth(500);
        tablaSubidos.getColumnModel().getColumn(1).setMaxWidth(500);
        tablaSubidos.getColumnModel().getColumn(2).setMinWidth(50);
        tablaSubidos.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablaSubidos.getColumnModel().getColumn(2).setMaxWidth(50);
        tablaSubidos.getColumnModel().getColumn(3).setMinWidth(50);
        tablaSubidos.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablaSubidos.getColumnModel().getColumn(3).setMaxWidth(50);
        tablaSubidos.getColumnModel().getColumn(4).setMinWidth(100);
        tablaSubidos.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaSubidos.getColumnModel().getColumn(4).setMaxWidth(100);

        jTabbedPane1.addTab("Nuevos", scrollSubidos);

        tablaActualizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Proveedor", "Familia", "Tipo", "Ficha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaActualizados.getTableHeader().setReorderingAllowed(false);
        scrollActualizados.setViewportView(tablaActualizados);
        tablaActualizados.getColumnModel().getColumn(0).setMinWidth(85);
        tablaActualizados.getColumnModel().getColumn(0).setPreferredWidth(85);
        tablaActualizados.getColumnModel().getColumn(0).setMaxWidth(85);
        tablaActualizados.getColumnModel().getColumn(2).setMinWidth(50);
        tablaActualizados.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablaActualizados.getColumnModel().getColumn(2).setMaxWidth(50);
        tablaActualizados.getColumnModel().getColumn(3).setMinWidth(50);
        tablaActualizados.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablaActualizados.getColumnModel().getColumn(3).setMaxWidth(50);
        tablaActualizados.getColumnModel().getColumn(4).setMinWidth(100);
        tablaActualizados.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaActualizados.getColumnModel().getColumn(4).setMaxWidth(100);

        jTabbedPane1.addTab("Actualizados", scrollActualizados);

        tablaBorrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Proveedor", "Familia", "Tipo", "Ficha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaBorrados.getTableHeader().setReorderingAllowed(false);
        scrollBorrados.setViewportView(tablaBorrados);
        tablaBorrados.getColumnModel().getColumn(0).setMinWidth(85);
        tablaBorrados.getColumnModel().getColumn(0).setPreferredWidth(85);
        tablaBorrados.getColumnModel().getColumn(0).setMaxWidth(85);
        tablaBorrados.getColumnModel().getColumn(2).setMinWidth(50);
        tablaBorrados.getColumnModel().getColumn(2).setPreferredWidth(50);
        tablaBorrados.getColumnModel().getColumn(2).setMaxWidth(50);
        tablaBorrados.getColumnModel().getColumn(3).setMinWidth(50);
        tablaBorrados.getColumnModel().getColumn(3).setPreferredWidth(50);
        tablaBorrados.getColumnModel().getColumn(3).setMaxWidth(50);
        tablaBorrados.getColumnModel().getColumn(4).setMinWidth(100);
        tablaBorrados.getColumnModel().getColumn(4).setPreferredWidth(100);
        tablaBorrados.getColumnModel().getColumn(4).setMaxWidth(100);

        jTabbedPane1.addTab("Borrados", scrollBorrados);

        Estado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Estado.setText("Pulse el boton para inicar.");

        lblTotal.setText("Progreso total:");

        lblTiempo.setText("Tiempo Estimado:");

        botonBorrar.setBackground(new java.awt.Color(255, 0, 0));
        botonBorrar.setText("Borrar y Actualizar");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .addComponent(Estado, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                                .addComponent(lblTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(barraTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addComponent(botonBorrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boton)
                    .addComponent(botonBorrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Estado)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(lblTiempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(barraTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        
        this.boton.setEnabled(false);
        this.botonBorrar.setEnabled(false);
        HiloSubidos hs = new HiloSubidos(this,0);
        hs.start();
    }//GEN-LAST:event_botonActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea borrar la Base De Datos completamente?");
        if(result == 0){
            this.boton.setEnabled(false);
            this.botonBorrar.setEnabled(false);
            HiloSubidos hs = new HiloSubidos(this,1);
            hs.start();
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        SQL sql =new SQL();
        sql.actualizaBaseDatosWeb("UPDATE actualizando SET actualizando=0");
    }//GEN-LAST:event_formWindowClosing
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel Estado;
    public javax.swing.JProgressBar barraTotal;
    public javax.swing.JButton boton;
    public javax.swing.JButton botonBorrar;
    public javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JLabel lblTiempo;
    public javax.swing.JLabel lblTotal;
    public javax.swing.JScrollPane scrollActualizados;
    public javax.swing.JScrollPane scrollBorrados;
    public javax.swing.JScrollPane scrollSubidos;
    public javax.swing.JTable tablaActualizados;
    public javax.swing.JTable tablaBorrados;
    public javax.swing.JTable tablaSubidos;
    // End of variables declaration//GEN-END:variables

}
