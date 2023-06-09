package org.joaocastillo.com.view.components;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Services_has_Employees;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.controller.FieldType;
import org.joaocastillo.com.dao.ConnectionServices_has_Employees;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.factory.EmployeeFactory;
import org.joaocastillo.com.dao.factory.ServicesFactory;

import javax.xml.soap.Text;
import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Services_has_EmployeesComponent extends GeneralModelComponent<Services_has_Employees> {
    public Services_has_EmployeesComponent() {
        super(new DAO<Services_has_Employees>(new ConnectionServices_has_Employees(),
                        "sp_insert_Services_has_Employees" +
                                "(?,?,?,?,?)",
                        "sp_select_Services_has_Employees(?)", "sp_select_all_Services_has_Employees()",
                        "sp_update_Services_has_Employees(?,?,?,?,?,?)", "sp_delete_Services_has_Employees(?)"), true,
                "idServiceEmployee",
                "Servicios tiene Empleados");
        fetchEmployees();
        fetchServices();
    }

    public void fetchEmployees() {
        try {
            ComboBox comboBox = (ComboBox) getFields().get("_idEmployee");
            if (!comboBox.getItems().isEmpty()) return;
            comboBox.getItems().clear();
            System.out.println("Fetching dishes...");
            EmployeeFactory.getDAO().readAll().forEach(dish -> {
                comboBox.getItems().add(dish.getIdEmployee() + separator + dish.getFirstNamesEmployee());
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
    public void setFields(Services_has_Employees model) {
        ((TextField) getFields().get("idServiceEmployee")).setText(String.valueOf(model.getIdServiceEmployee()));
        setComboboxValue(((ComboBox) getFields().get("_idService")), model.get_idService());
        setComboboxValue((ComboBox) getFields().get("_idEmployee"), model.get_idEmployee());
        ((DatePicker) getFields().get("eventDate")).setSelectedDate(model.getEventDate());
        ((TextField) getFields().get("eventTime")).setText(model.getEventTime().toString());
        ((TextField) getFields().get("eventLocation")).setText(model.getEventLocation());
    }

    @Override
    public Services_has_Employees getModel() {
        int id = ((TextField) getFields().get("idServiceEmployee")).getText().isEmpty() ? -1 : Integer.parseInt(
                ((TextField) getFields().get("idServiceEmployee")).getText());


        return new Services_has_Employees(id, Integer.parseInt(
                ((ComboBox) getFields().get("_idService")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]), Integer.parseInt(
                ((ComboBox) getFields().get("_idEmployee")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]),
                new Date(((DatePicker) getFields().get("eventDate")).getSelectedDate().getTime()),
                Time.valueOf(((TextField) getFields().get("eventTime")).getText()),
                ((TextField) getFields().get("eventLocation")).getText()
        );
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdServiceEmployee());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idServiceEmployee", new Field("ID", FieldType.INPUT));
            put("_idService", new Field("ID Servicio", FieldType.COMBOBOX));
            put("_idEmployee", new Field("ID Empleado", FieldType.COMBOBOX));
            put("eventDate", new Field("Fecha de evento", FieldType.DATEPICKER));
            put("eventTime", new Field("Hora de evento", FieldType.INPUT));
            put("eventLocation", new Field("Lugar de evento", FieldType.INPUT));
        }};
    }

    @Override
    protected List<TableColumn<Services_has_Employees, ?>> createColumns() {
        TableColumn<Services_has_Employees, Integer> idServiceEmployeeColumn = new TableColumn<>("ID");
        idServiceEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("idServiceEmployee"));

        TableColumn<Services_has_Employees, Integer> _idServiceColumn = new TableColumn<>("ID Servicio");
        _idServiceColumn.setCellValueFactory(new PropertyValueFactory<>("_idService"));

        TableColumn<Services_has_Employees, Integer> _idEmployeeColumn = new TableColumn<>("ID Empleado");
        _idEmployeeColumn.setCellValueFactory(new PropertyValueFactory<>("_idEmployee"));

        TableColumn<Services_has_Employees, Date> eventDateColumn = new TableColumn<>("Fecha de evento");
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));

        TableColumn<Services_has_Employees, Integer> eventTimeColumn = new TableColumn<>("Hora de evento");
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<>("eventTime"));

        TableColumn<Services_has_Employees, String> eventLocationColumn = new TableColumn<>("Lugar de evento");
        eventLocationColumn.setCellValueFactory(new PropertyValueFactory<>("eventLocation"));


        List<TableColumn<Services_has_Employees, ?>> columns =
                new ArrayList<TableColumn<Services_has_Employees, ?>>() {{
                    add(idServiceEmployeeColumn);
                    add(_idServiceColumn);
                    add(_idEmployeeColumn);
                    add(eventDateColumn);
                    add(eventTimeColumn);
                    add(eventLocationColumn);
                }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }
}
