package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Services_has_Dishes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionServices_has_Dishes implements IConnection<Services_has_Dishes> {
    @Override
    public Services_has_Dishes resulsetToObject(ResultSet resultSet) throws SQLException {
        int _idService = resultSet.getInt("_idService");
        int _idDish = resultSet.getInt("_idDish");
        int idServiceDish = resultSet.getInt("idServiceDish");
        return new Services_has_Dishes(idServiceDish, _idService, _idDish);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Services_has_Dishes model) throws SQLException {
        sp.setInt(1, model.getIdServiceDish());
        sp.setInt(2, model.get_idService());
        sp.setInt(3, model.get_idDish());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Services_has_Dishes model) throws SQLException {
        sp.setInt(1, model.get_idService());
        sp.setInt(2, model.get_idDish());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
