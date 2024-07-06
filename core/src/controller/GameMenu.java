package controller;

import model.card.Card;
import model.card.Commander;
import model.card.Faction;
import model.card.Special;
import model.game.Player;
import model.game.Playground;
import model.game.Table;
import view.Message;

import java.util.ArrayList;

public class GameMenu extends Menu {
    private static GameMenu instance;
    private final Table table;

    private GameMenu(Player player1, Player player2) {
        this.table = new Table(player1, player2);
    }

    public static GameMenu getInstance() {
        return instance;
    }

    public static GameMenu setInstance(Player player1, Player player2) {
        instance = new GameMenu(player1, player2);
        return instance;
    }

    public static boolean vetoCards(int cardNumber) {
        GameMenu game = GameMenu.getInstance();
        Table table = game.getTable();
        Player currentPlayer = table.getPlayers(0);
        if (table.getRoundNumber() != 1) {
            System.out.println(Message.YOU_CANT_VETO.message());
            return false;
        }
        if (cardNumber > currentPlayer.getHand().size() || cardNumber <= 0) {
            System.out.println(Message.INVALID_NUMBER.message());
            return false;
        }
        String cardName = currentPlayer.getHand().cardAt(cardNumber - 1).getName();
        boolean isSpecial = currentPlayer.getHand().cardAt(cardNumber - 1).isSpecial();
        if (currentPlayer.getHand().specifiedCardCounter(cardName) == 1) {
            System.out.println(Message.YOU_JUST_ONLY_HAVE_ONE.message());
            return false;
        }
        currentPlayer.vetoPlayer(cardName);
        System.out.println(Message.VETO_CARD.message());
        return true;
    }

    public static boolean placeCardInRow(int cardNumber, int rowNumber, int index) {
        Table table = getInstance().getTable();
        Player currentPlayer = table.getPlayers(0);
        Card card = currentPlayer.getCardFromHand(cardNumber);
        if (card == null) {
            System.out.println(Message.INVALID_NUMBER.message());
            return false;
        }
        int state = table.getPlayGround().cardRangeChecker(card, rowNumber, index);
        if (state == -1 || state == 0) {
            System.out.println(Message.INVALID_PLAYGROUND_NUMBER.message());
            return false;
        }
        if (state == 4) {
            currentPlayer.getHand().removeCard(card.getName());
            table.getPlayGround().placeNoneSpyUnit(card, rowNumber, table.getPlayers(0), table.getPlayers(1));
            return false;
        }
        if (state == 5) {
            currentPlayer.getHand().removeCard(card.getName());
            return true;
        }
        return false;
    }

