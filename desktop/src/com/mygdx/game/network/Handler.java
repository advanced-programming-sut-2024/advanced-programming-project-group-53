package com.mygdx.game.network;

import controller.*;
import game.GWENT;
import model.game.User;
import network.Command;
import network.Instruction;
import network.MyJWT;

import java.io.*;
import java.net.Socket;
import java.util.Objects;

public class Handler extends Thread {
    private final Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
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
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.PROFILE_MESSAGE, ProfileMenu.getInstance().getInformation(arguments[0]));
            case CHANGE_USERNAME:
                if (MyJWT.validateToken(arguments[1]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeUsername(arguments[0],
                        arguments[1]));
            case CHANGE_NICKNAME:
                if (MyJWT.validateToken(arguments[1]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeNickname(arguments[0],
                        arguments[1]));
            case CHANGE_EMAIL:
                if (MyJWT.validateToken(arguments[1]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changeEmail(arguments[0],
                        arguments[1]));
            case CHANGE_PASSWORD:
                if (MyJWT.validateToken(arguments[3]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.CHANGE_FIELD_MESSAGE, ProfileMenu.getInstance().changePassword(arguments[0],
                        arguments[1],
                        arguments[2],
                        arguments[3]));
            case HISTORY_INFORMATION:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.HISTORY_MESSAGE, ProfileMenu.getInstance().showGameHistory(arguments[0],
                        arguments[1],
                        arguments[2]));
            case RANKING:
                return new Instruction(Command.RANKING_MESSAGE, ProfileMenu.getInstance().showRanking());
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
                if (MyJWT.validateToken(strings[1]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.SEND_MESSAGE, ChatMenu.getInstance().setMessage(strings[0], strings[1], strings[2]));
            case SHOW_CHAT:
                if (MyJWT.validateToken(arguments[1]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.SHOW_CHAT_MESSAGE, ChatMenu.getInstance().getMessage(arguments[0], arguments[1]));
            case USERNAME_VALIDATION:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.USERNAME_VALIDATION_MESSAGE, ChatMenu.getInstance().userValidation(arguments[0]));
            case REGISTER_VALIDATION:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.REGISTER_VALIDATION_MESSAGE, RegisterMenu.getInstance().registerValidate(arguments[0],
                        arguments[1],
                        arguments[2],
                        arguments[3]));
            case EMAIL_VALIDATION:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.EMAIL_VALIDATION_MESSAGE, RegisterMenu.getInstance().sendAuthorizationEmail(arguments[0],
                        arguments[1]));
            case REQUEST_IN:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.REQUEST_IN_MESSAGE, FriendMenu.getInstance().requestsIn(arguments[0]));
            case REQUEST_OUT:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.REQUEST_OUT_MESSAGE, FriendMenu.getInstance().requestsOut(arguments[0]));
            case FRIEND:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.FRIEND_MESSAGE, FriendMenu.getInstance().friends(arguments[0]));
            case FRIEND_REQUEST:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                if (Objects.equals(arguments[2], "true"))
                    return new Instruction(Command.FRIEND_REQUEST_MESSAGE, FriendMenu.getInstance().acceptFriendRequest(arguments[0], arguments[1]));
                else
                    return new Instruction(Command.FRIEND_REQUEST_MESSAGE, FriendMenu.getInstance().rejectFriendRequest(arguments[0], arguments[1]));
            case SEND_REQUEST:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                return new Instruction(Command.SEND_REQUEST_MESSAGE, FriendMenu.getInstance().sendFriendRequest(arguments[0], arguments[1]));
            case SEARCH_RANDOM:
                if (MyJWT.validateToken(arguments[0]))
                    return new Instruction(Command.EXPIRE);
                PregameMenu.getInstance().addToWaiting(arguments[0], new GWENT());
                return new Instruction(Command.EMPTY);
            default:
                return null;
        }
    }
}
