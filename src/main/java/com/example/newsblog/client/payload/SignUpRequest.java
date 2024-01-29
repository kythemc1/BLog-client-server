package com.example.newsblog.client.payload;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SignUpRequest {
    private DataInputStream in =  null;
    public boolean requestServer(String user) throws IOException {
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",1);
        ObjectOutputStream dout=new ObjectOutputStream(s.getOutputStream());

        in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        String[] arr={"4",user};
        dout.writeObject(arr);

        String str = in.readUTF();
        System.out.println(str.toString());
        if(str.equals("true"))
        {
            return true;
        }
        return false;
    }
}
