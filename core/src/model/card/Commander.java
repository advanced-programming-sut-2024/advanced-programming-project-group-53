package model.card;

public class Commander {
    private final CommanderInformation commanderInformation;

    public Commander(CommanderInformation commanderInformation) {
        this.commanderInformation = commanderInformation;
    }

    public CommanderInformation getCommanderInformation() {
        return commanderInformation;
    }
}
