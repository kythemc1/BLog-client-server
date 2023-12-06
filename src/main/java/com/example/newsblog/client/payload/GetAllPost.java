package com.example.newsblog.client.payload;

import com.example.newsblog.client.model.Post;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GetAllPost {
    private DataInputStream in =  null;
    public List<Post> requestServer() throws IOException {
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",1);
        Object dout=new ObjectOutputStream(s.getOutputStream());

        in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        String[] arr={"2"};
        ((ObjectOutputStream) dout).writeObject(arr);

        String str = in.readUTF();
        System.out.println(str.toString());

        return null;
    }

}
