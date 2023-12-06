package com.example.newsblog.server;

import com.example.newsblog.server.service.HomeService;
import com.example.newsblog.server.service.SignIn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WorkerThread extends Thread {
    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }
    public void run() {
        System.out.println("Processing: " + socket);
        try {
            ObjectInputStream din = new ObjectInputStream(socket.getInputStream());

            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Object object;
            int i=0;
            while (i==0) {

                object = din.readObject();
                if (object instanceof String[]) {
                    String[] arr = (String[]) object;
                    System.out.println("vao dayyyyy");
                    switch (arr[0].toString()) {
                        case "1": {
                            String username = arr[1];
                            String password = arr[2];
                            SignIn signIn = new SignIn();
                            if (signIn.SignIn(username, password) == true) {
                                System.out.println("trueee");
                                dout.writeUTF("true");
                                dout.flush();
                            } else {
                                System.out.println("false");
                                dout.writeUTF("false");
                                dout.flush();
                            }
                            System.out.println(arr[0]);

                            break;
                        }

                        case "2":
                            System.out.println("all post");
                            HomeService homeService =new HomeService();
                            String str = homeService.getAllPost().toString();
                            dout.writeUTF(str);
                            dout.flush();
                            break;
                        default:
                            System.out.println("break");
                            break;
                    }

                }

            }
//        din.close();
//        s.close();
//        ss.close();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("Complete processing: " + socket);
    }
}