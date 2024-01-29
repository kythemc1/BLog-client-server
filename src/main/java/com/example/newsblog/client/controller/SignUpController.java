package com.example.newsblog.client.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.newsblog.client.model.User;
import com.example.newsblog.client.payload.SignUpRequest;
import com.example.newsblog.client.utils.ViewUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import static com.example.newsblog.client.utils.Utils.createDialog;

public class SignUpController implements Initializable{

//    @FXML
//    private Button backButton;

//    @FXML
//    private RadioButton isAdmin;
//
//    @FXML
//    private RadioButton isUser;

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
    void handleSignUp(ActionEvent event) throws IOException {
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
//            if (!isUser.isSelected() && !isAdmin.isSelected()) {
//                createDialog(
//                        Alert.AlertType.WARNING,
//                        "Khoan nào cán bộ",
//                        "", "Vui lòng chọn role cho username!"
//                );
//            }   else {
//                if (isUser.isSelected()) role = 0;
//                if (isAdmin.isSelected()) role = 1;
                User user =new User();
                user.setRole(0);
                user.setUsername(inputUsername);
                user.setPassword(inputPassword);
                user.setEmail(inputEmail);
                SignUpRequest signUpRequest =new SignUpRequest();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonLog = gson.toJson(user);

                if(signUpRequest.requestServer(jsonLog)){
                    signUpUsername.clear();
                    signUpPassword.clear();
                    signUpEmail.clear();
                    createDialog(
                            Alert.AlertType.WARNING,
                            "Chuc mung",
                            "", "Da them thanh cong tai khoan");
                }

//            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        isAdmin.setToggleGroup(toggleRole);
//        isUser.setToggleGroup(toggleRole);
    }

}