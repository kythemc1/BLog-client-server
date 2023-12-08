module com.example.newsblog {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.prefs;
    requires com.fasterxml.jackson.databind;
    requires org.json;
    requires com.google.gson;
    exports com.example.newsblog.client.model;
    opens com.example.newsblog.client.model to com.google.gson;
    opens com.example.newsblog to javafx.fxml;
    exports com.example.newsblog;
    exports com.example.newsblog.client.controller;
    opens com.example.newsblog.client.controller to javafx.fxml;
    exports com.example.newsblog.client;
    opens com.example.newsblog.client to javafx.fxml;
}