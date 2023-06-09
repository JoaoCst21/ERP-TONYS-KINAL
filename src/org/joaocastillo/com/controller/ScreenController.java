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

    private HashMap<Pantallas, ScreenChanger> screenMap = new HashMap<Pantallas, ScreenChanger>() {
        {
            put(Pantallas.MENU, () -> {
                try {
                    return FXMLLoader.load(
                            this.getClass().getResource("/org/joaocastillo/com/view/MenuPrincipalView.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            put(Pantallas.EMPRESAS, () -> new CompanyComponent());
            put(Pantallas.SERVICIOS, () -> new ServicesComponent());
            put(Pantallas.PRODUCTOS, () -> new ProductsComponent());
            put(Pantallas.TIPOS_DE_PLATOS, () -> new DishTypeComponent());
            put(Pantallas.TIPOS_DE_EMPLEADOS, () -> new EmployeeTypeComponent());
            put(Pantallas.PRESUPUESTOS, () -> new BudgetComponent());
            put(Pantallas.PLATOS, () -> new DishComponent());
            put(Pantallas.PRODUCTOS_TIENE_PLATOS, () -> new Products_has_DishesComponent());
            put(Pantallas.SERVICIOS_TIENE_PLATOS, () -> new Services_has_DishesComponent());
            put(Pantallas.SERVICIOS_TIENE_EMPLEADOS, () -> new Services_has_EmployeesComponent());
            put(Pantallas.PROGRAMADOR, () -> {
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

    public void activate(Pantallas pantallas) {
        if (this.main == null) {
            this.main = new Scene(screenMap.get(pantallas).changeScreen());
        }
        if (currentScreen != null) {
            currentScreen.setDisable(true);
        }
        Parent root = screenMap.get(pantallas).changeScreen();
        main.setRoot(root);
        currentScreen = root;
        currentStage.setScene(this.main);
        /*stage.setScene();
        stage.show();*/
    }

    public void activate(String name) {
        System.out.println(name.replaceAll(" ", "_").toUpperCase());
        System.out.println("SERVICIOS_TIENE_PLATOS" == name.replaceAll(" ", "_").toUpperCase());
        activate(Pantallas.valueOf(name.replace(' ', '_').toUpperCase()));
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
