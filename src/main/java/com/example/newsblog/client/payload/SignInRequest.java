package com.example.newsblog.client.payload;


import com.example.newsblog.HelloApplication;

import java.io.*;
import java.net.Socket;
import java.util.prefs.Preferences;

public class SignInRequest {
    private String requestsId;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRequestId() {
        return requestsId;
    }

    public void setRequestsId(String requestsId) {
        this.requestsId = requestsId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private DataInputStream in =  null;
    public boolean requestServer(String requestsId,String username,String password) throws IOException {
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",1);
        Object dout=new ObjectOutputStream(s.getOutputStream());

        in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
        String[] arr={requestsId,username,password};
        ((ObjectOutputStream) dout).writeObject(arr);

        String str = in.readUTF();
        System.out.println(str.toString());
        if(str.equals("false"))
        {

            return false;
        }
        Preferences preferences = Preferences.userNodeForPackage(HelloApplication.class);
        preferences.put("username",username);
        preferences.put("userId",str);
        return true;
    }


    @Override
    public String toString() {
        return "SignInRequest{" +
                "requestsId='" + requestsId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
