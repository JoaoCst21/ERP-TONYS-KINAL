package org.joaocastillo.com.view.components;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.joaocastillo.com.controller.LoadFXML;
import org.joaocastillo.com.controller.Pantallas;
import org.joaocastillo.com.controller.ScreenController;
import org.joaocastillo.com.dao.factory.UserFactory;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginComponent extends AnchorPane implements Initializable {


    @FXML
    Button btnLogIn;
    @FXML
    Hyperlink btnNewAccount;

    @FXML
    TextField txtEmail;
    @FXML TextField txtPassword;

    public LoginComponent() {
        new LoadFXML().loadFXML(this, "../view/LoginView.fxml");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnLogIn.setOnMouseClicked(event -> {
            if (txtEmail.getText().isEmpty()) return;
            if (txtPassword.getText().isEmpty()) return;
            try {
                boolean isUserLogged = UserFactory.getDAO().readAll().stream().anyMatch(user -> {
                    if (!user.getUserEmail().equals(txtEmail.getText())) return false;
                    if (!user.getUserPassword().equals(txtPassword.getText())) return false;
                    return true;
                });

                if (!isUserLogged) {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ScreenController.getInstance().activate(Pantallas.MENU);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnNewAccount.setOnMouseClicked(event -> {
            ScreenController.getInstance().activate(Pantallas.CREATE_USER);
        });
    }
}
