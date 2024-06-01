package model.command;

import java.util.ArrayList;

public class Instruction {
    private final Command command;
    private final ArrayList<String> arguments;

    public Instruction(Command command, ArrayList<String> arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command command() {
        return command;
    }

    public ArrayList<String> arguments() {
        return arguments;
    }

    public int argumentCount() {
        return arguments.size();
    }
}