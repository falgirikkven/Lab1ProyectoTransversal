package lab1proyectotransversal.vistas;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import lab1proyectotransversal.accesoADatos.AlumnoData;
import lab1proyectotransversal.accesoADatos.Conexion;
import lab1proyectotransversal.accesoADatos.InscripcionData;
import lab1proyectotransversal.accesoADatos.MateriaData;

/**
 *
 * @author Grupo-3
 */
public class MainFrame extends javax.swing.JFrame {

    JInternalFrame focusedFrame = null;
    GestionAlumno gestionAlumno;
    GestionMateria gestionMateria;
    ManejoInscripcion manejoInscripcion;
    ManipulacionNotas manipulacionNotas;
    ConsultaAlumnosPorMateria consultaAlumnosPorMateria;

    AlumnoData alumnoData;
    MateriaData materiaData;
    InscripcionData inscripcionData;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        Connection connection = Conexion.getInstance();     // PARA QUÉ SE USA?
        this.alumnoData = new AlumnoData();
        this.materiaData = new MateriaData();
        this.inscripcionData = new InscripcionData(materiaData, alumnoData);

        // Alumnos
        gestionAlumno = new GestionAlumno(alumnoData);
        jDesktopPane1.add(gestionAlumno);

        // Materias
        gestionMateria = new GestionMateria(materiaData);
        jDesktopPane1.add(gestionMateria);

        // Inscripciones
        manejoInscripcion = new ManejoInscripcion(alumnoData, inscripcionData, materiaData);
        jDesktopPane1.add(manejoInscripcion);

        // Notas
        manipulacionNotas = new ManipulacionNotas(alumnoData, inscripcionData);
        jDesktopPane1.add(manipulacionNotas);

        // Consulta Alumnos por materia
        consultaAlumnosPorMateria = new ConsultaAlumnosPorMateria(materiaData, inscripcionData);
        jDesktopPane1.add(consultaAlumnosPorMateria);

    }

    private void focusIFrame(JInternalFrame iFrame) {

        if (focusedFrame != null) {
            focusedFrame.hide();
        }

        focusedFrame = iFrame;
        focusedFrame.setVisible(true);
        focusedFrame.setLocation(0, 0);
        focusedFrame.moveToFront();
        try {
            focusedFrame.setSelected(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
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

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        alumnoMenu = new javax.swing.JMenu();
        alumnoFormulario = new javax.swing.JMenuItem();
        materiaMenu = new javax.swing.JMenu();
        materiaFormulario = new javax.swing.JMenuItem();
        administracionMenu = new javax.swing.JMenu();
        administracionInscripciones = new javax.swing.JMenuItem();
        administracionNotas = new javax.swing.JMenuItem();
        consultasMenu = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jDesktopPane1.setPreferredSize(new java.awt.Dimension(640, 457));

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        alumnoMenu.setText("Alumno");
        alumnoMenu.setToolTipText("");

        alumnoFormulario.setText("Formulario de Alumno");
        alumnoFormulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alumnoFormularioActionPerformed(evt);
            }
        });
        alumnoMenu.add(alumnoFormulario);

        jMenuBar1.add(alumnoMenu);

        materiaMenu.setText("Materia");

        materiaFormulario.setText("Formulario de Materia");
        materiaFormulario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                materiaFormularioActionPerformed(evt);
            }
        });
        materiaMenu.add(materiaFormulario);

        jMenuBar1.add(materiaMenu);

        administracionMenu.setText("Admistración");

        administracionInscripciones.setText("Manejo de Inscripciones");
        administracionInscripciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administracionInscripcionesActionPerformed(evt);
            }
        });
        administracionMenu.add(administracionInscripciones);

        administracionNotas.setText("Manipulacioón de notas");
        administracionNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administracionNotasActionPerformed(evt);
            }
        });
        administracionMenu.add(administracionNotas);

        jMenuBar1.add(administracionMenu);

        consultasMenu.setText("Consultas");

        jMenuItem5.setText("Alumnos por Materia");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        consultasMenu.add(jMenuItem5);

        jMenuBar1.add(consultasMenu);

        salir.setText("Salir");
        jMenuBar1.add(salir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void alumnoFormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alumnoFormularioActionPerformed
        focusIFrame(gestionAlumno);
    }//GEN-LAST:event_alumnoFormularioActionPerformed

    private void materiaFormularioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_materiaFormularioActionPerformed
        focusIFrame(gestionMateria);
    }//GEN-LAST:event_materiaFormularioActionPerformed

    private void administracionInscripcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administracionInscripcionesActionPerformed
        focusIFrame(manejoInscripcion);
    }//GEN-LAST:event_administracionInscripcionesActionPerformed

    private void administracionNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administracionNotasActionPerformed
        focusIFrame(manipulacionNotas);
    }//GEN-LAST:event_administracionNotasActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        focusIFrame(consultaAlumnosPorMateria);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void ejecutar(String args[]) {
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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem administracionInscripciones;
    private javax.swing.JMenu administracionMenu;
    private javax.swing.JMenuItem administracionNotas;
    private javax.swing.JMenuItem alumnoFormulario;
    private javax.swing.JMenu alumnoMenu;
    private javax.swing.JMenu consultasMenu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem materiaFormulario;
    private javax.swing.JMenu materiaMenu;
    private javax.swing.JMenu salir;
    // End of variables declaration//GEN-END:variables
}
