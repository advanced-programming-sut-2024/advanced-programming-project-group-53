package Model;

import java.util.ArrayList;

public class Command {
    private final CommandList command;
    private final ArrayList<String> arguments;
    public Command(CommandList command, ArrayList<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }
    public CommandList getCommand() {
        return command;
    }
    public ArrayList<String> getArguments() {
        return arguments;
    }
}
