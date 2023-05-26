package org.joaocastillo.com.view.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Companies;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.dao.factory.CompanyDAOFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CompanyComponent extends GeneralModelComponent<Companies> {

    // Constructor
    public CompanyComponent() {
        super(CompanyDAOFactory.getDAO(), true, "idCompany", "Empresas");
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

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));

        return columns;
    }

    @Override
    public void setFields(Companies model) {
        ((TextField) getFields().get("idCompany")).setText(String.valueOf(model.getIdCompany()));
        ((TextField) getFields().get("nameCompany")).setText(model.getNameCompany());
        ((TextField) getFields().get("addressCompany")).setText(model.getAddressCompany());
        ((TextField) getFields().get("phoneCompany")).setText(model.getPhoneCompany());
    }

    @Override
    public Companies getModel() {
        int idCompany = ((TextField) getFields().get("idCompany")).getText().isEmpty() ? -1 : Integer.parseInt(((TextField) getFields().get(
                "idCompany")).getText());


        return new Companies(idCompany,
                ((TextField) getFields().get("nameCompany")).getText(),
                ((TextField) getFields().get("addressCompany")).getText(),
                ((TextField) getFields().get("phoneCompany")).getText());
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdCompany());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idCompany", new Field("ID", "input"));
            put("nameCompany", new Field("Nombre", "input"));
            put("addressCompany", new Field("Dirección", "input"));
            put("phoneCompany", new Field("Teléfono", "input"));
        }};
    }
}