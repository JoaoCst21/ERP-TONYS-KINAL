package org.joaocastillo.com.view.components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.joaocastillo.com.bean.User;
import org.joaocastillo.com.controller.LoadFXML;
import org.joaocastillo.com.controller.Pantallas;
import org.joaocastillo.com.controller.ScreenController;
import org.joaocastillo.com.dao.factory.UserFactory;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpComponent extends AnchorPane implements Initializable {
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPassword;
    @FXML
    TextField txtName;
    @FXML
    TextField txtLastName;
    @FXML
    Button btnSignUp;

    @FXML
    ImageView btnHome;

    public SignUpComponent() {
        new LoadFXML().loadFXML(this, "../view/SignUpView.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnSignUp.setOnMouseClicked(event -> {
            if (fieldsAreEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                User user = new User(-1, txtEmail.getText(), txtPassword.getText(), txtName.getText(), txtLastName.getText());
                UserFactory.getDAO().save(user);
                ScreenController.getInstance().activate(Pantallas.LOGIN);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error al crear el usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnHome.setOnMouseClicked(event -> {
            ScreenController.getInstance().activate(Pantallas.LOGIN);
        });
    }

    private boolean fieldsAreEmpty() {
        if (txtEmail.getText().isEmpty()) return true;
        if (txtPassword.getText().isEmpty()) return true;
        if (txtName.getText().isEmpty()) return true;
        if (txtLastName.getText().isEmpty()) return true;
        return false;
    }
}
