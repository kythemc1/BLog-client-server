package com.example.newsblog.server.service;

import com.example.newsblog.client.model.Post;
import javafx.geometry.Pos;

import java.sql.*;

import static com.example.newsblog.server.connectJDBC.DBConstants.*;

public class PostService {
    public boolean addPost(Post post){
        String SELECT_QUERY = "INSERT INTO posts (postId, content, title, image, video,userId) VALUES (?, ?, ?, ?, ?,?)";
        try {
            //Khai bao ket noi sql
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setInt(1, post.getPostId());
            preparedStatement.setString(2, post.getContent());
            preparedStatement.setString(3, post.getTitle());
            preparedStatement.setString(4, post.getImageUrl());
            preparedStatement.setString(5, post.getVideoUrl());
            preparedStatement.setInt(6, post.getUserId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Bài viết đã được thêm vào cơ sở dữ liệu thành công.");
                return true;
            } else {
                System.out.println("Không thành công khi thêm bài viết vào cơ sở dữ liệu.");
                return false;
            }
        }   catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    };
    public boolean deletePost(int postId){
        String DELETE_QUERY = "DELETE FROM posts WHERE postId = ?";

        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

            // Chuẩn bị câu lệnh SQL
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, postId);

            // Thực thi câu lệnh SQL để xóa bài viết từ bảng post
            int rowsAffected = preparedStatement.executeUpdate();

            // Kiểm tra xem có bản ghi nào bị xóa hay không
            if (rowsAffected > 0) {
                System.out.println("Bài viết đã được xóa thành công.");
                return true;
            } else {
                System.out.println("Không thành công khi xóa bài viết.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean updatePost(String newContent,String newImage,String newVideo,int postId){
        String UPDATE_QUERY = "UPDATE posts SET content = ?, image = ?, video = ? WHERE postId = ?";

        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

            // Chuẩn bị câu lệnh SQL
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, newContent);
            preparedStatement.setString(2, newImage);
            preparedStatement.setString(3, newVideo);
            preparedStatement.setInt(4, postId);

            // Thực thi câu lệnh SQL để cập nhật thông tin của bài viết trong bảng post
            int rowsAffected = preparedStatement.executeUpdate();

            // Kiểm tra xem có bản ghi nào được cập nhật hay không
            if (rowsAffected > 0) {
                System.out.println("Thông tin bài viết đã được cập nhật thành công.");
                return true;
            } else {
                System.out.println("Không thành công khi cập nhật thông tin bài viết.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Post findPost(String content){
        String SELECT_QUERY= "SELECT * FROM posts WHERE content LIKE ?";
        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

            // Chuẩn bị câu lệnh SQL
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
            preparedStatement.setString(1,"%"+ content+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            Post postResponse = null;
            return (Post) resultSet;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
