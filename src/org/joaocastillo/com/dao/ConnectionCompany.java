package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Companies;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionCompany implements IConnection<Companies> {
    @Override
    public Companies resulsetToObject(ResultSet resultSet) throws SQLException {
        int idCompany = resultSet.getInt("idCompany");
        String nameCompany = resultSet.getString("nameCompany");
        String addressCompany = resultSet.getString("addressCompany");
        String phoneCompany = resultSet.getString("phoneCompany");

        return new Companies(idCompany, nameCompany, addressCompany, phoneCompany);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Companies object) throws SQLException {
        sp.setInt(1, object.getIdCompany());
        sp.setString(2, object.getNameCompany());
        sp.setString(3, object.getAddressCompany());
        sp.setString(4, object.getPhoneCompany());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Companies object) throws SQLException {
        sp.setString(1, object.getNameCompany());
        sp.setString(2, object.getAddressCompany());
        sp.setString(3, object.getPhoneCompany());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
