package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Products;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProducts implements IConnection<Products>{
	@Override
	public Products resulsetToObject(ResultSet resultSet) throws SQLException {
		int idProducts = resultSet.getInt("idProduct");
		String productName = resultSet.getString("productName");
		int productQuantity = resultSet.getInt("productQuantity");


		return new Products(idProducts, productName, productQuantity);
	}

	@Override
	public void setProcedureParamsUpdate(PreparedStatement sp, Products model) throws SQLException {
		sp.setInt(1, model.getIdProduct());
		sp.setString(2, model.getProductName());
		sp.setInt(3, model.getProductQuantity());
	}

	@Override
	public void setProcedureParamsCreate(PreparedStatement sp, Products model) throws SQLException {
		sp.setString(1, model.getProductName());
		sp.setInt(2, model.getProductQuantity());
	}

	@Override
	public void setIdParam(PreparedStatement sp, String id) throws SQLException {
		sp.setInt(1, Integer.parseInt(id));
	}
}
