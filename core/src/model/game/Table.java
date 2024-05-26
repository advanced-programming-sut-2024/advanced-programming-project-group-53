package model.game;

import model.card.Card;
import model.Game;
import model.GameLog;
import model.Player;

import java.util.ArrayList;

public class Table {
    private final Player player1, player2;
    private Player currentPlayer;
    private Player winner;
    private GameLog gameLog;
    private final Game game;
    private ArrayList<ArrayList<Card>> playGround;
    public Table(Player player1, Player player2, GameLog gameLog) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameLog = gameLog;
        this.game = new Game(player1.getUser(), player2.getUser());
        this.currentPlayer = player1;
    }

    public void changeTurn() {
        //TODO : fill this
    }

    public boolean checkCardPosition(Card card, int position) {
        //TODO : check that card type is suitable for the selected position .
        return false;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayers(int which) {
        if (which == 1) return player1;
        return player2;
    }

    public GameLog getGameLog() {
        return gameLog;
    }

    public void setGameLog(GameLog gameLog) {
        this.gameLog = new GameLog(this.currentPlayer,this.player1.getPoint(),
                this.player2.getPoint());
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
