package com.example.newsblog.client.controller;

import com.example.newsblog.HelloApplication;
import com.example.newsblog.client.model.Post;
import com.example.newsblog.client.payload.AddPostRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.example.newsblog.client.utils.Utils.createDialog;

public class AddPostController implements Initializable {
    @FXML
    private TextField addPostId;
    @FXML
    private TextField addPostContent;

    @FXML
    private TextField addPostTitle;

    @FXML
    private TextField addPostLinkImage;

    @FXML
    void handleAddPost(ActionEvent event) throws IOException {
        Preferences preferences =Preferences.userNodeForPackage(HelloApplication.class);
        if(preferences.get("username","")!=""&&preferences.get("userId","")!=""){
            System.out.println(preferences.get("username",""));
            String content = addPostContent.getText();
            String title = addPostTitle.getText();
            String linkImage = addPostLinkImage.getText();
            String postId = addPostId.getText();

            if (content.trim().equals("") || title.trim().equals("")|| linkImage.trim().equals("")|| postId.trim().equals("")) {
                createDialog(
                        Alert.AlertType.WARNING,
                        "Khoan nào cán bộ",
                        "", "Vui lòng nhập đủ các trường!"
                );
            }  else {

                System.out.println("hien ra userid "+ preferences.get("username",""));


                Post post =new Post();
                post.setContent(content);
                post.setTitle(title);
                post.setImageUrl(linkImage);
                post.setPostId(Integer.parseInt(postId));
                post.setUserId(Integer.parseInt(preferences.get("userId","")));
                post.setVideoUrl(linkImage);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonLog = gson.toJson(post);
                AddPostRequest addPostRequest =new AddPostRequest();


                if(addPostRequest.requestServer(jsonLog)){
                    addPostLinkImage.clear();
                    addPostId.clear();
                    addPostContent.clear();
                    addPostTitle.clear();
                    createDialog(
                            Alert.AlertType.WARNING,
                            "Chuc mung",
                            "", "Da them thanh cong bai viet");
                }
        }


        }
        else {
            createDialog(
                    Alert.AlertType.WARNING,
                    "Cảnh báo!",
                    "Khoan nào cán bộ!",
                    "Bạn chưa đăng nhập"
            );
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
