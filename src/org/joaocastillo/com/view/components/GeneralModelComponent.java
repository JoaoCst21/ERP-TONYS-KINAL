package org.joaocastillo.com.view.components;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.joaocastillo.com.controller.*;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.report.GenerateReport;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public abstract class GeneralModelComponent<M> extends AnchorPane implements Initializable {
    @FXML
    public Button btnSave;
    @FXML
    public Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnReport;
    @FXML
    private Label lblTitle;
    @FXML
    public GridPane formModel;
    @FXML
    public ImageView imgCreateSave;
    @FXML
    public ImageView homeImg;

    @FXML
    protected TableView<M> tblModel;
    @FXML
    public ImageView imgDeleteCancel;
    private DAO<M> dao;
    private HashMap<String, Node> fields;
    public UIOperation currentOperation;
    private String title;
    public String fieldID;
    public boolean isAutoIncrement = false;
    public Image saveImage = new Image("/org/joaocastillo/com/image/save.png");
    public Image createImage = new Image("/org/joaocastillo/com/image/adduser.png");
    public Image deleteImage = new Image("/org/joaocastillo/com/image/delete.png");
    public Image cancelImage = new Image("/org/joaocastillo/com/image/cancel.png");
    public FormOperations<M> formOperations = new FormOperations<M>(this);
    public CRUDOperation crudOperations = new CRUDOperation(this);

    protected String separator = " - ";


    // Constructor
    public GeneralModelComponent(DAO<M> dao) {
        this.dao = dao;
        int inputsSize = this.getMapFields().size();
        if (inputsSize <= 4) new LoadFXML().loadFXML(this, "../view/Plantilla.fxml");
        else new LoadFXML().loadFXML(this, "../view/PlantillaGrande.fxml");
    }

    public GeneralModelComponent(DAO<M> dao, boolean isAutoIncrement, String fieldID, String entityName) {
        this(dao);
        this.isAutoIncrement = isAutoIncrement;
        this.fieldID = fieldID;
        this.title = entityName;
        lblTitle.setText(this.title);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HashMap<String, Object> parameters = new HashMap() {{
            // replace \ with \\ to avoid errors
            put("baseDir", System.getProperty("user.dir").replaceAll("\\\\", "\\\\\\\\"));
        }};
        System.out.println(parameters.get("baseDir"));
        System.out.println("baseDir");
        btnSave.setOnMouseClicked(event -> crudOperations.onCreate());
        btnDelete.setOnMouseClicked(event -> crudOperations.onCancel());
        btnEdit.setOnMouseClicked(event -> crudOperations.onEdit());
        btnReport.setOnMouseClicked(vent -> GenerateReport.mostrarReporte(this.title, "Reporte de " + this.title,
                parameters));
        imgCreateSave.setImage(createImage);
        imgDeleteCancel.setImage(deleteImage);
        formOperations.setDefaultFields();
        formOperations.setFormData();
        formOperations.disableFields();

        homeImg.setOnMouseClicked(event -> {
            // hide current window
            ScreenController.getInstance().getMain().getWindow().hide();
            Stage stage = new Stage();

            // set the main window to the stage
            ScreenController.getInstance().setStage(stage);
            ScreenController.getInstance().activate("Menu");
            stage.setResizable(true);

            // show the stage
            stage.show();
        });

        // when clicked on a row, select the row, on click again, deselect the row
        AtomicBoolean isAlreadySelected = new AtomicBoolean(false);
        AtomicReference<M> currentSelected = new AtomicReference<M>(null);
        tblModel.setOnMouseClicked(event -> {
            if (!isAlreadySelected.get() || (currentSelected.get() != getSelectedModel())) {
                isAlreadySelected.set(true);
                currentSelected.set(getSelectedModel());
                setFields(getSelectedModel());
                return;
            }
            tblModel.getSelectionModel().clearSelection();
            formOperations.clearFields();
            isAlreadySelected.set(false);
            currentSelected.set(null);
        });

        try {
            // set the columns to the table
            tblModel.getColumns().addAll(createColumns());
            fetchData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchData() throws Exception {
        tblModel.setItems(FXCollections.observableArrayList(getDAO().readAll()));
    }

    public M getSelectedModel() {
        return tblModel.getSelectionModel().getSelectedItem();
    }

    public HashMap<String, Node> getFields() {
        return this.fields;
    }

    public void setFields(HashMap<String, Node> fields) {
        this.fields = fields;
    }

    public DAO<M> getDAO() {
        return dao;
    }

    public abstract void setFields(M model);

    public void setComboboxValue(ComboBox comboBox, int id) {
        // iterate over the comboBox items
        for (int i = 0; i < comboBox.getItems().size(); i++) {
            // if the item is equal to the model's id, set the comboBox value to that item
            if (comboBox.getItems().get(i).toString().split(separator)[0].equals(String.valueOf(id))) {
                comboBox.getSelectionModel().select(i);
                comboBox.setValue(comboBox.getItems().get(i));
                break;
            }
        }
    }

    public abstract M getModel();

    public abstract String getModelID();

    public abstract HashMap<String, Field> getMapFields();

    protected abstract List<TableColumn<M, ?>> createColumns();
}

