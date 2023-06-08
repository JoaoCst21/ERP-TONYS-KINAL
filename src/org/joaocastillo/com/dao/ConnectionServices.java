package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Services;

import java.sql.*;

public class ConnectionServices implements IConnection<Services> {

    @Override
    public Services resulsetToObject(ResultSet resultSet) throws SQLException {
        int idService = resultSet.getInt(1);
        Date dateService = resultSet.getDate(2);
        String typeService = resultSet.getString(3);
        Time timeService = resultSet.getTime(4);
        String locationService = resultSet.getString(5);
        String contactPhoneService = resultSet.getString(6);
        int _idCompany = resultSet.getInt(7);

        return new Services(idService,
                dateService,
                typeService,
                timeService,
                locationService,
                contactPhoneService,
                _idCompany);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Services model) throws SQLException {
        sp.setInt(1, model.getIdService());
        sp.setDate(2, model.getDateService());
        sp.setString(3, model.getTypeService());
        sp.setTime(4, model.getTimeService());
        sp.setString(5, model.getLocationService());
        sp.setString(6, model.getContactPhoneService());
        sp.setInt(7, model.get_idCompany());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Services model) throws SQLException {
        sp.setDate(1, model.getDateService());
        sp.setString(2, model.getTypeService());
        sp.setTime(3, model.getTimeService());
        sp.setString(4, model.getLocationService());
        sp.setString(5, model.getContactPhoneService());
        sp.setInt(6, model.get_idCompany());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
