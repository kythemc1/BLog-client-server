package com.example.newsblog.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.newsblog.server.service.SignUp;
import com.example.newsblog.client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import static com.example.newsblog.client.utils.Utils.createDialog;

public class SignUpController implements Initializable{

//    @FXML
//    private Button backButton;

    @FXML
    private RadioButton isAdmin;

    @FXML
    private RadioButton isUser;

//    @FXML
//    private Button signUpButton;

    @FXML
    private PasswordField signUpPassword;

    @FXML
    private TextField signUpUsername;

    @FXML
    private TextField signUpEmail;

    private final ToggleGroup toggleRole = new ToggleGroup();


    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        ViewUtils a = new ViewUtils();
        a.changeScene(event, "/com/example/newsblog/sign-in.fxml");
    }

    @FXML
    void handleSignUp(ActionEvent event) {
        String inputUsername = signUpUsername.getText();
        String inputPassword = signUpPassword.getText();
        String inputEmail = signUpEmail.getText();
        int role = 1;

        if (inputUsername.trim().equals("") || inputPassword.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Khoan nào cán bộ",
                    "", "Vui lòng nhập đủ username và password!"
            );

        }   else {
            if (!isUser.isSelected() && !isAdmin.isSelected()) {
                createDialog(
                        Alert.AlertType.WARNING,
                        "Khoan nào cán bộ",
                        "", "Vui lòng chọn role cho username!"
                );
            }   else {
                if (isUser.isSelected()) role = 0;
                if (isAdmin.isSelected()) role = 1;
                SignUp signUp =new SignUp();
                if(signUp.SignUp(inputUsername,inputPassword,inputEmail,role)){
                        signUpPassword.clear();
                        signUpUsername.clear();
                        signUpEmail.clear();
                        isAdmin.setSelected(false);
                        isUser.setSelected(false);
                }else {

                }

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isAdmin.setToggleGroup(toggleRole);
        isUser.setToggleGroup(toggleRole);
    }

}