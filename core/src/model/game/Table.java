package model.game;

import model.card.Card;
import model.card.SpecialInformation;
import model.card.UnitInformation;

import javax.swing.plaf.IconUIResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Table {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Player winner;
    private Round round;
    private final GameInformation gameInformation;
    private ArrayList<ArrayList<Card>> playGround;

    public Table(Player player1, Player player2, Round round) {
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.round = round;
        this.gameInformation = new GameInformation(player1, player2);
        this.currentPlayer = player1;
    }

    public void changeTurn() {
        //TODO : I made this field as simple as possible but don't remember to complete it .
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public GameInformation saveGame() {
        //for end of the game.
        //TODO : specify attributes that are needed for save a game correctly .
        return null;
    }
    public boolean checkCardPosition(Card card, int position) {
        //TODO : check that card type is suitable for the selected position .
        return false;
    }

    public GameInformation getGame() {
        return gameInformation;
    }

    public Player getPlayers(int which) {
        return players.get(which - 1);
    }

    public Round getGameLog() {
        return round;
    }

    public void setGameLog(Round round) {
        this.round = new Round(this.currentPlayer.getUser());
    }

    public ArrayList<ArrayList<Card>> getPlayGround() {
        return playGround;
    }

    public ArrayList<Card> getSpells(int which) {
        ArrayList<Card> spells = new ArrayList<>();
        // TODO : iterate and add spells card to the return value of this method.
        return spells;
    }

    public void showAllCardInFaction() {
        //TODO : complete and move print part to view package
        System.out.println("Unit Cards:");
        for (UnitInformation unitInfo: UnitInformation.values()){
            if (unitInfo.faction().equals(currentPlayer.getFaction()))
                System.out.println(unitInfo.name());
        }
        System.out.println("Special Cards:");
        for (SpecialInformation specialInfo: SpecialInformation.values()) {
            if (specialInfo.faction().equals(currentPlayer.getFaction()))
                System.out.println(specialInfo.name());
        }
    }

}
