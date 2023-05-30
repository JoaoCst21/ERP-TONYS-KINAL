package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Products_has_Dishes;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.dao.ConnectionProduct_has_Dishes;
import org.joaocastillo.com.dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Products_has_DishesComponent extends GeneralModelComponent<Products_has_Dishes> {

    public Products_has_DishesComponent() {
        super(new DAO<Products_has_Dishes>(
                new ConnectionProduct_has_Dishes(),
                "sp_insert_Products_has_Dishes(?,?)",
                "sp_select_Products_has_Dishes(?)",
                "sp_select_all_Products_has_Dishes()",
                "sp_update_Products_has_Dishes(?,?,?)",
                "sp_delete_Products_has_Dishes(?)"), true, "idProductDish", "Productos tiene Platos");
    }

    @Override
    public void setFields(Products_has_Dishes model) {
        ((TextField) getFields().get("idProductDish")).setText(String.valueOf(model.getIdProductDish()));
        ((TextField) getFields().get("_idProduct")).setText(String.valueOf(model.get_idProduct()));
        ((TextField) getFields().get("_idDish")).setText(String.valueOf(model.get_idDish()));
    }

    @Override
    public Products_has_Dishes getModel() {
        int id = ((TextField) getFields().get("idProductDish")).getText().isEmpty()
                ? -1 : Integer.parseInt(((TextField) getFields().get("idProductDish")).getText());

        return new Products_has_Dishes(id,
                Integer.parseInt(((TextField) getFields().get("_idProduct")).getText()),
                Integer.parseInt(((TextField) getFields().get("_idDish")).getText()));
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdProductDish());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idProductDish", new Field("ID", "input"));
            put("_idProduct", new Field("ID Producto", "input"));
            put("_idDish", new Field("ID Plato", "input"));
        }};
    }

    @Override
    protected List<TableColumn<Products_has_Dishes, ?>> createColumns() {
        TableColumn<Products_has_Dishes, Integer> idProductDishColumn = new TableColumn<>("ID");
        idProductDishColumn.setCellValueFactory(new PropertyValueFactory<>("idProductDish"));

        TableColumn<Products_has_Dishes, Integer> _idProductColumn = new TableColumn<>("ID Producto");
        _idProductColumn.setCellValueFactory(new PropertyValueFactory<>("_idProduct"));

        TableColumn<Products_has_Dishes, Integer> _idDishColumn = new TableColumn<>("ID Plato");
        _idDishColumn.setCellValueFactory(new PropertyValueFactory<>("_idDish"));

        List<TableColumn<Products_has_Dishes, ?>> columns = new ArrayList<TableColumn<Products_has_Dishes, ?>>() {{
            add(idProductDishColumn);
            add(_idProductColumn);
            add(_idDishColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }
}
