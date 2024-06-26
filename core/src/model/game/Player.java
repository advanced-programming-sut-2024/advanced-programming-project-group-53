package model.game;

import model.card.*;
import model.cards.Deck;
import model.cards.DiscardPile;
import model.cards.Hand;

import java.util.ArrayList;

public class Player {
    private final User user;
    private final Deck deck;
    private final Hand hand;
    private final DiscardPile discardPiles;
    private final Commander commander;
    private final Faction faction;
    private int point;
    private int hp = 2;
    //remember to check if commander card is needed or not
    public Player(User user, Deck deck, Faction faction, Commander commander) {
        //TODO: check allCard configuration and application.
        this.user = user;
        this.deck = deck;
        this.discardPiles = new DiscardPile();
        this.commander = commander;
        this.faction = faction;
        this.hand = new Hand();
    }

    public void initialHandSelection() {
        this.hand.setCards(this.deck.shuffleAndSelect());
    }

    public void vetoPlayer(String cardName) {
        this.discardPiles.add(this.hand.getCard(cardName));
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

    public DiscardPile getDiscardPiles() {
        return discardPiles;
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

    public ArrayList<Unit> allMusters() {
        ArrayList<Unit> handMusters = hand.allMusters();
        ArrayList<Unit> deckMuster = deck.allMusters();
        ArrayList<Unit> allMusters = new ArrayList<>();
        allMusters.addAll(handMusters);
        allMusters.addAll(deckMuster);
        return allMusters;
    }

    public Card getRandomCardFromDiscardPile() {
        if (discardPiles.size() == 0) return null;
        discardPiles.shuffle();
        Card card = discardPiles.cardAt(0);
        discardPiles.remove(card);
        return card;
    }

    public void medicAbility() {
        Card card = getRandomCardFromDiscardPile();
        if (card == null) return;
        Card cardForHand;
        if (card.isSpecial()) cardForHand = Special.getInstanceByName(card.getName());
        else cardForHand = Unit.getInstanceByName(card.getName());
        hand.add(cardForHand);
    }

    public void addToDiscardPiles(Card card) {
        discardPiles.add(card);
    }
}
