package org.joaocastillo.com.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.joaocastillo.com.report.GenerateReport;
import org.joaocastillo.com.view.components.CompanyComponent;
import org.joaocastillo.com.view.components.DishTypeComponent;
import org.joaocastillo.com.view.components.EmployeeTypeComponent;
import org.joaocastillo.com.view.components.ProductsComponent;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PrincipalMenuController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void openEntityView(ActionEvent actionEvent) {
        // close current Screen.
        ScreenController.getInstance().getMain().getWindow().hide();

        // open new Screen.
        Stage stage = new Stage();
        ScreenController.getInstance().setStage(stage);
//        stage.setScene(ScreenController.getInstance().getMain());

        ScreenController.getInstance().activate(((MenuItem) actionEvent.getTarget()).getText());
        stage.setResizable(false);
        stage.show();
    }

    public void openGeneralReport(ActionEvent actionEvent) {
        GenerateReport.mostrarReporte("General", "Reporte General", new HashMap() {{
            put("baseDir", System.getProperty("user.dir").replaceAll("\\\\", "\\\\\\\\"));
        }});
    }
}
