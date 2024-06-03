package Model.game;

import Model.game.User;
import Model.card.Card;
import Model.card.Faction;
import Model.game.Round;

import java.util.ArrayList;

public class Player {
    private User user;
    private ArrayList<Card> deck;
    private Round round;
    private Faction faction;
    private int point;
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
}
