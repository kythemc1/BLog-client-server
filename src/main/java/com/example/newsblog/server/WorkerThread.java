package com.example.newsblog.server;

import com.example.newsblog.client.model.Post;
import com.example.newsblog.client.model.User;
import com.example.newsblog.server.service.HomeService;
import com.example.newsblog.server.service.PostService;
import com.example.newsblog.server.service.SignIn;
import com.example.newsblog.server.service.SignUp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class WorkerThread extends Thread {
    private final Socket socket;

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
                if (object instanceof String[] arr) {
                    System.out.println("vao dayyyyy");
                    switch (arr[0]) {
                        // dang nhap
                        case "1" -> {
                            String username = arr[1];
                            String password = arr[2];
                            SignIn signIn = new SignIn();
                            String result = signIn.SignIn(username,password);
                            if (result!=null) {
                                System.out.println(result);
                                dout.writeUTF(result);
                                dout.flush();
                            } else {
                                System.out.println("false");
                                dout.writeUTF("false");
                                dout.flush();
                            }
                            System.out.println(arr[0]);

                        }

                        // get all post
                        case "2" -> {
                            System.out.println("all post");
                            HomeService homeService = new HomeService();
                            List<Post> posts = homeService.getAllPost();
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            String jsonLog = gson.toJson(posts);
                            System.out.println(jsonLog);
                            dout.writeUTF(jsonLog);
                            dout.flush();
                        }
                        // add post
                        case "3" -> {
                            System.out.println("case add post" + arr[1]);
                            PostService postService = new PostService();
                            Gson gson2 = new Gson();
                            String str = arr[1];

                            Post post = gson2.fromJson(str, Post.class);
                            if (postService.addPost(post)) {
                                dout.writeUTF("true");
                                dout.flush();
                            } else {
                                dout.writeUTF("false");
                                dout.flush();
                            }

                        }
                        case "4" -> {
                            System.out.println("sign uppp" + arr[1]);
                            Gson gson3 = new Gson();
                            String str = arr[1];
                            SignUp signUp = new SignUp();
                            User user = gson3.fromJson(str, User.class);
                            if (signUp.SignUp(user)) {
                                dout.writeUTF("true");
                                dout.flush();
                            } else {
                                dout.writeUTF("false");
                                dout.flush();
                            }
                        }
                        case "5" ->{
                            System.out.println("find post by content"+ arr[1]);
                            Gson gson3 = new Gson();
                            String str = arr[1];
                            PostService postService=new PostService();
                            postService.findPost(str);
                        }
                        default -> System.out.println("break");
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