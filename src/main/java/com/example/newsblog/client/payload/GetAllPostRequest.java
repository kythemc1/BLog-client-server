package com.example.newsblog.client.payload;

import com.example.newsblog.client.model.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class GetAllPostRequest {
    private DataInputStream in =  null;
    public List<Post> requestServer() throws IOException {
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",1);
        Object dout=new ObjectOutputStream(s.getOutputStream());

        in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        String[] arr={"2"};
        ((ObjectOutputStream) dout).writeObject(arr);

        String str = in.readUTF();
        System.out.println("vai ca biuuuu"+str);
        // Tạo một đối tượng Gson
        Gson gson = new Gson();

        // Sử dụng TypeToken để xác định kiểu dữ liệu List<Post>
        Type postListType = new TypeToken<List<Post>>() {}.getType();

        // Chuyển chuỗi JSON thành danh sách List<Post>
        List<Post> postList = gson.fromJson(str, postListType);
        return  postList;
    }

}
