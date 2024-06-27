package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Handler extends Thread {
    private final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            while (true) {
                try {
                    String line = dataInputStream.readUTF();
                    Instruction instruction = Instruction.fromString(line);
                    if (instruction.command() == Command.EXIT)
                        break;
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }
}
