package org.joaocastillo.com.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.joaocastillo.com.dao.DAO;

public class CompanyController<M> {

    @FXML private Button btnSave;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnReport;

    save() {
        M model = getModel();
        new DAO<M>(getConnectionHelper()).save(model);
    }

}
