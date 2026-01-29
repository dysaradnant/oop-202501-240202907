package com.upb.agripos.controller;

import com.upb.agripos.auth.AuthService;
import com.upb.agripos.auth.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    private AuthService authService = new AuthService();

    @FXML
    public void handleLogin() {
        User user = authService.login(txtUsername.getText(), txtPassword.getText());

        if (user != null) {
            String fxml = user.getRole().equalsIgnoreCase("ADMIN") ? "/fxml/admin.fxml" : "/fxml/pos.fxml";
            loadScene(fxml, "AgriPOS - " + user.getRole(), user);
        } else {
            new Alert(Alert.AlertType.ERROR, "Login Gagal!").show();
        }
    }

    private void loadScene(String fxml, String title, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            // Jika masuk ke POS, kirim data user agar struk tahu nama kasirnya
            if (fxml.equals("/fxml/pos.fxml")) {
                PosController pc = loader.getController();
                pc.setUser(user);
            }

            Stage stage = (Stage) txtUsername.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(title);
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }
}