    public static boolean commanderExecution() {
        if (getInstance().getTable().getPlayers(0).isCommanderUsed()) {
            System.out.println(Message.YOU_HAVE_COMMANDER.message());
            return false;
        }
        Table table = getInstance().getTable();
        Player player = table.getPlayers(0);
        Commander commander = player.getCommander();
        ArrayList<Card> cardsToWorkWith;
        if (player.getFaction() == Faction.NorthernRealms) {
            cardsToWorkWith = table.executeNorthern(commander);
        } else if (player.getFaction() == Faction.NilfgaardianEmpire) {
            cardsToWorkWith = table.executeNilfgaardian(commander);
        } else if (player.getFaction() == Faction.Monsters) {
            cardsToWorkWith = table.executeMonsters(commander);
        } else if (player.getFaction() == Faction.Scoiatael) {
            cardsToWorkWith = table.executeScoiatael(commander);
        } else if (player.getFaction() == Faction.Skellige) {
            cardsToWorkWith = table.executeSkellige(commander);
        }
        //TODO : do something with cardsToWorkWith with graphical interface.
        return true;
    }
    /*
    public static boolean vetoCards(int cardNumber) {
        GameMenu game = GameMenu.getInstance();
        Table table = game.getTable();
        Player currentPlayer = table.getPlayers(0);
        if (table.getRoundNumber() != 1) {
            Printer.print(MenuMessage.YOU_CANT_VETO.message());
            return false;
        }
        if (cardNumber > currentPlayer.getHand().size() || cardNumber <= 0) {
            Printer.print(MenuMessage.INVALID_NUMBER.message());
            return false;
        }
        String cardName = currentPlayer.getHand().cardAt(cardNumber - 1).getName();
        boolean isSpecial = currentPlayer.getHand().cardAt(cardNumber - 1).isSpecial();
        if (currentPlayer.getHand().specifiedCardCounter(cardName) == 1) {
            Printer.print(MenuMessage.YOU_JUST_ONLY_HAVE_ONE.message());
            return false;
        }
        currentPlayer.vetoPlayer(cardName);
        Printer.print(MenuMessage.VETO_CARD.message());
        return true;
    }

    public static boolean cardsInHand(int position) {
        Player currentPlayer = getInstance().getTable().getPlayers(0);
        if (position == -1) {
            Printer.print(currentPlayer.getHand().toString());
        } else {
            Card card = currentPlayer.getHand().cardAt(position);
            if (card == null) {
                Printer.print(MenuMessage.INVALID_NUMBER.message());
                return false;
            }
            Printer.print(card.toString());
        }
        return true;
    }

    public static void discardPilesShow() {
        Player currentPlayer = getInstance().table.getPlayers(0);
        Player opponentPlayer = getInstance().table.getPlayers(1);
        Printer.print("YOUR DISCARD PILE(S):");
        Printer.print(currentPlayer.getDiscardPiles().toString());
        Printer.print("OPPONENT DISCARD PILE(S):");
        Printer.print(opponentPlayer.getDiscardPiles().toString());
    }

    public static boolean cardsInRow(int row) {
        Table table = getInstance().getTable();
        Playground playground = table.getPlayGround();
        if (playground.rowNumberValidation(row)) {
            Printer.print(MenuMessage.INVALID_NUMBER.message());
            return false;
        }
        ArrayList<Card> unitCardsInRow = playground.getUnitCardsInRow(row);
        Card spellInRow = playground.getSpecialInRow(row);
        Printer.print("UNIT CARDS IN ROW:");
        if (unitCardsInRow.isEmpty())
            Printer.print("There is no unit card.");
        else
            for (Card card : unitCardsInRow)
                Printer.print(card.toString());
        Printer.print("SPECIAL CARD IN ROW:");
        if (spellInRow == null)
            Printer.print("There is no spell in this row.");
        else Printer.print(spellInRow.toString());
        return true;
    }

    public static void spellsInPlay() {
        int counter = 0;
        ArrayList<Special> spells = getInstance().getTable().getPlayGround().getSpells();
        for (Card card : spells)
            Printer.print(++counter + "." + card);
        if (counter == 0) Printer.print("No Special in play.");
    }

    public static boolean placeCardInRow(int cardNumber, int rowNumber, int index) {
        Table table = getInstance().getTable();
        Player currentPlayer = table.getPlayers(0);
        Card card = currentPlayer.getCardFromHand(cardNumber);
        if (card == null) {
            Printer.print(MenuMessage.INVALID_NUMBER.message());
            return false;
        }
        int state = table.getPlayGround().cardRangeChecker(card, rowNumber, index);
        if (state == -1 || state == 0) {
            Printer.print(MenuMessage.INVALID_PLAYGROUND_NUMBER.message());
            return false;
        }
        if (state == 4) {
            currentPlayer.getHand().removeCard(card.getName());
            table.getPlayGround().placeNoneSpyUnit(card, rowNumber, table.getPlayers(0), table.getPlayers(1));
            return false;
        }
        if (state == 5) {
            currentPlayer.getHand().removeCard(card.getName());
            return true;
        }
        return false;
    }
    public static boolean commanderExecution() {
        if (getInstance().getTable().getPlayers(0).isCommanderUsed()) {
            Printer.print(MenuMessage.YOU_USED_YOUR_COMMANDER.message());
            return false;
        }
        Table table = getInstance().getTable();
        Player player = table.getPlayers(0);
        Commander commander = player.getCommander();
        ArrayList<Card> cardsToWorkWith;
        if (player.getFaction() == Faction.NorthernRealms) {
            cardsToWorkWith = table.executeNorthern(commander);
        } else if (player.getFaction() == Faction.NilfgaardianEmpire) {
            cardsToWorkWith = table.executeNilfgaardian(commander);
        } else if (player.getFaction() == Faction.Monsters) {
            cardsToWorkWith = table.executeMonsters(commander);
        } else if (player.getFaction() == Faction.Scoiatael) {
            cardsToWorkWith = table.executeScoiatael(commander);
        } else if (player.getFaction() == Faction.Skellige) {
            cardsToWorkWith = table.executeSkellige(commander);
        }
        //TODO : do something with cardsToWorkWith with graphical interface.
        return true;
    }
     */
    public Table getTable() {
        return table;
    }
}
