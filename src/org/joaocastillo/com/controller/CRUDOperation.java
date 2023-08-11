package org.joaocastillo.com.controller;

import org.joaocastillo.com.view.components.GeneralModelComponent;
import org.joaocastillo.com.view.components.PopUp;

import javax.swing.JOptionPane;

public class CRUDOperation {
    private final GeneralModelComponent component;

    public CRUDOperation(GeneralModelComponent component) {
        this.component = component;
    }

    // public
    public void onCreate() {
        component.formOperations.enableFields();
        if (component.btnSave.getText().equals("Guardar")) onSave();
        toggleImage("Nuevo");
        toggleImageCancel();
        component.currentOperation = () -> component.getDAO().save(component.getModel());
    }

    public void onCancel() {
        System.out.println("OnCancelDelete Clicked");
        component.formOperations.disableFields();
        component.formOperations.clearFields();
        System.out.println(component.btnDelete.getText());
        if (component.btnDelete.getText().equals("Eliminar") && component.getModelID() == null) return;
        if (component.btnDelete.getText().equals("Eliminar")) {
            onDelete();
            return;
        }
        System.out.println("cancel");
        toggleImageCancel();
        toggleImage("Nuevo");
    }

    public void onEdit() {
        if (component.getSelectedModel() == null) return;
        component.formOperations.enableFields();
        component.setFields(component.getSelectedModel());
        if (component.btnSave.getText().equals("Guardar")) return;
        toggleImage("Modificar");
        toggleImageCancel();
        component.currentOperation = () -> component.getDAO().update(component.getModel());
    }


    // private
    private void onSave() {
        try {
            if (component.formOperations.areFieldsEmpty()) throw new Exception("Fields are empty");
            component.currentOperation.operate();
            component.formOperations.clearFields();
            component.formOperations.disableFields();
            component.fetchData();
//            btnSave.setText("Nuevo");
        } catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e.getMessage());
            new PopUp(e);
            return;
        }

        new PopUp();
    }

    private void onDelete() {
        try {
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar este registro?" + "\n esta tabla puede ser una llave foranea", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) return;
            component.getDAO().delete(component.getModelID());
            component.fetchData();
        } catch (Exception e) {
            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, e.getMessage());
            new PopUp(e);
            return;
        }

        new PopUp();
    }

    private void toggleImageCancel() {
        if (component.imgDeleteCancel.getImage() == component.deleteImage) {
            component.imgDeleteCancel.setImage(component.cancelImage);
            component.btnDelete.setText("Cancelar");
            return;
        }

        if (component.imgDeleteCancel.getImage() == component.cancelImage) {
            component.imgDeleteCancel.setImage(component.deleteImage);
            component.btnDelete.setText("Eliminar");
            component.formOperations.disableFields();
            component.formOperations.clearFields();
        }
    }

    private void toggleImage(String text) {
        if (component.imgCreateSave.getImage() == component.saveImage) {
            component.imgCreateSave.setImage(component.createImage);
            component.btnSave.setText(text);
            return;
        }
        // Guardar
        if (component.imgCreateSave.getImage() == component.createImage) {
            component.imgCreateSave.setImage(component.saveImage);
            component.btnSave.setText("Guardar");
            component.formOperations.enableFields();
        }

    }

}
