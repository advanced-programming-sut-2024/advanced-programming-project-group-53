package com.mygdx.game.network;

import controller.ChatMenu;
import controller.LoginMenu;
import controller.ProfileMenu;
import controller.RegisterMenu;
import network.Command;
import network.Instruction;
import view.ChatContainer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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
        System.out.println(instruction);
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
            case RANKING_INFORMATION:
                return null;//TODO:!
            case DECK_INFORMATION:
                return null;//TODO:!
            case HAND_INFORMATION:
                return null;//TODO:!
            case DISCARD_PILE_INFORMATION:
                return null;//TODO:!
            case SEND:
                String[] strings = new String[3];
                strings[2] = arguments[arguments.length - 1];
                strings[1] = arguments[arguments.length - 2];
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < arguments.length - 2; i++)
                    builder.append(arguments[i]).append(" ");
                strings[0] = builder.toString();
                return new Instruction(Command.SEND_MESSAGE, ChatMenu.getInstance().setMessage(strings[0], strings[1], strings[2]));
            case SHOW_CHAT:
                return new Instruction(Command.SHOW_CHAT_MESSAGE, ChatMenu.getInstance().getMessage(arguments[0], arguments[1]));
            case USERNAME_VALIDATION:
                return new Instruction(Command.USERNAME_VALIDATION_MESSAGE, ChatMenu.getInstance().userValidation(arguments[0]));
            default:
                return null;
        }
    }
}
