package com.mygdx.game.network;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (Exception ignored) {
        }
    }
    public void start() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
                handler.join();
            } catch (Exception ignored) {
            }
        }
    }
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }
}
