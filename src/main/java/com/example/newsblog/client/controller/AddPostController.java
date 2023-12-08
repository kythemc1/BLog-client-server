package com.example.newsblog.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.newsblog.client.utils.Utils.createDialog;

public class AddPostController implements Initializable {
    @FXML
    private TextField addPostContent;

    @FXML
    private TextField addPostTitle;

    @FXML
    private TextField addPostLinkImage;

    @FXML
    void handleAddPost(ActionEvent event) {
        String content = addPostContent.getText();
        String title = addPostTitle.getText();
        String linkImage = addPostLinkImage.getText();

        if (content.trim().equals("") || title.trim().equals("")|| linkImage.trim().equals("")) {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Khoan nào cán bộ",
                    "", "Vui lòng nhập đủ các trường!"
            );
        }  else {


        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
