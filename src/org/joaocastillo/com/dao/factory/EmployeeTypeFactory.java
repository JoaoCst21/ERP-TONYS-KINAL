package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.EmployeeType;
import org.joaocastillo.com.dao.ConnectionEmployeeType;
import org.joaocastillo.com.dao.DAO;

public class EmployeeTypeFactory {
    public static DAO<EmployeeType> getDAO() {
        return new DAO<EmployeeType>(new ConnectionEmployeeType(),
                "sp_insert_EmployeeType(?)",
                "sp_select_EmployeeType(?)",
                "sp_select_all_EmployeeType()",
                "sp_update_EmployeeType(?,?)",
                "sp_delete_EmployeeType(?)");
    }
}
