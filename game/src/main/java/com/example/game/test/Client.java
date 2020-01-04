package com.example.game.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Author: zhangat
 * @Date: 2019-12-23 0023 15:43
 * @DESCIBE
 */
public class Client {

    private final Socket socket;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host,port);
    }


    public void run(){
        Thread thread=new Thread(this::readResponse);
        thread.start();

        try {
            OutputStream out=socket.getOutputStream();
            byte[] buffer=new byte[1024];
            int n;
            while ((n=System.in.read(buffer))>0){
                out.write(buffer,0,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readResponse(){
        try {
            InputStream in=socket.getInputStream();
            byte[] buffer=new byte[1024];
            int n;
            while ((n=in.read(buffer))>0){
                System.out.write(buffer,0,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            Client client=new Client("localhost", 9877);
            client.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
