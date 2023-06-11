package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUser implements IConnection<User>{
    @Override
    public User resulsetToObject(ResultSet resultSet) throws SQLException {
        int idUser = resultSet.getInt("idUser");
        String userEmail = resultSet.getString("userEmail");
        String userPassword = resultSet.getString("userPassword");
        String userName = resultSet.getString("userName");
        String userLastName = resultSet.getString("userLastName");

        return new User(idUser, userEmail, userPassword, userName, userLastName);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, User model) throws SQLException {
        sp.setInt(1, model.getIdUser());
        sp.setString(2, model.getUserEmail());
        sp.setString(3, model.getUserPassword());
        sp.setString(4, model.getUserName());
        sp.setString(5, model.getUserLastName());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, User model) throws SQLException {
        sp.setString(1, model.getUserEmail());
        sp.setString(2, model.getUserPassword());
        sp.setString(3, model.getUserName());
        sp.setString(4, model.getUserLastName());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
