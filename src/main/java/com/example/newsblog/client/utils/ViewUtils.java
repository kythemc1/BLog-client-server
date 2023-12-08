package com.example.newsblog.client.utils;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewUtils {
    public void changeScene(ActionEvent event, String viewSource) throws IOException {
        Stage stage = null;
        Scene scene= null;
        Parent root= null;
        URL fxmlLocation = getClass().getResource(viewSource);
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        root = loader.load();
        scene = new Scene(root);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void changeAnchorPane(AnchorPane currentPane, String viewSource) throws IOException {
        Node node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(viewSource)));
        currentPane.getChildren().setAll(node);
    }

}
