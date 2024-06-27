package com.mygdx.game.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public boolean establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean sendMessage(Command command, String... arguments) {
        try {
            dataOutputStream.writeUTF(new Instruction(command, arguments).toString());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public Instruction receiveMessage() {
        try {
            return Instruction.fromString(dataInputStream.readUTF());
        } catch (IOException e) {
            return null;
        }
    }

    public boolean endConnection() {
        if (socket == null)
            return true;
        try {
            socket.close();
            dataInputStream.close();
            dataOutputStream.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void start() {
        this.establishConnection("localhost", 8080);
        Instruction instruction;
        do {
            instruction = receiveMessage();
        } while (instruction.command() != Command.EXIT);
    }
}
