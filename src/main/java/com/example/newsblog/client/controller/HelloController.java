package com.example.newsblog.client.controller;

import com.example.newsblog.client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick(ActionEvent event) throws IOException {

        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, "/com/example/newsblog/sign-in.fxml");
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    private void onButtonView(ActionEvent event) throws IOException {
        ViewUtils viewUtils = new ViewUtils();
        viewUtils.changeScene(event, "/com/example/newsblog/home.fxml");
    }
}