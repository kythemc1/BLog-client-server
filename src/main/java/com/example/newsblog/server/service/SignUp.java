package com.example.newsblog.server.service;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.newsblog.server.connectJDBC.DBConstants.*;
import static com.example.newsblog.client.utils.Utils.createDialog;

public class SignUp {

    public boolean SignUp(String username,String password, String email,int role){
        String CREATE_QUERY = "INSERT INTO user (username, password,email, role) VALUES (?,?,?,?)";
                try {
                    Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement = conn.prepareStatement(CREATE_QUERY);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    preparedStatement.setString(3, email);
                    preparedStatement.setInt(4, role);
                    int result = preparedStatement.executeUpdate();
                    if (result == 1) {
                        createDialog(
                                Alert.AlertType.CONFIRMATION,
                                "Thành công",
                                "", "Đăng ký người dùng mới thành công!"
                        );
                        return true;
                    }   else {
                        createDialog(
                                Alert.AlertType.ERROR,
                                "Thất bại",
                                "", "Đăng ký người dùng mới thất bại!"

                        );
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
