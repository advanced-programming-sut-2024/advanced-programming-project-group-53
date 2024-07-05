package view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatContainer {
    private String message;
    private String sender;
    private String receiver;
    private Date date;

    public ChatContainer(String message, String sender, String receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.date = new Date();
    }

    public String message() {
        return message;
    }

    public String sender() {
        return sender;
    }

    public String receiver() {
        return receiver;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(message);
        builder.append("/");
        builder.append(sender);
        builder.append("/");
        builder.append(receiver);
        builder.append("/");
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy HH:mm:ss");
        builder.append(sdf.format(date));
        return builder.toString();
    }

}
