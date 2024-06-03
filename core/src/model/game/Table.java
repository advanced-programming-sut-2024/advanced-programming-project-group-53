package model.game;

import model.card.Card;
import model.card.SpecialInformation;
import model.card.UnitInformation;
import java.util.ArrayList;
import java.util.Collections;
import model.game.User;

public class Table {
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Player winner;
    private Player loser;
    private ArrayList<Round> rounds;
    private GameInformation gameInformation;
    private Playground playGround;

    public Table(Player player1, Player player2) {
        this.players = new ArrayList<>();
        this.players.add(player1);
        this.players.add(player2);
        this.rounds = new ArrayList<>();
        this.currentPlayer = player1;
    }

    public void changeTurn() {
        //TODO : I made this field as simple as possible but don't remember to complete it .
        // probably I will use it in controller
        Collections.swap(players, 0, 1);
        currentPlayer = players.get(0);
    }

    public GameInformation saveGame() {
        //for end of the game. probably with Gson and saving with random naming .
        //TODO : specify attributes that are needed for save a game correctly .
        this.gameInformation = new GameInformation(winner, loser, this.rounds);
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

    public void newRound(Round round) {
        // TODO : make this and round better for store with Gson and preventing circular references.
        this.rounds.add(new Round(this.currentPlayer.getUser(), this.players.get(0).getPoint(),
                this.players.get(1).getPoint()));
    }

    public Playground getPlayGround() {
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

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }
}
