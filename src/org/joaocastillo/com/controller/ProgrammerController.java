package org.joaocastillo.com.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgrammerController implements Initializable {
    @FXML private Circle circle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        circle.setFill(new ImagePattern(new Image("org/joaocastillo/com/image/photo.jpeg")));
    }
}
