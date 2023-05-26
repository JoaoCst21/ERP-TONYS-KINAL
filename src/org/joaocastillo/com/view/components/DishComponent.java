package org.joaocastillo.com.view.components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Dishes;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.dao.ConnectionDishes;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.factory.DishTypeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DishComponent extends GeneralModelComponent<Dishes> {

    public DishComponent() {
        super(new DAO<Dishes>(new ConnectionDishes(), "sp_insert_Dishes(?,?,?,?,?)", "sp_select_Dishes(?)", "sp_select_all_Dishes()", "sp_update_Dishes(?,?,?,?,?,?)", "sp_delete_Dishes(?)"), true, "idDish", "Plato");
        fetchTypeDishData();
    }

    public void fetchTypeDishData() {
        try {
            if (!((ComboBox) getFields().get("_idDishType")).getItems().isEmpty()) return;
            ((ComboBox<String>) getFields().get("_idDishType")).getItems().clear();
            System.out.println("Fetching Dish Type data...");
            DishTypeFactory.getDAO().readAll().forEach(dishType -> {
                ((ComboBox<String>) getFields().get("_idDishType")).getItems().add(dishType.getIdDishType() + " - " + dishType.getDescriptionDishType());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFields(Dishes model) {
        ((TextField) getFields().get("idDish")).setText(String.valueOf(model.getIdDish()));
        ((TextField) getFields().get("quantity")).setText(String.valueOf(model.getQuantity()));
        ((TextField) getFields().get("dishName")).setText(model.getDishName());
        ((TextField) getFields().get("descriptionDish")).setText(model.getDescriptionDish());
        ((TextField) getFields().get("dishPrice")).setText(String.valueOf(model.getDishPrice()));
        ((ComboBox) getFields().get("_idDishType")).setValue(model.get_idDishType());
    }

    @Override
    public Dishes getModel() {
        int idDish = ((TextField) getFields().get("idDish")).getText().isEmpty() ? -1 : Integer.parseInt(((TextField) getFields().get("idDish")).getText());

        return new Dishes(idDish, Integer.parseInt(((TextField) getFields().get("quantity")).getText()), ((TextField) getFields().get("dishName")).getText(), ((TextField) getFields().get("descriptionDish")).getText(), Double.parseDouble(((TextField) getFields().get("dishPrice")).getText()), Integer.parseInt(((ComboBox) getFields().get("_idDishType")).getSelectionModel().getSelectedItem().toString().charAt(0) + ""));
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdDish());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idDish", new Field("ID", "input"));
            put("quantity", new Field("Cantidad", "input"));
            put("dishName", new Field("Nombre", "input"));
            put("descriptionDish", new Field("Descripción", "input"));
            put("dishPrice", new Field("Precio", "input"));
            put("_idDishType", new Field("Tipo de plato", "combobox"));
        }};
    }

    @Override
    protected List<TableColumn<Dishes, ?>> createColumns() {
        TableColumn<Dishes, Integer> idDishColumn = new TableColumn<>("ID");
        idDishColumn.setCellValueFactory(new PropertyValueFactory<>("idDish"));

        TableColumn<Dishes, Integer> quantityColumn = new TableColumn<>("Cantidad");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<Dishes, String> dishNameColumn = new TableColumn<>("Nombre");
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<>("dishName"));

        TableColumn<Dishes, String> descriptionDishColumn = new TableColumn<>("Descripción");
        descriptionDishColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionDish"));

        TableColumn<Dishes, Double> dishPriceColumn = new TableColumn<>("Precio");
        dishPriceColumn.setCellValueFactory(new PropertyValueFactory<>("dishPrice"));

        TableColumn<Dishes, Integer> _idDishTypeColumn = new TableColumn<>("Tipo de plato");
        _idDishTypeColumn.setCellValueFactory(new PropertyValueFactory<>("_idDishType"));

        List<TableColumn<Dishes, ?>> columns = new ArrayList<TableColumn<Dishes, ?>>() {{
            add(idDishColumn);
            add(quantityColumn);
            add(dishNameColumn);
            add(descriptionDishColumn);
            add(dishPriceColumn);
            add(_idDishTypeColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }
}
