package network;

public enum Command {
    EXIT,
    LOGIN,
    REGISTER,
    REGISTER_MESSAGE,
    ;

    public static Command fromString(String command) {
        switch (command) {
            case "EXIT":
                return EXIT;
            case "LOGIN":
                return LOGIN;
            case "REGISTER":
                return REGISTER;
            case "REGISTER_MESSAGE":
                return REGISTER_MESSAGE;
            default:
                return null;
        }
    }
}
