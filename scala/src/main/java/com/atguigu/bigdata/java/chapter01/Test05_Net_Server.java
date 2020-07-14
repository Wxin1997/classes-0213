package com.atguigu.bigdata.java.chapter01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author wx
 * @create 2020-05-19 8:56
 */
public class Test05_Net_Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //服务器
        ServerSocket server = new ServerSocket(9999);


        Socket client = server.accept();
        ObjectInputStream OIS = new ObjectInputStream(client.getInputStream());
        User05 user = (User05) OIS.readObject();


        System.out.println("服务器读取的数据为" + user.name);
    }
}
