package controller;

import model.game.User;
import model.view.FriendRequest;
import model.view.Message;

import java.util.ArrayList;
import java.util.Objects;

public class FriendMenu extends Menu {
    private static FriendMenu instance;
    private final ArrayList<FriendRequest> friendRequests = new ArrayList<>();

    private FriendMenu() {
    }

    public static FriendMenu getInstance() {
        if (instance == null)
            instance = new FriendMenu();
        return instance;
    }

    public String requestsIn(String username) {
        StringBuilder builder = new StringBuilder();
        for (FriendRequest friendRequest : friendRequests)
            if (Objects.equals(friendRequest.receiver(), username))
                builder.append(friendRequest.sender()).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String requestsOut(String username) {
        StringBuilder builder = new StringBuilder();
        for (FriendRequest friendRequest : friendRequests)
            if (Objects.equals(friendRequest.sender(), username))
                builder.append(friendRequest.receiver()).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String acceptFriendRequest(String sender, String receiver) {
        for (FriendRequest friendRequest : friendRequests)
            if (sender.equals(friendRequest.sender()) &&
                    receiver.equals(friendRequest.receiver())) {
                friendRequests.remove(friendRequest);
                User.findUser(sender).addFriend(receiver);
                User.findUser(receiver).addFriend(sender);
                break;
            }
        return "empty";
    }

    public String rejectFriendRequest(String sender, String receiver) {
        for (FriendRequest friendRequest : friendRequests)
            if (sender.equals(friendRequest.sender()) &&
                    receiver.equals(friendRequest.receiver())) {
                friendRequests.remove(friendRequest);
                User.findUser(sender).removeFriend(receiver);
                User.findUser(receiver).removeFriend(sender);
                break;
            }
        return "empty";
    }

    public String friends(String username) {
        StringBuilder builder = new StringBuilder();
        for (String friend : User.findUser(username).friends())
            builder.append(friend).append(" ");
        if (builder.length() == 0)
            return "empty";
        else
            return builder.toString();
    }

    public String sendFriendRequest(String sender, String receiver) {
        if (User.findUser(receiver) == null)
            return Message.NO_USER.message();
        else {
            if (User.findUser(sender).friends().contains(receiver))
                return Message.OLD_FRIEND.message();
            else {
                friendRequests.add(new FriendRequest(sender, receiver));
                return "empty";
            }
        }
    }
}
