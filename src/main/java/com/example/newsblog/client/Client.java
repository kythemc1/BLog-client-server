package com.example.newsblog.client;

import com.example.newsblog.client.payload.SignInRequest;

import java.net.*;
import java.io.*;


class Client{
    public static void main(String args[])throws Exception{
        System.out.println("cilent ket noi");
        Socket s=new Socket("localhost",3333);
        DataInputStream din=new DataInputStream(s.getInputStream());
        Object dout=new ObjectOutputStream(s.getOutputStream());

        SignInRequest signInRequest=new SignInRequest();
        signInRequest.setRequestsId("1");
        signInRequest.setUsername("1");
        signInRequest.setPassword("1");

        String[] arr={"1","1","1"};
        ((ObjectOutputStream) dout).writeObject(arr);
        if(din.toString()=="true")
        {
            System.out.println(" dung roi");
        }

        s.close();
    }}