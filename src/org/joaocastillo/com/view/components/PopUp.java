package org.joaocastillo.com.view.components;

import javafx.animation.TranslateTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.joaocastillo.com.controller.LoadFXML;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class PopUp extends AnchorPane implements Initializable {
    @FXML
    Button closeButton;

    @FXML
    Text message;

    @FXML
    Button confirmationButton;

    // Attributes
    boolean isError = false;

    Exception error;

    static ArrayList<Stage> notifications = new ArrayList<>();

    public PopUp(Exception error) {
        if (error != null) {
            isError = true;
            this.error = error;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Pop-up.fxml"));
        fxmlLoader.setController(this);

        try {
            AnchorPane root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.show();

            push(stage);
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX(screenBounds.getMaxX() - stage.getWidth() - 50);
            stage.setY(screenBounds.getMaxY() - stage.getHeight() - 100);


            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
                stage.close();
            }));
            timeline.setCycleCount(1); // Run only once
            timeline.play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PopUp() {
        this(null);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INITIALIZE");
        System.out.println(closeButton);
        closeButton.setOnMouseClicked(e -> {
            System.out.println("hello");
            Stage stage = ((Stage) closeButton.getScene().getWindow());
            remove(stage);
            stage.close();
        });

        System.out.println(isError);
        if (!isError) return;

        System.out.println(error.getMessage());
        message.setText(error.getMessage());
        message.setStyle("-fx-text-fill: red");
        confirmationButton.setStyle("-fx-background-color: red");
    }

    private void push(Stage popUpStage) {
        notifications.add(popUpStage);
        notifications.forEach(notification -> {
//            double yOffset = -20; // Adjust this value as needed
//            TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), notification.getScene().getRoot());
//            translateTransition.setByY(yOffset);
//
//            // Update notifications list and play the animation
//            translateTransition.setOnFinished(event -> notification.setY(notification.getY() + notification.getHeight() + yOffset));
//            translateTransition.play();

            notification.setY(notification.getY() - notification.getHeight() - 20);
        });
    }

    private void remove(Stage popUpStage) {
        boolean found = false;
        for (Stage notification : notifications) {

            notification.setY(notification.getY() + notification.getHeight() + 20);
            if (notification == popUpStage) break;
//            if (!found) continue;
        }
        notifications.remove(popUpStage);

    }
}
