package org.joaocastillo.com.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;

public class LoadFXML {
    public void loadFXML(Node node, String url) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));

        System.out.println(getClass().getResource(url));
        fxmlLoader.setRoot(node);
        fxmlLoader.setController(node);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
