package com.example.newsblog.server.service;

import com.example.newsblog.client.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.newsblog.server.connectJDBC.DBConstants.*;

public class SignUp {

    public boolean SignUp(User user){
        String CREATE_QUERY = "INSERT INTO user (username, password,email, role) VALUES (?,?,?,?)";
                try {
                    Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY);
                    preparedStatement.setString(1, user.getUsername());
                    preparedStatement.setString(2, user.getPassword());
                    preparedStatement.setString(3, user.getEmail());
                    preparedStatement.setInt(4, user.getRole());
                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        return true;
                    }   else {
                        return false;
                    }
                }   catch (SQLException e) {
                    e.printStackTrace();
                }
       return false;
    }

    private boolean userExist(String username){
        return true;
    }

    private boolean emailExist(String email){
        return true;
    }
}
