package org.joaocastillo.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.joaocastillo.com.bean.Companies;
import org.joaocastillo.com.dao.ConnectionCompany;
import org.joaocastillo.com.dao.DAO;

import java.util.HashMap;

public class CompanyController extends GeneralController<Companies> {

    @FXML
    private TextField txtIdCompany;

    @FXML
    private TextField txtPhoneCompany;

    @FXML
    private TextField txtAddressCompany;

    @FXML
    private TextField txtNameCompany;

    // Constructor
    public CompanyController() {
        super(new DAO<Companies>(new ConnectionCompany(), "sp_insert_Companies(?,?,?)", "sp_select_Companies(?)", "sp_select_all_Companies()", "sp_update_Companies(?,?,?,?)", "sp_delete_Companies(?)"), true, "idCompany");
    }

    @Override
    protected void setDefaultFields() {
        setFields(new HashMap<String, TextField>() {{
            put("idCompany", txtIdCompany);
            put("nameCompany", txtNameCompany);
            put("addressCompany", txtAddressCompany);
            put("phoneCompany", txtPhoneCompany);
        }});
    }

    @Override
    protected Companies getModel() {
        int idCompany = getFields().get("idCompany").getText().isEmpty() ? -1 : Integer.parseInt(getFields().get("idCompany").getText());
        return new Companies(idCompany, getFields().get("nameCompany").getText(), getFields().get("addressCompany").getText(), getFields().get("phoneCompany").getText());
    }

    @Override
    protected String getModelID() {
        return getFields().get("idCompany").getText();
    }
}