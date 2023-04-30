package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Companies;
import org.joaocastillo.com.dao.ConnectionCompany;
import org.joaocastillo.com.dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyComponent extends GeneralModelComponent<Companies> {

	// Constructor
	public CompanyComponent() {
		super(new DAO<Companies>(new ConnectionCompany(),
								 "sp_insert_Companies(?,?,?)",
								 "sp_select_Companies(?)",
								 "sp_select_all_Companies()",
								 "sp_update_Companies(?,?,?,?)",
								 "sp_delete_Companies" + "(?)"), true, "idCompany", "Empresas");
	}

	@Override
	protected List<TableColumn<Companies, ?>> createColumns() {
		TableColumn<Companies, Integer> idCompanyColumn = new TableColumn<>("ID");
		idCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("idCompany"));

		TableColumn<Companies, String> nameCompanyColumn = new TableColumn<>("Nombre");
		nameCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("nameCompany"));

		TableColumn<Companies, String> addressCompanyColumn = new TableColumn<>("Dirección");
		addressCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("addressCompany"));

		TableColumn<Companies, String> phoneCompanyColumn = new TableColumn<>("Teléfono");
		phoneCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("phoneCompany"));

		List<TableColumn<Companies, ?>> columns = new ArrayList<TableColumn<Companies, ?>>() {{
			add(idCompanyColumn);
			add(nameCompanyColumn);
			add(addressCompanyColumn);
			add(phoneCompanyColumn);
		}};

		columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

		return columns;
	}

	@Override
	public void setFields(Companies model) {
		getFields().get("idCompany").setText(String.valueOf(model.getIdCompany()));
		getFields().get("nameCompany").setText(model.getNameCompany());
		getFields().get("addressCompany").setText(model.getAddressCompany());
		getFields().get("phoneCompany").setText(model.getPhoneCompany());
	}

	@Override
	public Companies getModel() {
		int idCompany = getFields().get("idCompany").getText().isEmpty() ? -1 : Integer.parseInt(getFields().get(
				"idCompany").getText());


		return new Companies(idCompany,
							 getFields().get("nameCompany").getText(),
							 getFields().get("addressCompany").getText(),
							 getFields().get("phoneCompany").getText());
	}

	@Override
	public String getModelID() {
		if (getSelectedModel() == null) return null;
		return String.valueOf(getSelectedModel().getIdCompany());
	}

	@Override
	public HashMap<String, String> getMapFields() {
		return new HashMap<String, String>() {{
			put("idCompany", "ID");
			put("nameCompany", "Nombre");
			put("addressCompany", "Dirección");
			put("phoneCompany", "Teléfono");
		}};
	}
}