package lab1proyectotransversal.vistas;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import lab1proyectotransversal.accesoADatos.*;
import lab1proyectotransversal.entidades.*;

/**
 *
 * @author Grupo-3
 */
public class ManejoInscripcion extends javax.swing.JInternalFrame {

    private final AlumnoData alumnoData;
    private final MateriaData materiaData;
    private final InscripcionData inscripcionData;
    private DefaultTableModel modelo = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int i, int i1) {
            return false;
        }
    };

    /**
     * Creates new form ManejoInscripciones
     */
    public ManejoInscripcion(AlumnoData alumnoData, MateriaData materiaData, InscripcionData inscripcionData) {
        initComponents();
        this.alumnoData = alumnoData;
        this.materiaData = materiaData;
        this.inscripcionData = inscripcionData;
        armarCabeceraTabla();
    }

    private void configurarComboBox() {
        
        List<Alumno> listaAlumnos = alumnoData.listarAlumnosSegunEstado(true);  // Se obtienen todos los alumnos que están activos
        jcbAlumSeleccion.removeAllItems();      // Se eliminan todos los items de 'jcbAlumSeleccion' (si realmente habían items que borrar, entonces este método genera provoca la ejecución de 'jcbAlumSeleccionActionPerformed()')

        if (listaAlumnos.isEmpty()) {       // Si no hay ni un alumno activo...

            JOptionPane.showMessageDialog(this, "No hay alumnos a los cuales inscribir o desinscribir en materias.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            System.out.println("No hay alumnos a los cuales inscribir o desinscribir en materias.");    // Para depurar
            
            jcbAlumSeleccion.setSelectedIndex(-1);  // Se necesita para provocar la ejecución de 'jcbAlumSeleccionActionPerformed()' cuando se ejecuta este método por primera vez luego de crear 'ManejoInscripcion'

        } else {    // Si hay uno o más alumnos activos...

            // Se añaden los alumnos a 'jcbAlumSeleccion'
            for (Alumno alum : listaAlumnos) {
                jcbAlumSeleccion.addItem(alum);
            }
        }
    }

    private void armarCabeceraTabla() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Año");
        jtListadoMaterias.setModel(modelo);
    }

    private void adminDesinscripcion() {

        // Borrar las columnas de la tabla
        modelo.setRowCount(0);

        if (jcbAlumSeleccion.getSelectedIndex() != -1) {    // Si hay al menos un alumno (y, por ende, 'jcbAlumSeleccion' no está vacío)

            // Se obtiene el item selecionado en 'jcbAlumSeleccion' y las materias que cursa
            Alumno alumno = (Alumno) jcbAlumSeleccion.getSelectedItem();
            List<Materia> listaMaterias = inscripcionData.obtenerMateriasCursadas(alumno.getIdAlumno());

            if (listaMaterias.isEmpty()) {  // Si 'alumno' no cursa ninguna materia

                // No se puede desinscribir a 'alumno' de una materia que curse
                jbAnularInscrip.setEnabled(false);

            } else {    // Si 'alumno' cursa una o más materias

                // Se muestran las materias en 'jtListadoMaterias'
                for (Materia mat : listaMaterias) {
                    modelo.addRow(new Object[]{mat.getIdMateria(), mat.getNombre(), mat.getAnio()});
                }

                // Se puede desincribir a 'alumno' en una materia
                jbAnularInscrip.setEnabled(true);
            }
        } else {    // Si no hay alumnos (y, por ende, 'jcbAlumSeleccion' está vacío)

            // No se puede desincribir a un alumno que no existe de una materia
            jbAnularInscrip.setEnabled(false);
        }

        // No se puede inscribir a un alumno en una materia en la que ya está inscripto
        jbInscribir.setEnabled(false);

    }

    private void adminInscripcion() {

        // Borrar las columnas de la tabla
        modelo.setRowCount(0);

        if (jcbAlumSeleccion.getSelectedIndex() != -1) {    // Si hay al menos un alumno (y, por ende, 'jcbAlumSeleccion' no está vacío)

            // Se obtiene el item selecionado en 'jcbAlumSeleccion' y las materias que NO cursa
            Alumno alumno = (Alumno) jcbAlumSeleccion.getSelectedItem();
            List<Materia> listaMatNOCursadas = inscripcionData.obtenerMateriasNOCursadas(alumno.getIdAlumno());

            if (listaMatNOCursadas.isEmpty()) {     // Si 'alumno' cursa todas las materias

                // No se puede inscribir a 'alumno' en una materia que no curse
                jbInscribir.setEnabled(false);

            } else {    // Si existe una o más materias que 'alumno' no curse

                // Se muestran las materias en 'jtListadoMaterias'
                for (Materia mat : listaMatNOCursadas) {
                    modelo.addRow(new Object[]{mat.getIdMateria(), mat.getNombre(), mat.getAnio()});
                }

                // Se puede inscribir a 'alumno' en una materia que no curse
                jbInscribir.setEnabled(true);
            }
            
        } else {    // Si no hay alumnos (y, por ende, 'jcbAlumSeleccion' está vacío)
            
            // No se puede inscribir a un alumno que no existe en una materia
            jbInscribir.setEnabled(false);
        }
        
        // No se puede desinscribir a un alumno en una materia que no cursa
        jbAnularInscrip.setEnabled(false);

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
        jlTitulo = new javax.swing.JLabel();
        jlAlumSeleccion = new javax.swing.JLabel();
        jcbAlumSeleccion = new javax.swing.JComboBox<>();
        jlListaMaterias = new javax.swing.JLabel();
        jrbMatInscrip = new javax.swing.JRadioButton();
        jrbMatNoInscrip = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtListadoMaterias = new javax.swing.JTable();
        jbInscribir = new javax.swing.JButton();
        jbAnularInscrip = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(517, 444));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jlTitulo.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jlTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlTitulo.setText("Formulario de Inscripción");

        jlAlumSeleccion.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jlAlumSeleccion.setText("Alumno seleccionado:");

        jcbAlumSeleccion.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jcbAlumSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlumSeleccionActionPerformed(evt);
            }
        });

        jlListaMaterias.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jlListaMaterias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlListaMaterias.setText("Listado de Materias");

        materiasGrupo.add(jrbMatInscrip);
        jrbMatInscrip.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jrbMatInscrip.setText("Materias Inscriptas");
        jrbMatInscrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMatInscripActionPerformed(evt);
            }
        });

        materiasGrupo.add(jrbMatNoInscrip);
        jrbMatNoInscrip.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jrbMatNoInscrip.setSelected(true);
        jrbMatNoInscrip.setText("Materias no insciptas");
        jrbMatNoInscrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMatNoInscripActionPerformed(evt);
            }
        });

        jtListadoMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtListadoMaterias);

        jbInscribir.setText("Inscribir");
        jbInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInscribirActionPerformed(evt);
            }
        });

        jbAnularInscrip.setText("Anular Inscripcion");
        jbAnularInscrip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularInscripActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
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
                        .addComponent(jlTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jlAlumSeleccion)
                        .addGap(18, 18, 18)
                        .addComponent(jcbAlumSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 107, Short.MAX_VALUE))
                    .addComponent(jlListaMaterias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jrbMatInscrip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jrbMatNoInscrip)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jbInscribir)
                .addGap(42, 42, 42)
                .addComponent(jbAnularInscrip)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSalir)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAlumSeleccion)
                    .addComponent(jcbAlumSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jlListaMaterias)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jrbMatInscrip)
                    .addComponent(jrbMatNoInscrip))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbInscribir)
                    .addComponent(jbAnularInscrip)
                    .addComponent(jbSalir))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jrbMatNoInscripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMatNoInscripActionPerformed

        // Manejar inscripciones
        adminInscripcion();
    }//GEN-LAST:event_jrbMatNoInscripActionPerformed

    private void jcbAlumSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlumSeleccionActionPerformed

        /*
        *   Cuando se actualiza 'jcbAlumSeleccion' y se eliminan todos los alumnos del mismo (si los hubiera) 
        *   y se agregan los nuevos alumnos al mismo (si los hubiera) se activa este método, el cual determina qué se muestra en
        *   la tabla y qué botones se habilitan, en función del jrb que esté seleccionado en ese momento      
         */
        if (jrbMatInscrip.isSelected()) {
            adminDesinscripcion();
        } else {
            adminInscripcion();
        }
    }//GEN-LAST:event_jcbAlumSeleccionActionPerformed

    private void jrbMatInscripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMatInscripActionPerformed

        // Manejar desinscripciones
        adminDesinscripcion();
    }//GEN-LAST:event_jrbMatInscripActionPerformed

    private void jbInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInscribirActionPerformed

        // Se obtiene el item selecionado en 'jcbAlumSeleccion'
        Alumno alumno = (Alumno) jcbAlumSeleccion.getSelectedItem();

        // Según la cantidad de filas seleccionadas en 'jtListadoMaterias'
        switch (jtListadoMaterias.getSelectedRowCount()) {

            // Si no se seleccionó ninguna fila
            case 0:

                // Avisar al usuario que debe seleccionar una fila
                JOptionPane.showMessageDialog(this, """
                                                    Para inscribir al alumno en una materia primero debe 
                                                    seleccionar la misma en la lista, haciendole click.""", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;

            // Si se seleccionó una única fila
            case 1:

                // Se obtiene el índice de la fila seleccionada y se extraen los datos de la misma para crear 'materia'
                int filaSelec = jtListadoMaterias.getSelectedRow();
                int idMat = (int) jtListadoMaterias.getValueAt(filaSelec, 0);
                String nombreMat = (String) jtListadoMaterias.getValueAt(filaSelec, 1);
                int anio = (int) jtListadoMaterias.getValueAt(filaSelec, 2);
                Materia materia = new Materia(idMat, nombreMat, anio, true);

                // Se crea 'inscripcion' con 'alumno', 'materia' y 0 como nota
                Inscripcion inscripcion = new Inscripcion(alumno, materia, 0);

                if (inscripcionData.guardarInscripcion(inscripcion)) {  // Si 'inscripcion' es guardado

                    // Se actualiza el contenido de 'jtListadoMaterias' y los botones 'jbAnularInscrip' y 'jbInscribir'
                    adminInscripcion();

                    // Se comunica el resultado al usuario
                    JOptionPane.showMessageDialog(this, """
                                                        Se ha inscripto al alumno con 
                                                        DNI: """ + alumno.getDni() + " en " + materia.getNombre(), "Información", JOptionPane.INFORMATION_MESSAGE);

                } else {    // Si no se pudo guardar 'inscripcion'

                    // Se comunica el resultado al usuario
                    JOptionPane.showMessageDialog(this, """
                                                        ERROR: No se ha podido inscribir al alumno 
                                                        con DNI: """ + alumno.getDni() + " en " + materia.getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            // Si se seleccionó más de una fila
            default:

                // Avisar al usuario de que debe seleccionar sólo una fila
                JOptionPane.showMessageDialog(this, """
                                                    Debe seleccionar sólo una materia de la lista, haciendo click
                                                    en la misma.""", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbInscribirActionPerformed

    private void jbAnularInscripActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularInscripActionPerformed

        // Se obtiene el item selecionado en 'jcbAlumSeleccion'
        Alumno alumno = (Alumno) jcbAlumSeleccion.getSelectedItem();

        // Según la cantidad de filas seleccionadas en 'jtListadoMaterias'
        switch (jtListadoMaterias.getSelectedRowCount()) {

            // Si no se seleccionó ninguna fila
            case 0:

                // Avisar al usuario que debe seleccionar una fila
                JOptionPane.showMessageDialog(this, """
                                                    Para desinscribir al alumno en una materia primero debe 
                                                    seleccionar la misma en la lista, haciendole click.""", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;

            // Si se seleccionó una única fila
            case 1:

                // Se obtiene el índice de la fila seleccionada y se extraen algunos datos de la misma
                int filaSelec = jtListadoMaterias.getSelectedRow();
                String nombreMat = (String) jtListadoMaterias.getValueAt(filaSelec, 1);
                int idMat = (int) jtListadoMaterias.getValueAt(filaSelec, 0);

                if (inscripcionData.borrarInscripcionMateriaAlumno(alumno.getIdAlumno(), idMat)) {      // Si se borró la inscripción

                    // Se actualiza el contenido de 'jtListadoMaterias' y los botones 'jbAnularInscrip' y 'jbInscribir'
                    adminDesinscripcion();

                    // Se comunica el resultado al usuario
                    JOptionPane.showMessageDialog(this, """
                                                        Se ha borrado la inscripción del alumno con 
                                                        DNI: """ + alumno.getDni() + " en " + nombreMat, "Información", JOptionPane.INFORMATION_MESSAGE);

                } else {    // Si no se pudo borrar la inscripción

                    // Se comunica el resultado al usuario
                    JOptionPane.showMessageDialog(this, """
                                                        ERROR: No se ha podido borrar la inscripción del alumno 
                                                        con DNI: """ + alumno.getDni() + " en " + nombreMat, "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            // Si se seleccionó más de una fila
            default:

                // Avisar al usuario de que debe seleccionar sólo una fila
                JOptionPane.showMessageDialog(this, """
                                                    Debe seleccionar sólo una materia de la lista, haciendo click
                                                    en la misma.""", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbAnularInscripActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        this.hide();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

        /*
        *   Cada vez que este JInternalFram se seleccione desde la barra de menú, este método se ejecuta        
         */
        // Actualizar el contenido de 'jcbAlumSeleccion' 
        configurarComboBox();

        // Si, directamente, no hay ninguna materia (activa), entonces advertir al usuario
        List<Materia> listaMaterias = materiaData.listarMateriasSegunEstado(true);
        
        if (listaMaterias.isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "No hay materias en las cuales inscribir o desinscribir alumnos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            System.out.println("No hay materias en las cuales inscribir o desinscribir alumnos.");
        }

    }//GEN-LAST:event_formInternalFrameActivated


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbAnularInscrip;
    private javax.swing.JButton jbInscribir;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Alumno> jcbAlumSeleccion;
    private javax.swing.JLabel jlAlumSeleccion;
    private javax.swing.JLabel jlListaMaterias;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JRadioButton jrbMatInscrip;
    private javax.swing.JRadioButton jrbMatNoInscrip;
    private javax.swing.JTable jtListadoMaterias;
    private javax.swing.ButtonGroup materiasGrupo;
    // End of variables declaration//GEN-END:variables
}
