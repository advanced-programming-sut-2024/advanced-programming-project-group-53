package network;

public enum Command {
    EXIT,
    LOGIN
    ;
    public static Command fromString(String command) {
        switch (command) {
            case "EXIT":
                return EXIT;
            default:
                return null;
        }
    }
}
