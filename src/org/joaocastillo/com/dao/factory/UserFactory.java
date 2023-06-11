package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.User;
import org.joaocastillo.com.dao.ConnectionUser;
import org.joaocastillo.com.dao.DAO;

public class UserFactory {
    public static DAO<User> getDAO() {
        return new DAO<User>(new ConnectionUser(), "sp_insert_User(?,?,?,?)",
                "sp_select_User(?)",
                "sp_select_all_User()",
                "sp_update_User(?,?,?,?,?)",
                "sp_delete_User(?)");
    }
}
