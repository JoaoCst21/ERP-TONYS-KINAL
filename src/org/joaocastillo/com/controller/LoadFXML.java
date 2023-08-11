package org.joaocastillo.com.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class LoadFXML {
    public void loadFXML(Node node, String url) {
        try {
            // awkward solution to a problem with the path of the fxml files in Netbeans
            System.out.println(url.replace("..", "/org/joaocastillo/com"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url.replace("..", "/org/joaocastillo/com")));
            fxmlLoader.setRoot(node);
            fxmlLoader.setController(node);

            fxmlLoader.load();
        } catch (IOException exception) {
            exception.printStackTrace();
//            throw new RuntimeException(exception);
        }
    }
}
