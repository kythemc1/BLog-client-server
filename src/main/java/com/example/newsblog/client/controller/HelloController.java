package com.example.newsblog.client.controller;

import com.example.newsblog.client.model.Post;
import com.example.newsblog.client.utils.ViewUtils;
import com.example.newsblog.server.service.PostService;
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
        viewUtils.changeScene(event, "/com/example/newsblog/home.fxml");

        welcomeText.setText("Welcome to JavaFX Application!");
    }
}