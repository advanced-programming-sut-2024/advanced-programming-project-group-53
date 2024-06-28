package network;

import java.io.*;
import java.net.Socket;

public class Connector {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public void establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Connection failed");
        }
    }

    public void sendMessage(Instruction instruction) {
        try {
            dos.writeUTF(instruction.toString());
        } catch (Exception e) {
            System.out.println("Sending message failed");
        }
    }

    public Instruction receiveMessage() {
        try {
            return Instruction.fromString(dis.readUTF());
        } catch (Exception e) {
            System.out.println("Receiving message failed");
            return null;
        }
    }

    public void endConnection() {
        if (socket == null)
            return;
        try {
            socket.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            System.out.println("Disconnecting failed");
        }
    }

    public Instruction perform(Instruction instruction) {
        this.establishConnection("localhost", 8080);
        this.sendMessage(instruction);
        Instruction response = this.receiveMessage();
        this.endConnection();
        return response;
    }
}
