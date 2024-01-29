package com.example.newsblog.client.payload;

import com.example.newsblog.client.model.Post;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;

public class AddPostRequest {
    private DataInputStream in =  null;
    public Boolean requestServer(String post) throws IOException {
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",1);
        Object dout=new ObjectOutputStream(s.getOutputStream());

        in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        String[] arr={"3",post};
        ((ObjectOutputStream) dout).writeObject(arr);

        String str = in.readUTF();
        System.out.println("adddd"+str);
        // Tạo một đối tượng Gson
       if(str.equals("true")){
           return true;
       }
        else return false ;
    }

}
