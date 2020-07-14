package com.atguigu.bigdata.java.chapter01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author wx
 * @create 2020-05-19 8:56
 */
public class Test05_Net_Client {
    public static void main(String[] args) throws Exception {
        //客户端
        Socket socket = new Socket("localhost", 9999);
        User05 user = new User05();
        user.name = "zhangsan";
        ObjectOutputStream OOS = new ObjectOutputStream(socket.getOutputStream());
        OOS.writeObject(user);
        System.out.println("客户端发送的数据为" + user.name);
        socket.close();

    }
}
