package com.example.game.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: zhangat
 * @Date: 2019-12-23 0023 15:47
 * @DESCIBE
 */
public class Server {

    private final ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }


    public void run() throws IOException {
        Socket client=serverSocket.accept();
        handleClent(client);
    }

    private void handleClent(Socket socket) throws IOException {
        InputStream input=socket.getInputStream();
        OutputStream out=socket.getOutputStream();
        byte[] buffer=new byte[1024];
        int n;
        while ((n=input.read(buffer))>0){
            out.write(buffer,0,n);
        }
    }


    public static void main(String[] args) {
        try {
            Server server=new Server(9877);
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
