package model.game;

import model.card.Card;
import model.card.Faction;

import java.util.ArrayList;

public class Player {
    private User user;
    private ArrayList<Card> deck;
    private Round round;
    private final Faction faction;
    private int point;

    public Player(Faction faction) {
        this.faction = faction;
    }

    //remember to check if commander card is needed or not
    public void setFaction(Faction faction){

    }
    public void addToDeck(Card card) {

    }
    public void deleteFromDeck(Card card) {

    }

    public User getUser() {
        return user;
    }

    public int getPoint() {
        return point;
    }

    public Faction getFaction() {
        return faction;
    }
}
