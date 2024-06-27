package com.mygdx.game.network;

public class Instruction {
    private final Command command;
    private final String[] arguments;

    public Instruction(Command command, String... arguments) {
        this.command = command;
        this.arguments = arguments;
    }

    public Command command() {
        return command;
    }

    public String[] arguments() {
        return arguments;
    }

    public int argumentCount() {
        return arguments.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(command.toString());
        for (String argument : arguments)
            builder.append(" ").append(argument);
        return builder.toString();
    }

    public static Instruction fromString(String request) {
        String[] parts = request.split(" ");
        String[] arguments = new String[parts.length - 1];
        Command command = Command.fromString(parts[0]);
        for (int i = 1; i < parts.length; i++)
            arguments[i - 1] = parts[i];
        return new Instruction(command, arguments);
    }
}
