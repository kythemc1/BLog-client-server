package com.example.newsblog.client.controller;

import com.example.newsblog.HelloApplication;
import com.example.newsblog.client.model.Post;
import com.example.newsblog.client.payload.GetAllPostRequest;
import com.example.newsblog.client.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class HomePageController implements Initializable {

    @FXML
    private VBox postContainer;
    List<Post> posts ;

    @FXML
    private Label usernameSet;

    @FXML
    void backToLogin(ActionEvent event) throws IOException {
        ViewUtils a = new ViewUtils();
        a.changeScene(event, "/com/example/newsblog/sign-in.fxml");
    }

    @FXML
    void logout(ActionEvent event)throws IOException {
        Preferences preferences = Preferences.userNodeForPackage(HelloApplication.class);
        preferences.remove("username");
        preferences.remove("userId");

        ViewUtils a = new ViewUtils();
        a.changeScene(event, "/com/example/newsblog/sign-in.fxml");
    }

    @FXML
    void onMousePressGetPost() throws  IOException{
        try {
            posts = new ArrayList<>(getPost());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        postContainer.getChildren().setAll();
        try {
            for (Post post : posts){
//                System.out.println(post);
                FXMLLoader fxmlLoader =new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/newsblog/post-blogs.fxml"));
                VBox vBox =fxmlLoader.load();
                PostController postController =fxmlLoader.getController();
                postController.setData(post);
                postContainer.getChildren().add(vBox);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    void onMousePressChangePassword() throws IOException{
        FXMLLoader fxmlLoader =new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/com/example/newsblog/add-new-post.fxml"));
        VBox vBox =fxmlLoader.load();
        postContainer.getChildren().setAll(vBox);
//        postContainer.getChildren().add();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Preferences preferences= Preferences.userNodeForPackage(HelloApplication.class);

        try {
            if(preferences.get("username","")!="")
            {
                usernameSet.setText(preferences.get("username",""));
            }
            else {
                usernameSet.setText("Username");

            }
            posts = new ArrayList<>(getPost());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            for (Post post : posts){
//                System.out.println(post);
                FXMLLoader fxmlLoader =new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/example/newsblog/post-blogs.fxml"));
                VBox vBox =fxmlLoader.load();
                PostController postController =fxmlLoader.getController();
                postController.setData(post);
                postContainer.getChildren().add(vBox);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public List<Post> getPost() throws IOException {
        System.out.println("vao homepage ............ get post");
       GetAllPostRequest getAllPostRequest =new GetAllPostRequest();
       List<Post> list =new ArrayList<>();
       list=getAllPostRequest.requestServer();
        return list;
    }
}
