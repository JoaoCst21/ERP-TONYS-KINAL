package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.DishType;
import org.joaocastillo.com.dao.ConnectionDishType;
import org.joaocastillo.com.dao.DAO;

public class DishTypeFactory {
    public static DAO<DishType> getDAO() {
        return new DAO<DishType>(new ConnectionDishType(),
                "sp_insert_DishType(?)",
                "sp_select_DishType(?)",
                "sp_select_all_DishType()",
                "sp_update_DishType(?,?)",
                "sp_delete_DishType(?)");
    }
}
