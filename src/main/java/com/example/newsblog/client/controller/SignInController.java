package com.example.newsblog.client.controller;

import java.io.IOException;


import com.example.newsblog.client.payload.SignInRequest;
import com.example.newsblog.client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.newsblog.client.utils.Utils.createDialog;

public class SignInController {

    @FXML
    private Button buttonLogin;

    @FXML
    private Button buttonSignup;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private TextField inputUsername;

    @FXML
    void handleLogin(ActionEvent event) throws IOException {
        String Username = inputUsername.getText();
        String Password = inputPassword.getText();
        if (Username.trim().equals("") || Password.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Cảnh báo!",
                    "Khoan nào cán bộ!",
                    "Vui lòng nhập đầy đủ username và password!"
            );
        }   else {
//            SignIn signIn =new SignIn();
//            signIn.SignIn(Username,Password);

            SignInRequest signInRequest =new SignInRequest();
            if(signInRequest.requestServer("1",Username,Password)){
                ViewUtils utils =new ViewUtils();
                utils.changeScene(event,"/com/example/newsblog/home.fxml");
            }
            ;
        }
    }

    @FXML
    void signUpbtn(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, "/com/example/newsblog/sign-up.fxml");
    }
}