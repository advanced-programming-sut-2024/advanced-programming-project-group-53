package controller;

import model.game.User;
import view.ChatContainer;
import view.Message;

import java.util.ArrayList;
import java.util.Objects;

public class ChatMenu extends Menu {
    private static ChatMenu instance;
    private final ArrayList<ChatContainer> messages = new ArrayList<>();

    private ChatMenu() {
    }

    public static ChatMenu getInstance() {
        if (instance == null)
            instance = new ChatMenu();
        return instance;
    }

    public String userValidation(String username) {
        User user = User.findUser(username);
        if (user == null)
            return Message.NO_USER.message();
        else
            return "empty";
    }

    public String setMessage(String message, String sender, String receiver) {
        if (message.contains("\\") || message.contains("/"))
            return Message.ILLEGAL_CHARACTER.message();
        else {
            messages.add(new ChatContainer(message, sender, receiver));
            return "empty";
        }
    }

    public String getMessage(String username1, String username2) {
        ArrayList<ChatContainer> forMe = new ArrayList<>();
        for (ChatContainer chatContainer : messages)
            if ((Objects.equals(chatContainer.receiver(), username1) &&
                    Objects.equals(chatContainer.sender(), username2)) ||
                    (Objects.equals(chatContainer.receiver(), username2) &&
                            Objects.equals(chatContainer.sender(), username1)))
                forMe.add(chatContainer);
        StringBuilder builder = new StringBuilder();
        for (ChatContainer chatContainer : forMe)
            builder.append(chatContainer.toString()).append("\\");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }
}
