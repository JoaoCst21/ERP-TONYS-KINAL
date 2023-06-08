package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Services_has_Employees;
import org.joaocastillo.com.dao.IConnection;

import java.sql.*;

public class ConnectionServices_has_Employees implements IConnection<Services_has_Employees> {
    @Override
    public Services_has_Employees resulsetToObject(ResultSet resultSet) throws SQLException {
        int idServiceEmployee = resultSet.getInt("idServiceEmployee");
        int _idService = resultSet.getInt("_idService");
        int _idEmployee = resultSet.getInt("_idEmployee");
        Date eventDate = resultSet.getDate("eventDate");
        Time eventTime = resultSet.getTime("eventTime");
        String eventLocation = resultSet.getString("eventLocation");

        return new Services_has_Employees(idServiceEmployee, _idService, _idEmployee, eventDate, eventTime,
                eventLocation);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Services_has_Employees model) throws SQLException {
        sp.setInt(1, model.getIdServiceEmployee());
        sp.setInt(2, model.get_idService());
        sp.setInt(3, model.get_idEmployee());
        sp.setDate(4, model.getEventDate());
        sp.setTime(5, model.getEventTime());
        sp.setString(6, model.getEventLocation());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Services_has_Employees model) throws SQLException {
        sp.setInt(1, model.get_idService());
        sp.setInt(2, model.get_idEmployee());
        sp.setDate(3, model.getEventDate());
        sp.setTime(4, model.getEventTime());
        sp.setString(5, model.getEventLocation());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
