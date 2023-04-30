package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.DishType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDishType implements IConnection<DishType>{
	@Override
	public DishType resulsetToObject(ResultSet resultSet) throws SQLException {
		int idDishType = resultSet.getInt("idDishType");
		String descriptionDishType = resultSet.getString("descriptionDishType");

		return new DishType(idDishType, descriptionDishType);
	}

	@Override
	public void setProcedureParamsUpdate(PreparedStatement sp, DishType model) throws SQLException {
		sp.setInt(1, model.getIdDishType());
		sp.setString(2, model.getDescriptionDishType());
	}

	@Override
	public void setProcedureParamsCreate(PreparedStatement sp, DishType model) throws SQLException {
		sp.setString(1, model.getDescriptionDishType());
	}

	@Override
	public void setIdParam(PreparedStatement sp, String id) throws SQLException {
		sp.setInt(1, Integer.parseInt(id));
	}
}
