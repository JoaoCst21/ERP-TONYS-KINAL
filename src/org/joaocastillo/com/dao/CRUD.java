package org.joaocastillo.com.dao;

import java.util.ArrayList;

public interface CRUD<M> {
    public void save(M model) throws Exception;
    public M search(String id) throws Exception;
    public void update(M model) throws Exception;
    public void delete(String id) throws Exception;
    public ArrayList<M> readAll() throws Exception;
}
