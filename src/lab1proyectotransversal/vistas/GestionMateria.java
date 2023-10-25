package lab1proyectotransversal.vistas;

import javax.swing.JOptionPane;
import lab1proyectotransversal.accesoADatos.MateriaData;
import lab1proyectotransversal.entidades.Materia;

/**
 *
 * @author Grupo-3
 */
public class GestionMateria extends javax.swing.JInternalFrame {

    MateriaData materiaData;

    /**
     * Creates new form GestionMaterias
     */
    public GestionMateria(MateriaData materiaData) {
        initComponents();
        this.materiaData = materiaData;
    }

    private void limpiarCampos() {
        codigoTextField.setText("");
        nombreTextField.setText("");
        anioTextField.setText("");
        estadoRadioButton.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        codigoLabel = new javax.swing.JLabel();
        codigoTextField = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        nombreTextField = new javax.swing.JTextField();
        anioLabel = new javax.swing.JLabel();
        anioTextField = new javax.swing.JTextField();
        estadoLabel = new javax.swing.JLabel();
        estadoRadioButton = new javax.swing.JRadioButton();
        nuevoButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        guardarButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        buscarButton = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Materias");

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Materia");
        titulo.setToolTipText("");

        codigoLabel.setText("Codigo:");

        nombreLabel.setText("Nombre:");

        anioLabel.setText("Año:");

        estadoLabel.setText("Estado:");

        estadoRadioButton.setSelected(true);

        nuevoButton.setText("Nuevo");
        nuevoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoButtonActionPerformed(evt);
            }
        });

        eliminarButton.setText("Eliminar");
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        guardarButton.setText("Guardar");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codigoLabel)
                            .addComponent(nombreLabel)
                            .addComponent(anioLabel)
                            .addComponent(estadoLabel))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(codigoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(estadoRadioButton)
                            .addComponent(nombreTextField)
                            .addComponent(anioTextField))
                        .addGap(18, 18, 18)
                        .addComponent(buscarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevoButton)
                        .addGap(18, 18, 18)
                        .addComponent(eliminarButton)
                        .addGap(18, 18, 18)
                        .addComponent(guardarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                        .addComponent(salirButton)))
                .addGap(24, 30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoLabel)
                    .addComponent(codigoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anioLabel)
                    .addComponent(anioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(estadoLabel)
                    .addComponent(estadoRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuevoButton)
                    .addComponent(eliminarButton)
                    .addComponent(guardarButton)
                    .addComponent(salirButton))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        int idMateria;

        try {
            idMateria = Integer.parseInt(codigoTextField.getText());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El codigo debe ser un número entero sin decimales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar materia
        Materia materia = materiaData.buscarMateria(idMateria);
        if (materia == null) {
            // No se encontró
            nombreTextField.setText("");
            anioTextField.setText("");
            estadoRadioButton.setSelected(false);
            JOptionPane.showMessageDialog(this, "No se ha encontrado la materia.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Se encontró
            nombreTextField.setText(materia.getNombre());
            anioTextField.setText(materia.getAnio() + "");
            estadoRadioButton.setSelected(materia.isEstado());            
        }
    }//GEN-LAST:event_buscarButtonActionPerformed

    private void nuevoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoButtonActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_nuevoButtonActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        int idMateria;

        try {
            idMateria = Integer.parseInt(codigoTextField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero sin decimales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Materia materia = materiaData.buscarMateria(idMateria);
        if (materia == null) {
            // No encontrado, salir
            JOptionPane.showMessageDialog(this, "No hay materia con este Código.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            // Encontrado, pero ya está dado de baja. Salir
            if (materia.isEstado() == false) {
                JOptionPane.showMessageDialog(this, "Esta materia ya está dado de baja.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }

        // Se encontró
        if (materiaData.eliminarMateria(materia.getIdMateria())) {
            limpiarCampos();
            JOptionPane.showMessageDialog(this, "Materia eliminada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Se encontró pero no se pudo concretar la eliminación
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la materia.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed

        String codigo = codigoTextField.getText();
        String nombre = nombreTextField.getText();
        String anio = anioTextField.getText();
        boolean estado = estadoRadioButton.isSelected();
        System.out.println(estado);

        if (codigo.isBlank() || nombre.isBlank() || anio.isBlank()) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser rellenados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //validar el id de la materia
        int idMateria, anioMat;
        try {
            idMateria = Integer.parseInt(codigo);            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El código debe ser un número entero sin decimales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }        
        try {
            anioMat = Integer.parseInt(anio);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número entero sin decimales.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Buscar materia
        Materia materia = materiaData.buscarMateria(idMateria);

        // Guardar resultado posterior del SQL
        boolean result;

        if (materia == null) {
            // No se encontró una materia con ese ID. Se crea una nueva
            materia = new Materia(idMateria, nombre, anioMat, estado);
            result = materiaData.guardarMateria(materia);
        } else {
            // Se encontró una materia con ese ID. Se modifica 
            materia.setIdMateria(idMateria);
            materia.setNombre(nombre);
            materia.setAnio(anioMat);
            materia.setEstado(estado);
            result = materiaData.modificarMateria(materia);
        }
        
        // Imprimir resultado SQL (creación o actualización)
        if (result) {
            JOptionPane.showMessageDialog(this, "Materia guardada.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar la materia.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        this.hide();
    }//GEN-LAST:event_salirButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anioLabel;
    private javax.swing.JTextField anioTextField;
    private javax.swing.JButton buscarButton;
    private javax.swing.JLabel codigoLabel;
    private javax.swing.JTextField codigoTextField;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JRadioButton estadoRadioButton;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JButton nuevoButton;
    private javax.swing.JButton salirButton;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
