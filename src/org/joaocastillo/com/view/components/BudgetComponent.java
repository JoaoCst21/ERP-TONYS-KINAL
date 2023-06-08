package org.joaocastillo.com.view.components;

import eu.schudt.javafx.controls.calendar.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.joaocastillo.com.bean.Budgets;
import org.joaocastillo.com.controller.Field;
import org.joaocastillo.com.controller.FieldType;
import org.joaocastillo.com.dao.factory.CompanyDAOFactory;
import org.joaocastillo.com.dao.ConnectionBudget;
import org.joaocastillo.com.dao.DAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BudgetComponent extends GeneralModelComponent<Budgets> {
    public BudgetComponent() {
        super(new DAO<Budgets>(new ConnectionBudget(),
                "sp_insert_Budgets(?,?,?)",
                "sp_select_Budgets(?)",
                "sp_select_all_Budgets()",
                "sp_update_Budgets(?,?,?,?)",
                "sp_delete_Budgets(?)"
        ), true, "idBudget", "Presupuesto");

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
    public void setFields(Budgets model) {
        ((TextField) getFields().get("idBudget")).setText(String.valueOf(model.getIdBudget()));
        ((TextField) getFields().get("budgetAmount")).setText(String.valueOf(model.getBudgetAmount()));
        ((DatePicker) getFields().get("requestDate")).setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        ((DatePicker) getFields().get("requestDate")).setSelectedDate(
                new java.util.Date(model.getRequestDate().getTime()));
        ((ComboBox) getFields().get("_idCompany")).getSelectionModel().select(model.get_idCompany() - 1);
    }

    @Override
    public Budgets getModel() {
        int idCompany = ((TextField) getFields().get("idBudget")).getText().isEmpty() ? -1 : Integer.parseInt(
                ((TextField) getFields().get(
                        "idBudget")).getText());

        return new Budgets(
                idCompany,
                new Date(((DatePicker) getFields().get("requestDate")).getSelectedDate().getTime()),
                Double.parseDouble(((TextField) getFields().get("budgetAmount")).getText()),
                Integer.parseInt(((ComboBox) getFields().get(
                        "_idCompany")).getSelectionModel().getSelectedItem().toString().charAt(0) + "")
        );
    }

    @Override
    public String getModelID() {
        if (getSelectedModel() == null) return null;
        return String.valueOf(getSelectedModel().getIdBudget());
    }

    @Override
    public HashMap<String, Field> getMapFields() {
        return new HashMap<String, Field>() {{
            put("idBudget", new Field("ID", FieldType.INPUT));
            put("budgetAmount", new Field("Presupuesto", FieldType.INPUT));
            put("requestDate", new Field("Fecha de solicitud", FieldType.DATEPICKER));
            put("_idCompany", new Field("Compañia", FieldType.COMBOBOX));
        }};
    }

    @Override
    protected List<TableColumn<Budgets, ?>> createColumns() {
        TableColumn<Budgets, Integer> idBudgetColumn = new TableColumn<>("ID");
        idBudgetColumn.setCellValueFactory(new PropertyValueFactory<>("idBudget"));

        TableColumn<Budgets, Date> requestDateColumn = new TableColumn<>("Fecha de solicitud");
        requestDateColumn.setCellValueFactory(new PropertyValueFactory<>("requestDate"));

        TableColumn<Budgets, Double> budgetAmountColumn = new TableColumn<>("Presupuesto");
        budgetAmountColumn.setCellValueFactory(new PropertyValueFactory<>("budgetAmount"));

        TableColumn<Budgets, Integer> _idCompanyColumn = new TableColumn<>("Compañia");
        _idCompanyColumn.setCellValueFactory(new PropertyValueFactory<>("_idCompany"));

        List<TableColumn<Budgets, ?>> columns = new ArrayList<TableColumn<Budgets, ?>>() {{
            add(idBudgetColumn);
            add(requestDateColumn);
            add(budgetAmountColumn);
            add(_idCompanyColumn);
        }};

        columns.forEach(column -> column.prefWidthProperty().bind(tblModel.widthProperty().divide(columns.size())));
        return columns;
    }
}
