package network;

import controller.LoginMenu;
import controller.ProfileMenu;
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
        String[] arguments = instruction.arguments();
        switch (instruction.command()) {
            case REGISTER:
                return new Instruction(Command.REGISTER_MESSAGE,
                        RegisterMenu.getInstance().register(arguments[0],
                                arguments[1],
                                arguments[2],
                                arguments[3],
                                arguments[4],
                                arguments[5],
                                arguments[6]));
            case QUESTION:
                return new Instruction(Command.QUESTION_MESSAGE, LoginMenu.getInstance().question(arguments[0]));
            case USERNAME_CHECK:
                return new Instruction(Command.FORGET_PASSWORD_MESSAGE_USER, LoginMenu.getInstance().userValidation(arguments[0]));
            case FORGET_PASSWORD:
                return new Instruction(Command.FORGET_PASSWORD_MESSAGE_PASSWORD, LoginMenu.getInstance().changePassword(arguments[0],
                        arguments[1],
                        arguments[2],
                        arguments[3]));
            case LOGIN:
                return new Instruction(Command.LOGIN_MESSAGE, LoginMenu.getInstance().login(arguments[0],
                        arguments[1]));
            case PROFILE_INFORMATION:
                return new Instruction(Command.PROFILE_MESSAGE, ProfileMenu.getInstance().getInformation(arguments[0]));
            case CHANGE_USERNAME:
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeUsername(arguments[0],
                        arguments[1]));
            case CHANGE_NICKNAME:
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeNickname(arguments[0],
                        arguments[1]));
            case CHANGE_EMAIL:
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeEmail(arguments[0],
                        arguments[1]));
            case CHANGE_PASSWORD:
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changePassword(arguments[0],
                        arguments[1],
                        arguments[2],
                        arguments[3]));
            case HISTORY_INFORMATION:
                return new Instruction(Command.HISTORY_MESSAGE, ProfileMenu.getInstance().showGameHistory(arguments[0],
                        arguments[1],
                        arguments[2]));
            default:
                return null;
        }
    }
}
