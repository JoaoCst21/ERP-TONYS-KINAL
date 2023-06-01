package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.Dishes;
import org.joaocastillo.com.dao.ConnectionDishes;
import org.joaocastillo.com.dao.DAO;

public class DishesFactory {
    public static DAO<Dishes> getDAO() {
        return new DAO<Dishes>(new ConnectionDishes(), "sp_insert_Dishes(?,?," + "?,?,?)", "sp_select_Dishes(?)",
                "sp_select_all_Dishes()", "sp_update_Dishes(?,?,?,?,?,?)", "sp_delete_Dishes(?)");
    }
}
