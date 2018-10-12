/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Salvador Hernandez Mendoza
 */
public class ControllerAgenda {

    public ModelAgenda modelAgenda;
    public ViewAgenda viewAgenda;

    /**
     * Objeto de tipo ActionListener para atrapar los eventos actionPerformed y
     * llamar a los metodos para ver los registros almacenados en la bd.
     */
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_cancelar) {
                jbtn_cancelar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_modificar) {
                jbtn_modificar_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_borrar) {
                jbtn_borrar_actionPerformed();
            }
        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        viewAgenda.jbtn_cancelar.setEnabled(false);
        viewAgenda.jbtn_guardar.setEnabled(false);
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }

//    /**
//     * Metodo para inicializar la ViewAgenda
//     */
//    public void initComponents() {
//        viewAgenda.setLocationRelativeTo(null);
//        viewAgenda.setTitle("Agenda MVC");
//        viewAgenda.setVisible(true);
//    }
    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    private void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbtn_nuevo.addActionListener(actionListener);
        viewAgenda.jbtn_guardar.addActionListener(actionListener);
        viewAgenda.jbtn_modificar.addActionListener(actionListener);
        viewAgenda.jbtn_cancelar.addActionListener(actionListener);
        viewAgenda.jbtn_borrar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        modelAgenda.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        setValues();
    }

    /**
     * Muestra el nombre y email almacenados en el modelAgenda en el viewAgenda.
     */
    private void setValues() {
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        viewAgenda.jtf_telefono.setText(modelAgenda.getTelefono());
    }

    private void jbtn_nuevo_actionPerformed() {
        System.out.println("Action del boton jbtn_nuevo");

        int reply = JOptionPane.showConfirmDialog(null, "Agregar nuevo contacto", "Nuevo", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            viewAgenda.jbtn_primero.setEnabled(false);
            viewAgenda.jbtn_siguiente.setEnabled(false);
            viewAgenda.jbtn_anterior.setEnabled(false);
            viewAgenda.jbtn_ultimo.setEnabled(false);
            viewAgenda.jbtn_nuevo.setEnabled(false);
            viewAgenda.jbtn_borrar.setEnabled(false);
            viewAgenda.jbtn_modificar.setEnabled(false);
            viewAgenda.jbtn_guardar.setEnabled(true);
            viewAgenda.jbtn_cancelar.setEnabled(true);

            viewAgenda.jtf_email.setText("");
            viewAgenda.jtf_nombre.setText("");
            viewAgenda.jtf_telefono.setText("");
        } else {
            System.out.println("Action no de JOptionPane");
        }
    }

    private void jbtn_guardar_actionPerformed() {
        System.out.println("Action del boton jbtn_guardar");
        if (modelAgenda.esEmail(viewAgenda.jtf_email.getText())) {
            int reply = JOptionPane.showConfirmDialog(null, "¿Quiere guardar el nuevo contacto?", "Guardar", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                viewAgenda.jbtn_primero.setEnabled(true);
                viewAgenda.jbtn_siguiente.setEnabled(true);
                viewAgenda.jbtn_anterior.setEnabled(true);
                viewAgenda.jbtn_ultimo.setEnabled(true);
                viewAgenda.jbtn_nuevo.setEnabled(true);
                viewAgenda.jbtn_borrar.setEnabled(true);
                viewAgenda.jbtn_modificar.setEnabled(true);
                viewAgenda.jbtn_guardar.setEnabled(false);
                viewAgenda.jbtn_cancelar.setEnabled(false);

                modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
                modelAgenda.setEmail(viewAgenda.jtf_email.getText());
                modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());

                modelAgenda.nuevoRegistro(modelAgenda.getEmail(), modelAgenda.getNombre(), modelAgenda.getTelefono());
                initDB();
            } else {
                System.out.println("Action no de JOptionPane");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El correo es invalido");
        }
    }

    private void jbtn_cancelar_actionPerformed() {
        System.out.println("Action del boton jbtn_cancelar");

        int reply = JOptionPane.showConfirmDialog(null, "¿Quiere cancelar el registro?", "Cancelar", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {

            viewAgenda.jbtn_primero.setEnabled(true);
            viewAgenda.jbtn_siguiente.setEnabled(true);
            viewAgenda.jbtn_anterior.setEnabled(true);
            viewAgenda.jbtn_ultimo.setEnabled(true);
            viewAgenda.jbtn_nuevo.setEnabled(true);
            viewAgenda.jbtn_borrar.setEnabled(true);
            viewAgenda.jbtn_modificar.setEnabled(true);
            viewAgenda.jbtn_guardar.setEnabled(false);
            viewAgenda.jbtn_cancelar.setEnabled(false);

            initDB();

            this.initDB();
        } else {
            System.out.println("Action no de JOptionPane");
        }
    }

    private void jbtn_modificar_actionPerformed() {
        if (modelAgenda.esEmail(viewAgenda.jtf_email.getText())) {
            int reply = JOptionPane.showConfirmDialog(null, "¿Desea modificar el registro?", "Modifcar", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
                modelAgenda.setEmail(viewAgenda.jtf_email.getText());
                modelAgenda.setTelefono(viewAgenda.jtf_telefono.getText());
                modelAgenda.cambiarRegistro(modelAgenda.getEmail(), modelAgenda.getNombre(), modelAgenda.getTelefono());
                initDB();
            } else {
                System.out.println("Action no de JOptionPane");
            }
        } else {
            JOptionPane.showMessageDialog(null, "El campo de correo es invalido");
        }
    }

    private void jbtn_borrar_actionPerformed() {
        System.out.println("Action del boton jbtn_borrar");
        if (modelAgenda.esEmail(viewAgenda.jtf_email.getText())) {
            int reply = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres borrar el contacto?", "Borrar", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                modelAgenda.setEmail(viewAgenda.jtf_email.getText());
                modelAgenda.borrarRegistro(modelAgenda.getEmail());
                initDB();
            } else {
                System.out.println("Action no de JOptionPane");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ingresa un correo valido para poder eliminar el registro");
        }
    }
}
