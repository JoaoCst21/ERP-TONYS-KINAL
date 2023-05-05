package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Budgets;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionBudget implements IConnection<Budgets> {
    @Override
    public Budgets resulsetToObject(ResultSet resultSet) throws SQLException {
        int idBudget = resultSet.getInt("idBudget");
        Date requestDate = resultSet.getDate("requestDate");
        double budgetAmount = resultSet.getDouble("budgetAmount");
        int _idCompany = resultSet.getInt("_idCompany");

        return new Budgets(idBudget, requestDate, budgetAmount, _idCompany);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Budgets model) throws SQLException {
        sp.setInt(1, model.getIdBudget());
        sp.setDate(2, model.getRequestDate());
        sp.setDouble(3, model.getBudgetAmount());
        sp.setInt(4, model.get_idCompany());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Budgets model) throws SQLException {
        sp.setDate(1, model.getRequestDate());
        sp.setDouble(2, model.getBudgetAmount());
        sp.setInt(3, model.get_idCompany());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
