package controller;

import model.card.*;
import model.cards.Deck;
import model.game.Player;
import model.game.User;
import model.menu.MenuName;
import view.message.MenuMessage;

//make this usable in offline mode when we need run two player in one device.
public class StartMenu extends Menu {
    private static StartMenu instance;
    private Deck initialDeck;
    private final User user1, user2;
    private Player player1, player2;
    private Commander commanderUser;
    private Faction userFaction;
    private boolean hasFaction;

    //TODO : make it available for 2 user for interact in this menu.
    private StartMenu(User user1,User user2) {
        super.setMenuName(MenuName.StartMenu);
        this.user1 = user1;
        this.user2 = user2;
        this.initialDeck = new Deck();
        this.userFaction = null;
        this.hasFaction = false;
        this.commanderUser = null;
    }

    public static StartMenu getInstance() {
        if (instance == null)
            setInstance();
        return instance;
    }

    public static void setInstance() {
        //instance = new StartMenu(User.getCurrentUser(), User.getCurrentUser().getOpponent());
    }

    /*public static void showFactions() {
        Printer.print("FACTIONS:");
        for (Faction faction : Faction.values())
            Printer.print(faction.name());
    }

    public static void selectFaction(String factionName) {
        Faction selectedFaction = Faction.getFactionByName(factionName);
        if (selectedFaction == null  || selectedFaction == Faction.Neutral) {
            Printer.print(MenuMessage.INVALID_FACTION.message());
            return;
        }
        StartMenu.getInstance().setUserFaction(selectedFaction);
        StartMenu.getInstance().setHasFaction(true);
        Printer.print(MenuMessage.FACTION_SELECTED.message());
    }
    public static void showCards() {
        if (!getInstance().isHasFaction()) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return;
        }
        Printer.print("Unit Cards:");
        for (UnitInformation unitInformation : UnitInformation.values()) {
            if (unitInformation.faction() == getInstance().getUserFaction()) {
                String HeroCondition = (unitInformation.isHero()) ? "Hero" : "";
                Printer.print(unitInformation.name() + " : " + unitInformation.power() + "-" + unitInformation.faction() +
                        " -Ability: " + unitInformation.ability() + " " + HeroCondition);
            }
        }
        Printer.print("Special Cards:");
        for (SpecialInformation specialInformation : SpecialInformation.values()) {
            if (specialInformation.faction() == getInstance().getUserFaction()) {
                Printer.print(specialInformation.name() + " : " + specialInformation.power() + "-" + specialInformation.faction() +
                        " - " + specialInformation.type() + "-Ability:" + specialInformation.ability());
            }
        }
    }
    public static int nameAndCountValidation(String cardName, String countStr) {
        int state = StartMenu.nameValidation(cardName);
        if (state == 0) {
            Printer.print(MenuMessage.THERE_IS_NO_CARD_WITH_THIS_NAME.message());
            return 0;
        }
        int count;
        try {
            count = Integer.parseInt(countStr);
        } catch (NumberFormatException e) {
            Printer.print(MenuMessage.WRONG_NUMBER_FORMAT.message());
            return 0;
        }
        if (count >= 10 || count <= 0) {
            Printer.print(MenuMessage.COUNT_OUT_OF_RANGE.message());
            return 0;
        }
        return state;
    }
    //For add card(s) with a specific count to initial deck we have before start the game.
    public static boolean addToDeck(String cardName, String countStr) {
        if (!getInstance().isHasFaction()) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return false;
        }
        StartMenu currentStartMenu = StartMenu.getInstance();
        int state = StartMenu.nameAndCountValidation(cardName, countStr);
        if (state == 0) return false;
        int count = Integer.parseInt(countStr);
        int countCurrentDeck = getInstance().initialDeck.specifiedCardCounter(cardName);
        if (state == 1) {
            UnitInformation unitInfo = UnitInformation.getUnitInformationByName(cardName);
            assert unitInfo != null;
            if (unitInfo.faction() != getInstance().getUserFaction() && unitInfo.faction() != Faction.Neutral) {
                Printer.print(MenuMessage.INVALID_FACTION.message());
                return false;
            }
            if (count + countCurrentDeck > unitInfo.maxNumber()) {
                Printer.print(MenuMessage.MORE_THAT_AVAILABILITY.message());
                return false;
            }
            for (int i = 0; i < count; i++) {
                currentStartMenu.getInitialDeck().add(new Unit(unitInfo));
            }
        } else if (state == 2) {
            SpecialInformation specialInfo = SpecialInformation.getSpecialInformationByName(cardName);
            assert specialInfo != null;
            if (specialInfo.faction() != getInstance().getUserFaction() && specialInfo.faction() != Faction.Neutral) {
                Printer.print(MenuMessage.INVALID_FACTION.message());
                return false;
            }
            if (count + countCurrentDeck > specialInfo.maxNumber()) {
                Printer.print(MenuMessage.MORE_THAT_AVAILABILITY.message());
                return false;
            }
            for (int i = 0; i < count; i++) {
                currentStartMenu.getInitialDeck().add(new Special(specialInfo));
            }
        }
        Printer.print(MenuMessage.ADD_CARD.message());
        return true;
    }
    //For showing current initial deck.
    public static void showDeck() {
        Printer.print("CURRENT DECK:");
        Deck currentDeck = StartMenu.getInstance().getInitialDeck();
        for (int i = 0; i < currentDeck.size(); i++) {
            Printer.print((i + 1) + ". " + currentDeck.cardAt(i).getName() + " " + currentDeck.cardAt(i).getType() + "-"
            + currentDeck.cardAt(i).getFaction());
        }
    }
    //For showing current user information.
    public static void showCurrentUserInformation() {
        //User currentUser = User.getCurrentUser();
        //Printer.print(currentUser.username());
        if (StartMenu.getInstance().hasFaction) Printer.print("Faction: " + StartMenu.getInstance().userFaction);
        //TODO : make this printer for all deck size not only one current deck.(Or maybe not, according to project doc.
        Printer.print("Current Deck Size: " + StartMenu.getInstance().getInitialDeck().size());
        Printer.print("Unit Cards Count: " + StartMenu.getInstance().getInitialDeck().unitCounter());
        Printer.print("Special Cards Count: " + StartMenu.getInstance().getInitialDeck().specialCounter());
        Printer.print("Hero Cards Count: " + StartMenu.getInstance().getInitialDeck().heroCounter());
        Printer.print("Current Deck Cards Ability: ");
        for (int i = 0; i < StartMenu.getInstance().getInitialDeck().size(); i++) {
            Printer.print((i + 1) + ". " + StartMenu.getInstance().getInitialDeck().cardAt(i).getName() + " ---> " +
                    StartMenu.getInstance().getInitialDeck().cardAt(i).getAbility());
        }
    }

    public static void showCommanders() {
        if (!getInstance().isHasFaction()) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return;
        }
        int indexOfCommander = 1;
        for (CommanderInformation commanderInfo : CommanderInformation.values())
            if (commanderInfo.faction() == getInstance().getUserFaction())
                Printer.print(indexOfCommander++ + ". " + commanderInfo.name() + " - " + commanderInfo.faction());

    }
    //TODO : we need to complete file part of this menu.
    public static void selectCommander(int leaderNumber) {
        int counter = 1;
        if (!getInstance().isHasFaction()) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return;
        }
        for (CommanderInformation commanderInfo : CommanderInformation.values()) {
            if (commanderInfo.faction() == getInstance().getUserFaction()) {
                if (counter == leaderNumber) {
                    StartMenu.getInstance().setCommanderUser(new Commander(commanderInfo));
                    Printer.print(MenuMessage.COMMANDER_SELECTED.message());
                    return;
                }
                counter++;
            }
        }
        Printer.print(MenuMessage.INVALID_COMMANDER_INDEX.message());
    }
    //Checking for validation of card name from user input.
    public static int nameValidation(String cardName) {
        if (UnitInformation.getUnitInformationByName(cardName) == null) {
            if (SpecialInformation.getSpecialInformationByName(cardName) == null) {
                return 0;
            } else return 2;
        }
        return 1;
        /*
        0 for state which there is no card with this name.
        1 for when there is unit card with this name.
        2 for when there is special card with this name.

    }

    public static boolean deleteFromDeck(String cardName, String numberStr) {
        if (!getInstance().isHasFaction()) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return false;
        }
        StartMenu currentStartMenu = StartMenu.getInstance();
        int state = nameAndCountValidation(cardName, numberStr);
        if (state == 0) return false;
        int count = Integer.parseInt(numberStr);
        if (count > currentStartMenu.getInitialDeck().specifiedCardCounter(cardName)) {
            Printer.print(MenuMessage.YOU_HAVE_LESS.message());
            return false;
        }
        currentStartMenu.getInitialDeck().deleteCard(cardName, count);
        Printer.print(MenuMessage.CARD_DELETE.message());
        return true;
    }
    public static boolean playerBuildingValidation(StartMenu currentStartMenu) {
        if (!currentStartMenu.getInitialDeck().checkDeckValidation()) {
            Printer.print(MenuMessage.CARDS_QUANTITY_VALIDATION.message());
            return true;
        }
        if (!currentStartMenu.hasFaction) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_FACTION.message());
            return true;
        }
        if (currentStartMenu.getCommanderUser() == null) {
            Printer.print(MenuMessage.YOU_HAVE_NOT_SELECTED_COMMANDER.message());
            return true;
        }
        return false;
    }

    public static boolean changeTurn() {
        StartMenu currentStartMenu = StartMenu.getInstance();
        /*if (currentStartMenu.user2.equals(User.getCurrentUser())) {
            Printer.print(MenuMessage.YOU_CAM_JUST_START.message());
            return false;
        }
        if (playerBuildingValidation(currentStartMenu)) return false;
        Player userPlayer = new Player(currentStartMenu.getUser1(), currentStartMenu.getInitialDeck(), currentStartMenu.getUserFaction(),
                currentStartMenu.getCommanderUser());
        currentStartMenu.setPlayer1(userPlayer);
        currentStartMenu.setUserFaction(null);
        currentStartMenu.setHasFaction(false);
        currentStartMenu.setInitialDeck(new Deck());
        User.setCurrentUser(currentStartMenu.getUser2());
        return true;
    }
    
    public static boolean startGame() {
        StartMenu currentStartMenu = StartMenu.getInstance();
        /*if (currentStartMenu.getUser1().equals(User.getCurrentUser())) {
            Printer.print(MenuMessage.YOU_CAN_JUST_CHANGE.message());
            return false;
        }
        if (playerBuildingValidation(currentStartMenu)) return false;
        Player userPlayer = new Player(currentStartMenu.getUser2(), currentStartMenu.getInitialDeck(), currentStartMenu.getUserFaction(),
                currentStartMenu.getCommanderUser());
        currentStartMenu.setPlayer2(userPlayer);
        User.setCurrentUser(currentStartMenu.getUser1());
        currentStartMenu.enterMenu("Game");
        return true;
    }

    /*@Override
    public boolean enterMenu(String name) {
        if (MenuName.getMenu(name) == MenuName.GameMenu) {
            GameMenu.setInstance(this.player1, this.player2);
            TerminalRun.changeCurrentMenu(GameMenu.getInstance());
            Printer.print(MenuMessage.ENTER_GAME_MENU.message());
            return true;
        } else {
            Printer.print(MenuMessage.INVALID_MENU.message());
            return false;
        }
    }

    @Override
    public void exitMenu() {
        TerminalRun.changeCurrentMenu(MainMenu.getInstance());
        Printer.print(MenuMessage.ENTER_MAIN_MENU.message());
    }

    @Override
    public void showMenu() {
        Printer.print(MenuMessage.START_MENU.message());
    }*/

    public User getUser1() {
        return user1;
    }

    public Deck getInitialDeck() {
        return initialDeck;
    }
    
    public void setInitialDeck(Deck deck) {
        this.initialDeck = deck;
    }

    public Faction getUserFaction() {
        return userFaction;
    }

    public void setUserFaction(Faction userFaction) {
        this.userFaction = userFaction;
    }

    public boolean isHasFaction() {
        return hasFaction;
    }

    public void setHasFaction(boolean hasFaction) {
        this.hasFaction = hasFaction;
    }

    public Commander getCommanderUser() {
        return commanderUser;
    }

    public void setCommanderUser(Commander commanderUser) {
        this.commanderUser = commanderUser;
    }

    public User getUser2() {
        return user2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
