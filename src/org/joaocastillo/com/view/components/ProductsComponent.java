package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import org.joaocastillo.com.bean.Products;
import org.joaocastillo.com.dao.ConnectionProducts;
import org.joaocastillo.com.dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsComponent extends GeneralModelComponent<Products> {

	public ProductsComponent() {
		super(new DAO<Products>(new ConnectionProducts(),
								"sp_insert_Products(?,?)",
								"sp_select_Products(?)",
								"sp_select_all_Products()",
								"sp_update_Products(?,?,?)",
								"sp_delete_Products(?)"), true, "idProduct", "Productos");
	}

	@Override
	public void setFields(Products model) {
		getFields().get("idProduct").setText(String.valueOf(model.getIdProduct()));
		getFields().get("productName").setText(model.getProductName());
		getFields().get("productQuantity").setText(String.valueOf(model.getProductQuantity()));
	}

	@Override
	public Products getModel() {
		int idProduct = getFields().get("idProduct").getText().isEmpty()
				? -1 : Integer.parseInt(getFields().get("idProduct").getText());

		return new Products(idProduct,
							getFields().get("productName").getText(),
							Integer.parseInt(getFields().get("productQuantity").getText()));
	}

	@Override
	public String getModelID() {
		if (getSelectedModel() == null) return null;
		return String.valueOf(getSelectedModel().getIdProduct());
	}

	@Override
	public HashMap<String, String> getMapFields() {
		return new HashMap<String, String>() {{
			put("idProduct", "ID");
			put("productName", "Nombre");
			put("productQuantity", "Cantidad");
		}};
	}

	@Override
	protected List<TableColumn<Products, ?>> createColumns() {
		TableColumn<Products, Integer> idProductColumn = new TableColumn<>("ID");
		idProductColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("idProduct"));

		TableColumn<Products, String> productNameColumn = new TableColumn<>("Nombre");
		productNameColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("productName"));

		TableColumn<Products, String> productQuantityColumn = new TableColumn<>("Cantidad");
		productQuantityColumn.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("productQuantity"));

		ArrayList<TableColumn<Products, ?>> columns = new ArrayList<TableColumn<Products, ?>>() {{
			add(idProductColumn);
			add(productNameColumn);
			add(productQuantityColumn);
		}};

		columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

		return columns;
	}
}
