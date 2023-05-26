package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.Companies;
import org.joaocastillo.com.dao.ConnectionCompany;
import org.joaocastillo.com.dao.DAO;

public class CompanyDAOFactory {
    public static DAO<Companies> getDAO() {
        return new DAO<Companies>(new ConnectionCompany(),
                "sp_insert_Companies(?,?,?)",
                "sp_select_Companies(?)",
                "sp_select_all_Companies()",
                "sp_update_Companies(?,?,?,?)",
                "sp_delete_Companies" + "(?)");
    }
}
