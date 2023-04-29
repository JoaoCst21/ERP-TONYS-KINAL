package org.joaocastillo.com.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.joaocastillo.com.dao.DAO;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

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
    private Label lblTitle;

    @FXML
    public GridPane formModel;


    @FXML
    private ImageView imgCreateSave;

    @FXML
    protected TableView<M> tblModel;

    private boolean isAutoIncrement;
    private Image saveImage = new Image("/org/joaocastillo/com/image/save.png");
    private Image createImage = new Image("/org/joaocastillo/com/image/adduser.png");
    private DAO<M> dao;
    private HashMap<String, TextField> fields;

    private Operation currentOperation;
    @FXML
    private ImageView imgDeleteCancel;
    private Image deleteImage = new Image("/org/joaocastillo/com/image/delete.png");
    private Image cancelImage = new Image("/org/joaocastillo/com/image/cancel.png");
    private String title;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblTitle.setText(this.title);
        imgCreateSave.setImage(createImage);
        imgDeleteCancel.setImage(deleteImage);
        setDefaultFields();
        System.out.println(formModel);
        System.out.println(tblModel);
        setFormData();
        disableFields();
        try {
            // set the columns to the table
            tblModel.getColumns().addAll(createColumns());
            fetchData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setFormData() {
        int iteration = 0;
        int indexRow = 0;
        int indexColumn = 0;

        double width = formModel.getPrefWidth() / getMapFields().size();
        double txtFieldsSizeAcc = width * (getMapFields().size() / 2);
        double lblFieldsSizeAcc = txtFieldsSizeAcc / 2;
        double padding = (width - (lblFieldsSizeAcc + txtFieldsSizeAcc)) / (getMapFields().size() - 1);

        // set Space between label and textfield
        formModel.setHgap(padding + width);

        for (Map.Entry<String, String> entry : getMapFields().entrySet()) {

            Label label = new Label(entry.getValue());
            label.setPrefWidth(width / 2);
            label.setStyle("-fx-font-weight: bold;");

            getFields().get(entry.getKey()).setPrefWidth(width);
            formModel.add(label, indexColumn, indexRow);
            formModel.add(getFields().get(entry.getKey()), indexColumn + 1, indexRow);
            if (iteration % 2 == 0) indexRow++;
            else indexRow--;

            iteration++;

            if (iteration % 2 == 0) indexColumn += 2;
        }
    }

    private void fetchData() throws Exception {
        tblModel.setItems(FXCollections.observableArrayList(getDao().readAll()));
    }

    protected abstract List<TableColumn<M, ?>> createColumns();


    // Constructor
    public GeneralController(DAO<M> dao) {
        this.dao = dao;
        this.isAutoIncrement = false;
    }

    public GeneralController(DAO<M> dao, boolean isAutoIncrement, String fieldID, String entityName) {
        this.dao = dao;
        this.isAutoIncrement = isAutoIncrement;
        this.fieldID = fieldID;
        this.title = entityName;
    }

    public void onCreate() {
        enableFields();
        if (btnSave.getText().equals("Guardar")) onSave();
        toggleImage("Nuevo");
        toggleImageCancel();
        currentOperation = () -> getDao().save(getModel());
    }

    public void onCancel() {
        disableFields();
        clearFields();
        if (btnDelete.getText().equals("Eliminar") && getModelID() != null) {
            onDelete();
            return;
        }
        toggleImageCancel();
        toggleImage("Nuevo");
    }

    private void toggleImageCancel() {
        if (imgDeleteCancel.getImage() == deleteImage) {
            imgDeleteCancel.setImage(cancelImage);
            btnDelete.setText("Cancelar");
            return;
        }

        if (imgDeleteCancel.getImage() == cancelImage) {
            imgDeleteCancel.setImage(deleteImage);
            btnDelete.setText("Eliminar");
            disableFields();
            clearFields();
        }
    }

    private void toggleImage(String text) {
        if (imgCreateSave.getImage() == saveImage) {
            imgCreateSave.setImage(createImage);
            btnSave.setText(text);
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
            currentOperation.operate();
            clearFields();
            disableFields();
            fetchData();
//            btnSave.setText("Nuevo");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void onDelete() {
        try {
            getDao().delete(getModelID());
            fetchData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void onEdit() {
        enableFields();
        setFields(getSelectedModel());
        if (btnSave.getText().equals("Guardar")) return;
        toggleImage("Modificar");
        toggleImageCancel();
        currentOperation = () -> getDao().update(getModel());
    }

    protected M getSelectedModel() {
        return tblModel.getSelectionModel().getSelectedItem();
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

    protected abstract void setFields(M model);

    protected DAO<M> getDao() {
        return dao;
    }

    protected void setDefaultFields() {
        setFields(new HashMap<>());
        getMapFields().forEach((key, value) -> {
            getFields().put(key, new TextField());
        });
    }

    protected abstract M getModel();

    protected abstract String getModelID();

    protected abstract HashMap<String, String> getMapFields();
}

interface Operation {
    void operate() throws Exception;
}
