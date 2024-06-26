package model.game;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.game.User;

public class Table {
    private final ArrayList<Player> players;
    private final Player player1, player2;
    private Player currentPlayer;
    private Player winner;
    private Player loser;
    private ArrayList<Round> rounds;
    private GameInformation gameInformation;
    private Playground playGround;
    private int roundNumber;

    public Table(Player player1, Player player2) {
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.player1 = player1;
        this.player2 = player2;
        this.rounds = new ArrayList<>();
        this.currentPlayer = player1;
        this.roundNumber = 1;
        preGameConfigs();
    }

    public void preGameConfigs() {
        if (player1.getFaction() == Faction.Scoiatael)
            if (player2.getFaction() == Faction.Scoiatael) {
                boolean choice = (new Random()).nextBoolean();
                if (choice)
                    changeCurrentPlayer();
            }
        else if (player2.getFaction() == Faction.Scoiatael)
            changeCurrentPlayer();
        handCardRandomSelection();
    }

    public void handCardRandomSelection() {
        players.get(0).initialHandSelection();
        players.get(1).initialHandSelection();
    }
    public void changeCurrentPlayer() {
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public boolean putCard(Card card, int row) {
        //TODO : fill this one.
        ArrayList<Card> unitCardsInRow = playGround.getUnitCardsInRow(row);
        if (unitCardsInRow.size() == 9)
            return false;
        this.playGround.addCardWithoutSpecialDraw(card, row, getPlayers(0));
        return true;
    }

    public void changeTurn() {
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public GameInformation saveGame() {
        //for end of the game. probably with Gson and saving with random naming .
        //TODO : specify attributes that are needed for save a game correctly .
        this.gameInformation = new GameInformation(winner, loser, this.rounds);
        return null;
    }

    public GameInformation getGame() {
        return gameInformation;
    }

    public Player getPlayers(int which) {
        return players.get(which - 1);
    }

    public void newRound(Round round) {
        // TODO : make this and round better for store with Gson and preventing circular references.
        this.rounds.add(new Round(this.currentPlayer.getUser(), this.players.get(0).getPoint(),
                this.players.get(1).getPoint()));
    }

    public Playground getPlayGround() {
        return playGround;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        this.roundNumber = roundNumber;
    }


    //Card Abilities list
}
