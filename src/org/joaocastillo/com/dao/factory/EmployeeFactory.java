package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.Employees;
import org.joaocastillo.com.dao.ConnectionEmployee;
import org.joaocastillo.com.dao.DAO;

public class EmployeeFactory {
    public static DAO<Employees> getDAO() {
        return new DAO<Employees>(new ConnectionEmployee(), "sp_insert_Employees(?,?,?,?,?,?,?)",
                "sp_select_Employees(?)", "sp_select_all_Employees()", "sp_update_Employees(?,?,?,?,?,?,?,?)",
                "sp_delete_Employees(?)");
    }
}
