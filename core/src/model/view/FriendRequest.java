package model.view;

public class FriendRequest {
    private final String sender;
    private final String receiver;

    public FriendRequest(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public String sender() {
        return sender;
    }

    public String receiver() {
        return receiver;
    }
}
