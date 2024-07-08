package controller;

import model.card.*;
import model.cards.Deck;
import model.game.Player;
import model.game.User;

//make this usable in offline mode when we need run two player in one device.
public class StartMenu extends Menu {
    private static StartMenu instance;
    private Deck initialDeck;
    private final User user;
    private Commander commanderUser;
    private Faction userFaction;
    private Player player;

    //TODO : make it available for 2 user for interact in this menu.
    private StartMenu(String username, Faction faction, Commander commander) {
        this.user = User.findUser(username);
        this.initialDeck = new Deck();
        this.userFaction = faction;
        this.commanderUser = commander;
    }

    public static StartMenu setInstance(String username, Faction faction, Commander commander) {
        instance = new StartMenu(username, faction, commander);
        return instance;
    }

    public static StartMenu getInstance() {
        return instance;
    }

    public static void setDeck(Deck deck) {
        instance.initialDeck = deck;
    }
    public Deck getDeck() {
        return instance.initialDeck;
    }

    public boolean addSpecialToDeck(SpecialInformation specialInformation) {
        if (instance.initialDeck.specifiedCardCounter(specialInformation.name()) >= specialInformation.maxNumber()) {
            return false;
        }
        instance.initialDeck.add(new Special(specialInformation));
        return true;
    }

    public boolean addUnitToDeck(UnitInformation unitInformation) {
        if (instance.initialDeck.specifiedCardCounter(unitInformation.name()) >= unitInformation.maxNumber()) {
            return false;
        }
        instance.initialDeck.add(new Unit(unitInformation));
        return true;
    }

    public boolean availableToSave() {
        return initialDeck.specialCounter() <= 10 && initialDeck.unitCounter() >= 22;
    }

    public Commander getCommanderUser() {
        return commanderUser;
    }

    public void setCommanderUser(Commander commanderUser) {
        this.commanderUser = commanderUser;
    }

    public Faction getUserFaction() {
        return userFaction;
    }

    public void setUserFaction(Faction userFaction) {
        this.userFaction = userFaction;
    }

    public User getUser() {
        return user;
    }
}