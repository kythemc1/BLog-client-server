package com.example.newsblog.client.controller;

import com.example.newsblog.client.model.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {

    @FXML
    Label postTittle;

    @FXML
    Label postContent;

    @FXML
    ImageView postImage;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setData(Post post) {
//        postTittle.setText(post.getTitle().toString());
        postContent.setText(post.getContent().toString());

        String url=post.getImageUrl();
        if(url.length()>10){
            Image image = new Image(url,true);
            postImage.setImage(image);
        }
        else {
            url="https://media.wired.com/photos/644b1ee2a6c1fece8f4bb0d1/master/w_1920,c_limit/Ben-Smith-Jonah-Peretti-Plaintext-Business-1357479731.jpg";
            Image image = new Image(url,true);
            postImage.setImage(image);
        }

    }

//    public Post getPost(){
//        Post post1 =new Post();
//        post1.setPostId(1);
//        post1.setContent("Wagner Mutiny Puts Russia’s Military Bloggers on a Razor’s Edge120");
//        post1.setTitle("Telegram “war correspondents” have promoted the Kremlin’s invasion of Ukraine, but many have also supported mercenaries who launched a failed coup.");
//        post1.setUserId(1);
//        post1.setImageUrl("https://cdn.tuoitre.vn/thumb_w/480/471584752817336320/2023/9/15/3-1694750944226302247221.jpg");
//        return post1;
//    }
}