package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Employees;
import org.joaocastillo.com.view.components.GeneralModelComponent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionEmployee implements IConnection<Employees> {
    @Override
    public Employees resulsetToObject(ResultSet resultSet) throws SQLException {

        int idEmployee = resultSet.getInt("idEmployee");
        int employeeNumber = resultSet.getInt("employeeNumber");
        String firstNamesEmployee = resultSet.getString("firstNamesEmployee");
        String lastNamesEmployee = resultSet.getString("lastNamesEmployee");
        String addressEmployee = resultSet.getString("addressEmployee");
        String contactPhoneEmployee = resultSet.getString("contactPhoneEmployee");
        String chefDegreeEmployee = resultSet.getString("chefDegreeEmployee");
        int _idEmployeeType = resultSet.getInt("_idEmployeeType");

        return new Employees(idEmployee, employeeNumber, firstNamesEmployee, lastNamesEmployee, addressEmployee,
                contactPhoneEmployee, chefDegreeEmployee, _idEmployeeType);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Employees model) throws SQLException {
        sp.setInt(1, model.getIdEmployee());
        sp.setInt(2, model.getEmployeeNumber());
        sp.setString(3, model.getFirstNamesEmployee());
        sp.setString(4, model.getLastNamesEmployee());
        sp.setString(5, model.getAddressEmployee());
        sp.setString(6, model.getContactPhone());
        sp.setString(7, model.getChefDegreeEmployee());
        sp.setInt(8, model.get_idEmployeeType());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Employees model) throws SQLException {
        sp.setInt(1, model.getEmployeeNumber());
        sp.setString(2, model.getFirstNamesEmployee());
        sp.setString(3, model.getLastNamesEmployee());
        sp.setString(4, model.getAddressEmployee());
        sp.setString(5, model.getContactPhone());
        sp.setString(6, model.getChefDegreeEmployee());
        sp.setInt(7, model.get_idEmployeeType());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
