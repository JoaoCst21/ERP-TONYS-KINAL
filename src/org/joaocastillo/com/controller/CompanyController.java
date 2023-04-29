package org.joaocastillo.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.joaocastillo.com.bean.Companies;
import org.joaocastillo.com.dao.ConnectionCompany;
import org.joaocastillo.com.dao.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyController extends GeneralController<Companies> {
/*

    @FXML
    private TextField txtIdCompany;

    @FXML
    private TextField txtPhoneCompany;

    @FXML
    private TextField txtAddressCompany;

    @FXML
    private TextField txtNameCompany;
*/

    // Constructor
    public CompanyController() {
        super(new DAO<Companies>(new ConnectionCompany(), "sp_insert_Companies(?,?,?)", "sp_select_Companies(?)", "sp_select_all_Companies()", "sp_update_Companies(?,?,?,?)", "sp_delete_Companies(?)"), true, "idCompany", "Empresas");
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

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(4)));

        return columns;
    }

    @Override
    protected void setFields(Companies model) {
        getFields().get("idCompany").setText(String.valueOf(model.getIdCompany()));
        getFields().get("nameCompany").setText(model.getNameCompany());
        getFields().get("addressCompany").setText(model.getAddressCompany());
        getFields().get("phoneCompany").setText(model.getPhoneCompany());
    }

    @Override
    protected void setDefaultFields() {
        setFields(new HashMap<String, TextField>() {{
            put("idCompany", new TextField());
            put("nameCompany", new TextField());
            put("addressCompany", new TextField());
            put("phoneCompany", new TextField());
        }});
    }

    @Override
    protected Companies getModel() {
        int idCompany = getFields().get("idCompany").getText().isEmpty() ? -1 : Integer.parseInt(getFields().get("idCompany").getText());
        return new Companies(idCompany, getFields().get("nameCompany").getText(), getFields().get("addressCompany").getText(), getFields().get("phoneCompany").getText());
    }

    @Override
    protected String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdCompany());
    }

    @Override
    protected HashMap<String, String> getMapFields() {
        return new HashMap<String, String>() {{
            put("idCompany", "ID");
            put("nameCompany", "Nombre");
            put("addressCompany", "Dirección");
            put("phoneCompany", "Teléfono");
        }};
    }
}