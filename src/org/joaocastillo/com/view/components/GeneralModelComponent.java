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
import org.joaocastillo.com.controller.CRUDOperation;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.controller.FormOperations;
import org.joaocastillo.com.controller.LoadFXML;
import org.joaocastillo.com.dao.DAO;

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


	// Constructor
	public GeneralModelComponent(DAO<M> dao) {
		this.dao = dao;
		new LoadFXML().loadFXML(this, "../view/Plantilla.fxml");
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
		btnSave.setOnMouseClicked(event -> crudOperations.onCreate());
		btnDelete.setOnMouseClicked(event -> crudOperations.onCancel());
		btnEdit.setOnMouseClicked(event -> crudOperations.onEdit());
		imgCreateSave.setImage(createImage);
		imgDeleteCancel.setImage(deleteImage);
		formOperations.setDefaultFields();
		formOperations.setFormData();
		formOperations.disableFields();
		// when clicked on a row, select the row, on click again, deselect the row
		AtomicBoolean isAlreadySelected = new AtomicBoolean(false);
		AtomicReference<M> currentSelected = new AtomicReference<M>(null);
		tblModel.setOnMouseClicked(event -> {
			if (!isAlreadySelected.get() || (currentSelected.get() != getSelectedModel())) {
				isAlreadySelected.set(true);
				currentSelected.set(getSelectedModel());
				return;
			}
			tblModel.getSelectionModel().clearSelection();
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

	public abstract M getModel();

	public abstract String getModelID();

	public abstract HashMap<String, Field> getMapFields();

	protected abstract List<TableColumn<M, ?>> createColumns();
}

