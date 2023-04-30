package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.EmployeeType;
import org.joaocastillo.com.dao.ConnectionEmployeeType;
import org.joaocastillo.com.dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeTypeComponent extends GeneralModelComponent<EmployeeType> {

	public EmployeeTypeComponent() {
		super(new DAO<EmployeeType>(new ConnectionEmployeeType(),
									"sp_insert_EmployeeType(?)",
									"sp_select_EmployeeType(?)",
									"sp_select_all_EmployeeType()",
									"sp_update_EmployeeType(?,?)",
									"sp_delete_EmployeeType(?)"), true, "idEmployeeType", "TipoEmpleado");
	}

	@Override
	public void setFields(EmployeeType model) {
		getFields().get("idEmployeeType").setText(String.valueOf(model.getIdEmployeeType()));
		getFields().get("descriptionEmployeeType").setText(model.getDescriptionEmployeeType());
	}

	@Override
	public EmployeeType getModel() {
		int idEmployeeType = getFields().get("idEmployeeType").getText().isEmpty()
				? -1 : Integer.parseInt(getFields().get("idEmployeeType").getText());


		return new EmployeeType(idEmployeeType,
								getFields().get("descriptionEmployeeType").getText());
	}

	@Override
	public String getModelID() {
		if (getSelectedModel() == null) return null;
		return String.valueOf(getSelectedModel().getIdEmployeeType());
	}

	@Override
	public HashMap<String, String> getMapFields() {
		return new HashMap<String, String>() {{
			put("idEmployeeType", "ID");
			put("descriptionEmployeeType", "Descripción");
		}};
	}

	@Override
	protected List<TableColumn<EmployeeType, ?>> createColumns() {
		TableColumn<EmployeeType, Integer> idEmployeeTypeColumn = new TableColumn<>("ID");
		idEmployeeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("idEmployeeType"));

		TableColumn<EmployeeType, String> descriptionEmployeeTypeColumn = new TableColumn<>("Descripción");
		descriptionEmployeeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionEmployeeType"));

		List<TableColumn<EmployeeType, ?>> columns = new ArrayList<TableColumn<EmployeeType, ?>>() {{
			add(idEmployeeTypeColumn);
			add(descriptionEmployeeTypeColumn);
		}};

		columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

		return columns;
	}
}
