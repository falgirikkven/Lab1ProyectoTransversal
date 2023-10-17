package lab1proyectotransversal.vistas;

import lab1proyectotransversal.accesoADatos.AlumnoData;
import lab1proyectotransversal.accesoADatos.InscripcionData;

/**
 *
 * @author Grupo-3
 */
public class ManejoInscripcion extends javax.swing.JInternalFrame {

    AlumnoData alumnoData;
    InscripcionData inscripcionData;

    /**
     * Creates new form ManejoInscripciones
     */
    public ManejoInscripcion(AlumnoData alumnoData, InscripcionData inscripcionData) {
        initComponents();
        this.alumnoData = alumnoData;
        this.inscripcionData = inscripcionData;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        materiasGrupo = new javax.swing.ButtonGroup();
        titulo = new javax.swing.JLabel();
        alumnoSeleccionLabel = new javax.swing.JLabel();
        alumnoSeleccionCB = new javax.swing.JComboBox<>();
        listaMateriasLabel = new javax.swing.JLabel();
        materiasInscriptasRadioButton = new javax.swing.JRadioButton();
        materiasNoInscriptasRadioButton = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        inscribirButton = new javax.swing.JButton();
        desinscribirButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Formulario de Inscripción");

        alumnoSeleccionLabel.setText("Alumno seleccionado:");

        listaMateriasLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        listaMateriasLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        listaMateriasLabel.setText("Listado de Materias");

        materiasGrupo.add(materiasInscriptasRadioButton);
        materiasInscriptasRadioButton.setText("Materias Inscriptas");

        materiasGrupo.add(materiasNoInscriptasRadioButton);
        materiasNoInscriptasRadioButton.setText("Materias no insciptas");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Materia ID", "Nombre", "Año"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        inscribirButton.setText("Inscribir");

        desinscribirButton.setText("Anular Inscripcion");

        salirButton.setText("Salir");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(alumnoSeleccionLabel)
                        .addGap(18, 18, 18)
                        .addComponent(alumnoSeleccionCB, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(listaMateriasLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(materiasInscriptasRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(materiasNoInscriptasRadioButton)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(inscribirButton)
                .addGap(18, 18, 18)
                .addComponent(desinscribirButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(salirButton)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alumnoSeleccionLabel)
                    .addComponent(alumnoSeleccionCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(listaMateriasLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(materiasInscriptasRadioButton)
                    .addComponent(materiasNoInscriptasRadioButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inscribirButton)
                    .addComponent(desinscribirButton)
                    .addComponent(salirButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> alumnoSeleccionCB;
    private javax.swing.JLabel alumnoSeleccionLabel;
    private javax.swing.JButton desinscribirButton;
    private javax.swing.JButton inscribirButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel listaMateriasLabel;
    private javax.swing.ButtonGroup materiasGrupo;
    private javax.swing.JRadioButton materiasInscriptasRadioButton;
    private javax.swing.JRadioButton materiasNoInscriptasRadioButton;
    private javax.swing.JButton salirButton;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
