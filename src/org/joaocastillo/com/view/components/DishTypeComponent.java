package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.DishType;
import org.joaocastillo.com.dao.ConnectionDishType;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.IConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DishTypeComponent extends GeneralModelComponent<DishType> {

	public DishTypeComponent() {
		super(new DAO<DishType>(new ConnectionDishType(),
								"sp_insert_DishType(?)",
								"sp_select_DishType(?)",
								"sp_select_all_DishType()",
								"sp_update_DishType(?,?)",
								"sp_delete_DishType(?)"), true, "idDishType", "TipoPlato");
	}

	@Override
	public void setFields(DishType model) {
		getFields().get("idDishType").setText(String.valueOf(model.getIdDishType()));
		getFields().get("descriptionDishType").setText(model.getDescriptionDishType());
	}

	@Override
	public DishType getModel() {
		int idDishType = getFields().get("idDishType").getText().isEmpty()
				? -1 : Integer.parseInt(getFields().get("idDishType").getText());

		return new DishType(idDishType, getFields().get("descriptionDishType").getText());
	}

	@Override
	public String getModelID() {
		System.out.println(getSelectedModel());
		System.out.println(getModel().getIdDishType());
		if (getSelectedModel() == null) return null;
		return String.valueOf(getSelectedModel().getIdDishType());
	}

	@Override
	public HashMap<String, String> getMapFields() {
		return new HashMap<String, String>() {{
			put("idDishType", "ID");
			put("descriptionDishType", "Descripción");
		}};
	}

	@Override
	protected List<TableColumn<DishType, ?>> createColumns() {
		TableColumn<DishType, Integer> idDishTypeColumn = new TableColumn<>("ID");
		idDishTypeColumn.setCellValueFactory(new PropertyValueFactory<>("idDishType"));

		TableColumn<DishType, String> descriptionDishTypeColumn = new TableColumn<>("Descripción");
		descriptionDishTypeColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionDishType"));

		List<TableColumn<DishType, ?>> columns = new ArrayList<TableColumn<DishType, ?>>() {{
			add(idDishTypeColumn);
			add(descriptionDishTypeColumn);
		}};

		columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

		return columns;
	}
}
