package org.joaocastillo.com.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.joaocastillo.com.view.components.*;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    Parent currentScreen;
    Stage currentStage;
    private HashMap<String, ScreenChanger> screenMap = new HashMap<String, ScreenChanger>() {
        {
            put("Menu", () -> {
                try {
                    return FXMLLoader.load(this.getClass().getResource("/org/joaocastillo/com/view/MenuPrincipalView.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            put("Empresas", () -> new CompanyComponent());
            put("Productos", () -> new ProductsComponent());
            put("Tipos de Platos", () -> new DishTypeComponent());
            put("Tipos de Empleados", () -> new EmployeeTypeComponent());
            put("Presupuestos", () -> new BudgetComponent());
            put("Platos", () -> new DishComponent());
            put("Productos tiene platos", () -> new Products_has_DishesComponent());
            put("Servicios tiene platos", () -> new DishComponent());
            put("Servivicios tiene empleados", () -> new DishComponent());
            put("Programador", () -> {
                try {
                    return FXMLLoader.load(this.getClass().getResource("../view/ProgramadorView.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    };
    private Scene main;
    private static ScreenController instance;

    public static ScreenController getInstance() {
        if (instance == null) {
            instance = new ScreenController();
        }
        return instance;
    }

    private ScreenController() {
    }

    public void activate(String name) {
        if (this.main == null) {
            this.main = new Scene(screenMap.get(name).changeScreen());
        }
        if (currentScreen != null) {
            currentScreen.setDisable(true);
        }
        Parent root = screenMap.get(name).changeScreen();
        main.setRoot(root);
        currentScreen = root;
        currentStage.setScene(this.main);
        /*stage.setScene();
        stage.show();*/
    }

    public Scene getMain() {
        return this.main;
    }

    public void setStage(Stage stage) {
//        this.main = new Scene(screenMap.get("Menu").changeScreen());
        this.currentStage = stage;
//        stage.setScene(this.main);
    }
}

interface ScreenChanger {
    Parent changeScreen();
}
