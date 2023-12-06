package com.example.newsblog.server.service;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;



import static com.example.newsblog.server.connectJDBC.DBConstants.*;

public class SignIn {
    public boolean SignIn(String username, String password){
        String SELECT_QUERY = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
                //Khai bao ket noi sql
                Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet result = preparedStatement.executeQuery();
                if (result.next()) {
                    Preferences userPreferences = Preferences.userRoot();
                    userPreferences.put("role", result.getString(4));
                    userPreferences.put("username", result.getString(2));
                    System.out.println("thanh congnggggg");
                    return true;
                }   else {
                    return  false;
                }
            }   catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
    }
}
