package controller;

import model.card.*;
import model.game.Player;
import model.game.Table;
import model.view.Message;

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

    public int placeCardInRow(Card card, int rowNumber, int index) {
        Table table = getTable();
        Player currentPlayer = table.getPlayers(0);
        if (card == null) {
            System.out.println(Message.INVALID_NUMBER.message());
            return -2;
        }
        int state = table.getPlayGround().cardRangeChecker(card, rowNumber, index);
        if (state == -1 || state == 0) {
            System.out.println(Message.INVALID_PLAYGROUND_NUMBER.message());
            return state;
        }
        if (state == 3) {
            currentPlayer.getHand().removeCard(card.getName());
            table.getPlayGround().placeNoneSpyUnit(card, rowNumber, table.getPlayers(0), table.getPlayers(1));
            return state;
        }
        if (state == 5) {
            currentPlayer.getHand().removeCard(card.getName());
            table.getPlayGround().placeSpecialCard(rowNumber, card);
            return state;
        }
        return state;
    }

    public static int getCardPositionToPlay(Card card) {
        if (card.getType() == Type.Siege) return 0;
        else if (card.getType() == Type.RangedCombat) return 1;
        else if (card.getType() == Type.CloseCombat) return 2;
        else return -1;
    }
    public ArrayList<Card> commanderExecution() {
        if (getInstance().getTable().getPlayers(0).isCommanderUsed()) {
            System.out.println(Message.YOU_HAVE_COMMANDER.message());
            return null;
        }
        Table table = getInstance().getTable();
        Player player = table.getPlayers(0);
        Commander commander = player.getCommander();
        ArrayList<Card> cardsToWorkWith = null;
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
        return cardsToWorkWith;
    }

    public void changeTurn() {
        table.changeTurn();
    }
    public Table getTable() {
        return table;
    }
}
