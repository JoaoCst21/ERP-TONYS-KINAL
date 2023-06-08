package org.joaocastillo.com.view.components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Services_has_Dishes;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.dao.ConnectionServices_has_Dishes;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.factory.DishesFactory;
import org.joaocastillo.com.dao.factory.ServicesFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Services_has_DishesComponent extends GeneralModelComponent<Services_has_Dishes> {

    public Services_has_DishesComponent() {
        super(new DAO<Services_has_Dishes>(new ConnectionServices_has_Dishes(), "sp_insert_Services_has_Dishes(?,?)",
                        "sp_select_Services_has_Dishes(?)", "sp_select_all_Services_has_Dishes()",
                        "sp_update_Services_has_Dishes(?,?,?)", "sp_delete_Services_has_Dishes(?)"), true, "idServiceDish",
                "Servicios tiene Platos");
        fetchDishes();
        fetchServices();
    }

    public void fetchDishes() {
        try {
            ComboBox comboBox = (ComboBox) getFields().get("_idDish");
            if (!comboBox.getItems().isEmpty()) return;
            comboBox.getItems().clear();
            System.out.println("Fetching dishes...");
            DishesFactory.getDAO().readAll().forEach(dish -> {
                comboBox.getItems().add(dish.getIdDish() + separator + dish.getDishName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchServices() {
        try {
            ComboBox comboBox = (ComboBox) getFields().get("_idService");
            if (!comboBox.getItems().isEmpty()) return;
            comboBox.getItems().clear();

            System.out.println("Fetching services...");
            ServicesFactory.getDAO().readAll().forEach(service -> {
                comboBox.getItems().add(service.getIdService() + separator + service.getTypeService());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFields(Services_has_Dishes model) {
        ((TextField) getFields().get("idServiceDish")).setText(String.valueOf(model.getIdServiceDish()));
        ((ComboBox) getFields().get("_idService")).setValue(String.valueOf(model.get_idService()));
        ((ComboBox) getFields().get("_idDish")).setValue(String.valueOf(model.get_idDish()));
    }

    @Override
    public Services_has_Dishes getModel() {
        int id = ((TextField) getFields().get("idServiceDish")).getText().isEmpty() ? -1 : Integer.parseInt(
                ((TextField) getFields().get("idServiceDish")).getText());

        return new Services_has_Dishes(id, Integer.parseInt(
                ((ComboBox) getFields().get("_idService")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]), Integer.parseInt(
                ((ComboBox) getFields().get("_idDish")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]));
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdServiceDish());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idServiceDish", new Field("ID", "input"));
            put("_idService", new Field("ID Servicio", "combobox"));
            put("_idDish", new Field("ID Plato", "combobox"));
        }};
    }

    @Override
    protected List<TableColumn<Services_has_Dishes, ?>> createColumns() {
        TableColumn<Services_has_Dishes, Integer> idServiceDishColumn = new TableColumn<>("ID");
        idServiceDishColumn.setCellValueFactory(new PropertyValueFactory<>("idServiceDish"));

        TableColumn<Services_has_Dishes, Integer> _idServiceColumn = new TableColumn<>("ID Servicio");
        _idServiceColumn.setCellValueFactory(new PropertyValueFactory<>("_idService"));

        TableColumn<Services_has_Dishes, Integer> _idDishColumn = new TableColumn<>("ID Plato");
        _idDishColumn.setCellValueFactory(new PropertyValueFactory<>("_idDish"));

        List<TableColumn<Services_has_Dishes, ?>> columns = new ArrayList<TableColumn<Services_has_Dishes, ?>>() {{
            add(idServiceDishColumn);
            add(_idServiceColumn);
            add(_idDishColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }
}
