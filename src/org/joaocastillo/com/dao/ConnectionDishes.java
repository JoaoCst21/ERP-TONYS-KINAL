package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Dishes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDishes implements IConnection<Dishes> {


    @Override
    public Dishes resulsetToObject(ResultSet resultSet) throws SQLException {
        int idDish = resultSet.getInt("idDish");
        int quantity = resultSet.getInt("quantity");
        String dishName = resultSet.getString("dishName");
        String dishDescription = resultSet.getString("dishDescription");
        double dishPrice = resultSet.getDouble("dishPrice");
        int _idDishType = resultSet.getInt("_idDishType");

        return new Dishes(idDish, quantity, dishName, dishDescription, dishPrice, _idDishType);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Dishes model) throws SQLException {
        sp.setInt(1, model.getIdDish());
        sp.setInt(2, model.getQuantity());
        sp.setString(3, model.getDishName());
        sp.setString(4, model.getDescriptionDish());
        sp.setDouble(5, model.getDishPrice());
        sp.setInt(6, model.get_idDishType());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Dishes model) throws SQLException {
        System.out.println(model.getDescriptionDish());
        sp.setInt(1, model.getQuantity());
        sp.setString(2, model.getDishName());
        sp.setString(3, model.getDescriptionDish());
        sp.setDouble(4, model.getDishPrice());
        sp.setInt(5, model.get_idDishType());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
