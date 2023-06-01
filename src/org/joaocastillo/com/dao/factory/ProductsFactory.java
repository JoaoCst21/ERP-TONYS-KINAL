package org.joaocastillo.com.dao.factory;

import org.joaocastillo.com.bean.Products;
import org.joaocastillo.com.dao.ConnectionProducts;
import org.joaocastillo.com.dao.DAO;
import org.joaocastillo.com.dao.IConnection;

public class ProductsFactory {
    public static DAO<Products> getDAO() {
        return new DAO<Products>(new ConnectionProducts(),
                "sp_insert_Products(?,?)",
                "sp_select_Products(?)",
                "sp_select_all_Products()",
                "sp_update_Products(?,?,?)",
                "sp_delete_Products(?)");
    }
}
