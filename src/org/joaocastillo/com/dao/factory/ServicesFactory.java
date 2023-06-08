package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.Services;
import org.joaocastillo.com.dao.ConnectionServices;
import org.joaocastillo.com.dao.DAO;

public class ServicesFactory {
    public static DAO<Services> getDAO() {
        return new DAO<Services>(new ConnectionServices(), "sp_insert_Services(?,?,?,?,?,?)",
                "sp_select_Services(?)",
                "sp_select_all_Services()",
                "sp_update_Services(?,?,?,?,?,?,?)",
                "sp_delete_Services(?)");
    }
}
