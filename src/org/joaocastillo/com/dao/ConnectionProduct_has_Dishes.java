package org.joaocastillo.com.dao;


import org.joaocastillo.com.bean.Products_has_Dishes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProduct_has_Dishes implements IConnection<Products_has_Dishes> {
    @Override
    public Products_has_Dishes resulsetToObject(ResultSet resultSet) throws SQLException {
        int _idProduct = resultSet.getInt("_idProduct");
        int _idDish = resultSet.getInt("_idDish");
        int idProductDish = resultSet.getInt("idProductDish");
        return new Products_has_Dishes(idProductDish, _idProduct, _idDish);
    }

    @Override
    public void setProcedureParamsUpdate(PreparedStatement sp, Products_has_Dishes model) throws SQLException {
        sp.setInt(1, model.getIdProductDish());
        sp.setInt(2, model.get_idProduct());
        sp.setInt(3, model.get_idDish());
    }

    @Override
    public void setProcedureParamsCreate(PreparedStatement sp, Products_has_Dishes model) throws SQLException {
        sp.setInt(1, model.get_idProduct());
        sp.setInt(2, model.get_idDish());
    }

    @Override
    public void setIdParam(PreparedStatement sp, String id) throws SQLException {
        sp.setInt(1, Integer.parseInt(id));
    }
}
