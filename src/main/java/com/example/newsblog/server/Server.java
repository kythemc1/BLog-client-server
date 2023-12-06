package com.example.newsblog.server;

import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Server{
    public static final int NUM_OF_THREAD = 4;
    public final static int SERVER_PORT = 1;
//    public static void main(String args[])throws Exception{
////        try {
////            System.out.println("mo socket");
////            ServerSocket ss = new ServerSocket(3333);
////            Socket s = ss.accept();
////
////            // lay du lieu tu client
////            ObjectInputStream din = new ObjectInputStream(s.getInputStream());
////
////            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
////            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////
////            Object object;
////            int i=0;
////            while (i==0) {
////
////                object = din.readObject();
////                if (object instanceof String[]) {
////                    String[] arr = (String[]) object;
////                    System.out.println("vao dayyyyy");
////                    switch (arr[0].toString()) {
////                        case "1": {
////                            String username = arr[1];
////                            String password = arr[2];
////                            SignIn signIn = new SignIn();
////                            if (signIn.SignIn(username, password) == true) {
////                                System.out.println("trueee");
////                                dout.writeUTF("true");
////                                dout.flush();
////                            } else {
////                                System.out.println("false");
////                                dout.writeUTF("false");
////                                dout.flush();
////                            }
////                            System.out.println(arr[0]);
////
////                            break;
////                        }
////
////                        case "2":
////                            System.out.println("hello");
////                            break;
////                        default:
////                            System.out.println("break");
////                            break;
////                    }
////
////                }
////
////            }
//////        din.close();
//////        s.close();
//////        ss.close();
////        }
////        catch (IOException e){
////            e.printStackTrace();
////        }
//    }
public static void main(String[] args) throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
    ServerSocket serverSocket = null;
    try {
        System.out.println("Binding to port " + SERVER_PORT + ", please wait  ...");
        serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server started: " + serverSocket);
        System.out.println("Waiting for a client ...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted: " + socket);

                WorkerThread handler = new WorkerThread(socket);
                executor.execute(handler);
            } catch (IOException e) {
                System.err.println(" Connection Error: " + e);
            }
        }
    } catch (IOException e1) {
        e1.printStackTrace();
    } finally {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}

}