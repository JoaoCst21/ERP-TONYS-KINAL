package org.joaocastillo.com.view.components;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Employees;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.controller.FieldType;
import org.joaocastillo.com.dao.factory.EmployeeFactory;
import org.joaocastillo.com.dao.factory.EmployeeTypeFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeComponent extends GeneralModelComponent<Employees> {

    public EmployeeComponent() {
        super(EmployeeFactory.getDAO(), true, "idEmployee", "Empleados");
        fetchTypeEmployee();
    }

    public void fetchTypeEmployee() {
        try {
            if (!((ComboBox) getFields().get("_idEmployeeType")).getItems().isEmpty()) return;
            ((ComboBox<String>) getFields().get("_idEmployeeType")).getItems().clear();
            System.out.println("Fetching Dish Type data...");
            EmployeeTypeFactory.getDAO().readAll().forEach(employeeType -> {
                ((ComboBox<String>) getFields().get("_idEmployeeType")).getItems().add(
                        employeeType.getIdEmployeeType() + separator + employeeType.getDescriptionEmployeeType());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFields(Employees model) {
        ((TextField) getFields().get("idEmployee")).setText(String.valueOf(model.getIdEmployee()));
        ((TextField) getFields().get("employeeNumber")).setText(String.valueOf(model.getEmployeeNumber()));
        ((TextField) getFields().get("firstNamesEmployee")).setText(model.getFirstNamesEmployee());
        ((TextField) getFields().get("lastNamesEmployee")).setText(model.getLastNamesEmployee());
        ((TextField) getFields().get("addressEmployee")).setText(model.getAddressEmployee());
        ((TextField) getFields().get("contactPhone")).setText(model.getContactPhone());
        ((TextField) getFields().get("chefDegreeEmployee")).setText(model.getChefDegreeEmployee());
        setComboboxValue(((ComboBox) getFields().get("_idEmployeeType")), model.get_idEmployeeType());
    }

    @Override
    public Employees getModel() {
        int idEmployee = ((TextField) getFields().get("idEmployee")).getText().isEmpty()
                ? -1 : Integer.parseInt(((TextField) getFields().get("idEmployee")).getText());

        int employeeNumber = Integer.parseInt(((TextField) getFields().get("employeeNumber")).getText());
        String firstNamesEmployee = ((TextField) getFields().get("firstNamesEmployee")).getText();
        String lastNamesEmployee = ((TextField) getFields().get("lastNamesEmployee")).getText();
        String addressEmployee = ((TextField) getFields().get("addressEmployee")).getText();
        String contactPhone = ((TextField) getFields().get("contactPhone")).getText();
        String chefDegreeEmployee = ((TextField) getFields().get("chefDegreeEmployee")).getText();
        int _idEmployeeType = Integer.parseInt(
                ((ComboBox) getFields().get("_idEmployeeType")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]);

        return new Employees(idEmployee, employeeNumber, firstNamesEmployee, lastNamesEmployee, addressEmployee,
                contactPhone, chefDegreeEmployee, _idEmployeeType);
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdEmployee());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idEmployee", new Field("ID", FieldType.INPUT));
            put("employeeNumber", new Field("Número Empleado", FieldType.INPUT));
            put("firstNamesEmployee", new Field("Nombres Empleado", FieldType.INPUT));
            put("lastNamesEmployee", new Field("Apellidos Empleado", FieldType.INPUT));
            put("addressEmployee", new Field("Dirección Empleado", FieldType.INPUT));
            put("contactPhone", new Field("Teléfono de Contacto", FieldType.INPUT));
            put("chefDegreeEmployee", new Field("Posición de Empleado", FieldType.INPUT));
            put("_idEmployeeType", new Field("Tipo Empleado", FieldType.COMBOBOX));
        }};
    }

    @Override
    protected List<TableColumn<Employees, ?>> createColumns() {
        TableColumn<Employees, Integer> idEmployeeColumn = new TableColumn<>("ID");
        idEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idEmployee"));

        TableColumn<Employees, Integer> employeeNumberColumn = new TableColumn<>("Número Empleado");
        employeeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("employeeNumber"));

        TableColumn<Employees, String> firstNamesEmployeeColumn = new TableColumn<>("Nombres Empleado");
        firstNamesEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("firstNamesEmployee"));

        TableColumn<Employees, String> lastNamesEmployeeColumn = new TableColumn<>("Apellidos Empleado");
        lastNamesEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("lastNamesEmployee"));

        TableColumn<Employees, String> addressEmployeeColumn = new TableColumn<>("Dirección Empleado");
        addressEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("addressEmployee"));

        TableColumn<Employees, String > contactPhoneColumn = new TableColumn<>("Teléfono de Contacto");
        contactPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("contactPhone"));

        TableColumn<Employees, String> chefDegreeEmployeeColumn = new TableColumn<>("Posición de Empleado");
        chefDegreeEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("chefDegreeEmployee"));

        TableColumn<Employees, Integer> _idEmployeeTypeColumn = new TableColumn<>("Tipo Empleado");
        _idEmployeeTypeColumn.setCellValueFactory(new PropertyValueFactory<>("_idEmployeeType"));

        List<TableColumn<Employees, ?>> columns = new ArrayList<TableColumn<Employees, ?>>() {{
            add(idEmployeeColumn);
            add(employeeNumberColumn);
            add(firstNamesEmployeeColumn);
            add(lastNamesEmployeeColumn);
            add(addressEmployeeColumn);
            add(contactPhoneColumn);
            add(chefDegreeEmployeeColumn);
            add(_idEmployeeTypeColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }
}
