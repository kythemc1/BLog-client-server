package com.example.newsblog.server.service;

import com.example.newsblog.client.model.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.newsblog.server.connectJDBC.DBConstants.*;

public class HomeService {
    public List<Post> getAllPost(){
        List<Post> posts = new ArrayList<>();
        String SELECT_ALL_QUERY = "SELECT * FROM posts";

        try {
            // Kết nối đến cơ sở dữ liệu
            Connection conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

            // Chuẩn bị câu lệnh SQL
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_QUERY);

            // Thực thi câu lệnh SQL để lấy tất cả các bài viết từ bảng post
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý kết quả trả về
            while (resultSet.next()) {
                Post post = new Post();
                post.setPostId(resultSet.getInt("postId"));
                post.setContent(resultSet.getString("content"));
                post.setTitle(resultSet.getString("title"));
                post.setImageUrl(resultSet.getString("image"));
                post.setVideoUrl(resultSet.getString("video"));
                post.setUserId(resultSet.getInt("userId"));
                // Thêm bài viết vào danh sách
                posts.add(post);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
