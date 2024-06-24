package model.game;

import model.card.Card;
import model.card.Commander;
import model.card.Faction;
import model.cards.AllCard;
import model.cards.Deck;
import model.cards.DiscardPile;
import model.cards.Hand;

public class Player {
    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Commander commander;
    private final AllCard allCard;
    private final Faction faction;
    private int point;
    //remember to check if commander card is needed or not
    public Player(User user, Deck deck, Faction faction, Commander commander) {
        //TODO: check allCard configuration and application.
        this.user = user;
        this.deck = deck;
        this.discardPile = new DiscardPile();
        this.commander = commander;
        this.faction = faction;
        this.hand = null;
        this.allCard = null;
    }



    public User getUser() {
        return user;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public int getPoint() {
        return point;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public DiscardPile getDiscardPile() {
        return discardPile;
    }

    public AllCard getAllCard() {
        return allCard;
    }

    public Faction getFaction() {
        return faction;
    }

    public Commander getCommander() {
        return commander;
    }
}
