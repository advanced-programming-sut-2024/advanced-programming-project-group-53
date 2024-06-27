package network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Handler {
    private final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
        this.run();
    }

    private void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            String line = dataInputStream.readUTF();
            dataOutputStream.writeUTF(response(Instruction.fromString(line)).toString());
        } catch (Exception ignored) {
        }
    }

    private Instruction response(Instruction instruction) {
        return instruction;
    }
}
