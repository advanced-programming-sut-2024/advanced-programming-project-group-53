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
        System.out.println("Server started on port " + serverSocket.getLocalPort() + " with IP " + serverSocket.getInetAddress().getHostAddress());
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Accepted");
                new Handler(socket);
            } catch (Exception e) {
                System.out.println("Not accepted");
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.start();
    }
}
