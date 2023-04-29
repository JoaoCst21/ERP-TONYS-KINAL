package org.joaocastillo.com.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.joaocastillo.com.dao.DAO;

import javax.swing.*;
import javax.xml.soap.Text;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public abstract class GeneralController<M> implements Initializable {
    private String fieldID;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnReport;

    @FXML
    private ImageView imgCreateSave;

    @FXML
    private TableView tblModel;

    private boolean isAutoIncrement;
    private Image saveImage = new Image("/org/joaocastillo/com/image/save.png");
    private Image createImage = new Image("/org/joaocastillo/com/image/adduser.png");
    private DAO<M> dao;
    private HashMap<String, TextField> fields;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgCreateSave.setImage(createImage);
        setDefaultFields();
        disableFields();
        try {
            // set the columns to the table
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // Constructor
    public GeneralController(DAO<M> dao) {
        this.dao = dao;
        this.isAutoIncrement = false;
    }

    public GeneralController(DAO<M> dao, boolean isAutoIncrement, String fieldID) {
        this.dao = dao;
        this.isAutoIncrement = isAutoIncrement;
        this.fieldID = fieldID;
    }

    public void onCreate() {
        enableFields();
        toggleImage();
    }

    private void toggleImage() {
        if (imgCreateSave.getImage() == saveImage) {
            imgCreateSave.setImage(createImage);
            btnSave.setText("Nuevo");
            onSave();
            return;
        }
        // Guardar
        if (imgCreateSave.getImage() == createImage) {
            imgCreateSave.setImage(saveImage);
            btnSave.setText("Guardar");
            enableFields();
        }

    }


    public void onSave() {
        try {
            if (areFieldsEmpty()) throw new Exception("Fields are empty");
            getDao().save(getModel());
            clearFields();
            disableFields();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void onDelete() {
        try {
            getDao().delete(getModelID());
            clearFields(); // maybe not
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onEdit() {
        try {
            if (areFieldsEmpty()) throw new Exception("Fields are empty");
            getDao().update(getModel());
            clearFields();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    protected void clearFields() {
        for (TextField txtField : getFields().values()) {
            txtField.setText("");
        }
    }

    protected void enableFields() {
        for (TextField txtField : getFields().values()) {
            txtField.setDisable(false);
        }

        if (isAutoIncrement) getFields().get(fieldID).setDisable(true);
    }

    protected void disableFields() {
        for (TextField txtField : getFields().values()) {
            txtField.setDisable(true);
        }
    }

    protected boolean areFieldsEmpty() {
        for (Map.Entry<String, TextField> field : getFields().entrySet()) {
            if (isAutoIncrement && getFields().get(fieldID) == field.getValue()) continue;
            if (field.getValue().getText().isEmpty()) return true;
        }
        return false;
    }

    protected HashMap<String, TextField> getFields() {
        return this.fields;
    }

    protected void setFields(HashMap<String, TextField> fields) {
        this.fields = fields;
    }

    protected DAO<M> getDao() {
        return dao;
    }

    protected abstract void setDefaultFields();

    protected abstract M getModel();

    protected abstract String getModelID();


}
