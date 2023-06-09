package org.joaocastillo.com.view.components;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Services;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.controller.FieldType;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.factory.CompanyDAOFactory;
import org.joaocastillo.com.dao.factory.ServicesFactory;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServicesComponent extends GeneralModelComponent<Services> {
    public ServicesComponent() {
        super(ServicesFactory.getDAO(), true, "idService", "Servicios");
        fetchCompaniesData();
    }

    private void fetchCompaniesData() {
        try {
            if (!((ComboBox) getFields().get("_idCompany")).getItems().isEmpty()) return;
            ((ComboBox<String>) getFields().get("_idCompany")).getItems().clear();
            System.out.println("Fetching companies data...");
            CompanyDAOFactory.getDAO().readAll().forEach(company -> {
                ((ComboBox<String>) getFields().get("_idCompany")).getItems().add(
                        company.getIdCompany() + " - " + company.getNameCompany());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFields(Services model) {
        ((TextField) getFields().get("idService")).setText(String.valueOf(model.getIdService()));
        ((DatePicker) getFields().get("dateService")).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        ((TextField) getFields().get("typeService")).setText(String.valueOf(model.getTypeService()));
        ((TextField) getFields().get("timeService")).setText(String.valueOf(model.getTimeService()));
        ((TextField) getFields().get("locationService")).setText(String.valueOf(model.getLocationService()));
        ((TextField) getFields().get("contactPhoneService")).setText(String.valueOf(model.getContactPhoneService()));
        setComboboxValue(((ComboBox) getFields().get("_idCompany")), model.get_idCompany());
    }

    @Override
    public Services getModel() {
        int idService = ((TextField) getFields().get("idService")).getText().isEmpty()
                ? -1 : Integer.parseInt(((TextField) getFields().get("idService")).getText());

        Date dateService = new Date(((DatePicker) getFields().get("dateService")).getSelectedDate().getTime());
        String typeService = ((TextField) getFields().get("typeService")).getText();
        Time timeService = Time.valueOf(((TextField) getFields().get("timeService")).getText());
        String locationService = ((TextField) getFields().get("locationService")).getText();
        String contactPhoneService = ((TextField) getFields().get("contactPhoneService")).getText();
        int _idCompany = Integer.parseInt(
                ((ComboBox) getFields().get("_idCompany")).getSelectionModel().getSelectedItem().toString().split(
                        separator)[0]);

        return new Services(idService, dateService, typeService, timeService, locationService, contactPhoneService,
                _idCompany);
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idService", new Field("ID", FieldType.INPUT));
            put("dateService", new Field("Fecha de Servicio", FieldType.DATEPICKER));
            put("typeService", new Field("Tipo de Servicio", FieldType.INPUT));
            put("timeService", new Field("Hora", FieldType.INPUT));
            put("locationService", new Field("Lugar", FieldType.INPUT));
            put("contactPhoneService", new Field("Contacto", FieldType.INPUT));
            put("_idCompany", new Field("Empresa", FieldType.COMBOBOX));
        }};
    }

    @Override
    protected List<TableColumn<Services, ?>> createColumns() {
        TableColumn<Services, Integer> idServiceColumn = new TableColumn<>("ID");
        idServiceColumn.setCellValueFactory(new PropertyValueFactory<>("idService"));

        TableColumn<Services, String> dateServiceColumn = new TableColumn<>("Fecha de Servicio");
        dateServiceColumn.setCellValueFactory(new PropertyValueFactory<>("dateService"));

        TableColumn<Services, String> typeServiceColumn = new TableColumn<>("Tipo de Servicio");
        typeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("typeService"));

        TableColumn<Services, String> timeServiceColumn = new TableColumn<>("Hora");
        timeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("timeService"));

        TableColumn<Services, String> locationServiceColumn = new TableColumn<>("Lugar");
        locationServiceColumn.setCellValueFactory(new PropertyValueFactory<>("locationService"));

        TableColumn<Services, String> contactPhoneServiceColumn = new TableColumn<>("Contacto");
        contactPhoneServiceColumn.setCellValueFactory(new PropertyValueFactory<>("contactPhoneService"));

        TableColumn<Services, String> _idCompanyColumn = new TableColumn<>("Empresa");
        _idCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("_idCompany"));
        ArrayList<TableColumn<Services, ?>> columns = new ArrayList<TableColumn<Services, ?>>() {{
            add(idServiceColumn);
            add(dateServiceColumn);
            add(typeServiceColumn);
            add(timeServiceColumn);
            add(locationServiceColumn);
            add(contactPhoneServiceColumn);
            add(_idCompanyColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }


    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdService());
    }

}
