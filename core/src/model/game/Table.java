package model.game;

import model.card.Card;
import model.Round;

import java.util.ArrayList;

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
        //TODO : fill this
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
}
