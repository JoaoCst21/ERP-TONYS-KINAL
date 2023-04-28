package org.joaocastillo.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnection<M> {
    public M resulsetToObject(ResultSet resultSet) throws SQLException;

    public void setProcedureParams(PreparedStatement sp, M object) throws SQLException;

    public void setIdParam(PreparedStatement sp, String id) throws SQLException;
}
