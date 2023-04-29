package org.joaocastillo.com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO<M> implements CRUD<M> {
    // Attributes
    private String saveProcedure;
    private String readProcedure;
    private String readAllProcedure;
    private String updateProcedure;
    private String deleteProcedure;
    private IConnection<M> connectionHelper;

    // Constructor
    public DAO(IConnection<M> iConnection, String saveProcedure, String readProcedure, String readAllProcedure, String updateProcedure, String deleteProcedure) {
        this.connectionHelper = iConnection;
        this.saveProcedure = saveProcedure;
        this.readProcedure = readProcedure;
        this.readAllProcedure = readAllProcedure;
        this.updateProcedure = updateProcedure;
        this.deleteProcedure = deleteProcedure;
    }

    protected PreparedStatement prepareCall(String procedure) throws SQLException {
        return Conexion.getInstance().getConnection().prepareCall("CALL " + procedure);
    }

    protected void printError(Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
    }

    @Override
    public void save(M model) throws Exception {
        try {
            PreparedStatement sp = prepareCall(saveProcedure);
            connectionHelper.setProcedureParamsCreate(sp, model);
            sp.execute();
        } catch (SQLException e) {
            printError(e);
            throw new Exception("no se ha podido Guardar los Datos");
        }
    }

    @Override
    public M search(String id) throws Exception {
        try {
            PreparedStatement sp = prepareCall(readProcedure);
            connectionHelper.setIdParam(sp, id);
            ResultSet resultSet = sp.executeQuery();
            while (resultSet.next()) {
                return connectionHelper.resulsetToObject(resultSet);
            }
        } catch (SQLException e) {
            printError(e);
            throw new Exception("no se ha podido Encontrar los Datos");
        }
        return null;
    }

    @Override
    public ArrayList<M> readAll() throws Exception {

        try {
            ArrayList<M> modelList = new ArrayList<>();
            PreparedStatement sp = prepareCall(readAllProcedure);
            ResultSet resultSet = sp.executeQuery();
            while (resultSet.next()) {
                modelList.add(connectionHelper.resulsetToObject(resultSet));
            }
            return modelList;
        } catch (SQLException e) {
            printError(e);
            throw new Exception("Ha habido algún error en la base de Datos");
        }
    }

    @Override
    public void update(M model) throws Exception {
        try {
            PreparedStatement sp = prepareCall(updateProcedure);
            connectionHelper.setProcedureParamsUpdate(sp, model);
            sp.execute();
        } catch (SQLException e) {
            printError(e);
            throw new Exception("no se ha podido Actualizar los datos");
        }
    }

    @Override
    public void delete(String id) throws Exception {
        try {
            PreparedStatement sp = prepareCall(deleteProcedure);
            connectionHelper.setIdParam(sp, id);
            sp.execute();
        } catch (SQLException e) {
            printError(e);
            throw new Exception("no se ha podido Eliminar los datos");
        }
    }
}
