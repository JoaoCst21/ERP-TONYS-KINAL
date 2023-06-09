package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.EmployeeType;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.dao.ConnectionEmployeeType;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.factory.EmployeeTypeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeTypeComponent extends GeneralModelComponent<EmployeeType> {

    public EmployeeTypeComponent() {
        super(EmployeeTypeFactory.getDAO(), true, "idEmployeeType", "TipoEmpleado");
    }

    @Override
    public void setFields(EmployeeType model) {
        ((TextField) getFields().get("idEmployeeType")).setText(String.valueOf(model.getIdEmployeeType()));
        ((TextField) getFields().get("descriptionEmployeeType")).setText(model.getDescriptionEmployeeType());
    }

    @Override
    public EmployeeType getModel() {
        int idEmployeeType = ((TextField) getFields().get("idEmployeeType")).getText().isEmpty()
                ? -1 : Integer.parseInt(((TextField) getFields().get("idEmployeeType")).getText());


        return new EmployeeType(idEmployeeType,
                ((TextField) getFields().get("descriptionEmployeeType")).getText());
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdEmployeeType());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idEmployeeType", new Field("ID", "input"));
            put("descriptionEmployeeType", new Field("Descripción", "input"));
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
