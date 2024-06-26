package model.game;

import model.card.Card;
import model.card.Commander;
import model.card.Faction;
import model.cards.AllCard;
import model.cards.Deck;
import model.cards.DiscardPile;
import model.cards.Hand;

import java.util.Collections;

public class Player {
    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final DiscardPile discardPile;
    private final Commander commander;
    private final Faction faction;
    private int point;
    private int hp = 2;
    //remember to check if commander card is needed or not
    public Player(User user, Deck deck, Faction faction, Commander commander) {
        //TODO: check allCard configuration and application.
        this.user = user;
        this.deck = deck;
        this.discardPile = new DiscardPile();
        this.commander = commander;
        this.faction = faction;
        this.hand = new Hand();
    }

    public void initialHandSelection() {
        this.hand.setCards(this.deck.shuffleAndSelect());
    }

    public void vetoPlayer(String cardName) {
        this.discardPile.add(this.hand.getCard(cardName));
        this.hand.removeCard(cardName);
        deck.shuffle();
        String name = deck.cardAt(0).getName();
        hand.add(deck.cardAt(0));
        deck.removeCard(name);
    }

    public Card getCardFromDeck(int cardNumber) {
        return this.deck.cardAt(cardNumber);
    }

    public Card getCardFromHand(int cardNumber) {
        return this.hand.cardAt(cardNumber);
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


    public Faction getFaction() {
        return faction;
    }

    public Commander getCommander() {
        return commander;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
