package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.EmployeeType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionEmployeeType implements IConnection<EmployeeType>{
	@Override
	public EmployeeType resulsetToObject(ResultSet resultSet) throws SQLException {
		int idEmployeeType = resultSet.getInt("idEmployeeType");
		String descriptionEmployeeType = resultSet.getString("descriptionEmployeeType");

		return new EmployeeType(idEmployeeType, descriptionEmployeeType);
	}

	@Override
	public void setProcedureParamsUpdate(PreparedStatement sp, EmployeeType model) throws SQLException {
		sp.setInt(1, model.getIdEmployeeType());
		sp.setString(2, model.getDescriptionEmployeeType());
	}

	@Override
	public void setProcedureParamsCreate(PreparedStatement sp, EmployeeType model) throws SQLException {
		sp.setString(1, model.getDescriptionEmployeeType());
	}

	@Override
	public void setIdParam(PreparedStatement sp, String id) throws SQLException {
		sp.setInt(1, Integer.parseInt(id));
	}
}
