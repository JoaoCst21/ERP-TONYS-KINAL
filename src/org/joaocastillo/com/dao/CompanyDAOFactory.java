package org.joaocastillo.com.dao;

import org.joaocastillo.com.bean.Companies;

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
