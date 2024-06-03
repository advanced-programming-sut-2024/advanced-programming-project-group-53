package model.game;

import model.card.Card;
import model.card.Faction;
import model.cards.AllCard;
import model.cards.Deck;
import model.cards.DiscardPile;
import model.cards.Hand;

import java.util.ArrayList;

public class Player {
    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final AllCard allCard;
    private final Faction faction;
    private int point;
    //remember to check if commander card is needed or not
    public Player(User user, Deck deck, Hand hand, AllCard allCard, Faction faction) {
        //TODO: check allCard configuration and application.
        this.user = user;
        this.deck = deck;
        this.allCard = allCard;
        this.hand = hand;
        this.discardPile = new DiscardPile();
        this.faction = faction;
    }

    public void addToDeck(Card card) {

    }
    public void deleteFromDeck(Card card) {

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
}
