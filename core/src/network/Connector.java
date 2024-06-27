package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Connector {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    public boolean establishConnection(String address, int port) {
        try {
            socket = new Socket(address, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            return true;
        } catch (Exception e) {
            System.out.println("Connection failed");
            return false;
        }
    }

    public boolean sendMessage(Command command, String... arguments) {
        try {
            dataOutputStream.writeUTF(new Instruction(command, arguments).toString());
            return true;
        } catch (Exception e) {
            System.out.println("Sending message failed");
            return false;
        }
    }

    public Instruction receiveMessage() {
        try {
            return Instruction.fromString(dataInputStream.readUTF());
        } catch (Exception e) {
            System.out.println("Receiving message failed");
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
        } catch (Exception e) {
            System.out.println("Disconnecting failed");
            return false;
        }
    }

    public Instruction perform(Instruction instruction) {
        this.establishConnection("localhost", 8080);
        this.sendMessage(instruction.command(), instruction.arguments());
        Instruction result =  this.receiveMessage();
        this.endConnection();
        return result;
    }
}
