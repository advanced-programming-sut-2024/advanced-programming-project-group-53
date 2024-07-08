package model.view;

import controller.FriendMenu;

public class FriendRequest {
    private final String sender;
    private final String receiver;

    public FriendRequest(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
        saveRequest();
    }

    public void saveRequest() {
        FriendMenu.DataBaseHandler.insertRequest(this);
    }
    public String sender() {
        return sender;
    }

    public String receiver() {
        return receiver;
    }
}
