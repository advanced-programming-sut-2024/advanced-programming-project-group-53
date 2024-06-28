package network;

import controller.RegisterMenu;

import java.io.*;
import java.net.Socket;

public class Handler {
    private final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
        this.run();
    }

    private void run() {
        try {
            DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(response(Instruction.fromString(dis.readUTF())).toString());
        } catch (Exception ignored) {
        }
    }

    private Instruction response(Instruction instruction) {
        switch (instruction.command()) {
            case REGISTER:
                String[] arguments = instruction.arguments();
                return new Instruction(Command.REGISTER_MESSAGE,
                        RegisterMenu.getInstance().register(arguments[0],
                                arguments[1],
                                arguments[2],
                                arguments[3],
                                arguments[4],
                                arguments[5]));
            default:
                return null;
        }
    }
}
