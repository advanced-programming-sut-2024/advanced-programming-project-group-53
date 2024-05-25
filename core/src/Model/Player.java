package Model;

import Model.Card.Card;
import Model.Card.Commander;
import Model.Card.Faction;

import java.util.ArrayList;
/*
* I think it is better to remark that User can choose its faction and commander before it become a player.
* So player object will construct exactly after user in game menu choose his/her commander and faction.
 */


public class Player { //this class will only use in a game event
    private final User user;
    private final ArrayList<Card> deck; //should this be final or not?
    private final Faction faction;
    private final Commander commander;
    private GameLog currentGameLog;
    private double point;
    public Player(User user, Faction faction, Commander commander) {
        this.user = user;
        this.deck = new ArrayList<>(); // TODO : use serialization for loading decks from file system .
        this.faction = faction;
        this.commander = commander;
        this.point = 0;
    }

    public void addToDeck(Card card) {
        // TODO : is this method necessary or not?
    }
    public void deleteFromDeck(Card card) {
        // TODO : is this method necessary or not?
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public Faction getFaction() {
        return faction;
    }

    public Commander getCommander() {
        return commander;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }
}
