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
    private boolean commanderUsed;
    //remember to check if commander card is needed or not
    public Player(User user, Deck deck, Faction faction, Commander commander) {
        //TODO: check allCard configuration and application.
        this.user = user;
        this.deck = deck;
        this.discardPiles = new DiscardPile();
        this.commanderUsed = false;
        this.commander = commander;
        this.faction = faction;
        this.hand = new Hand();
    }

    public void initialHandSelection() {
        this.hand.setCards(this.deck.shuffleAndSelect());
    }

    public void decreaseHP() {
        this.hp--;
    }

    public int hp() {
        return hp;
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
        ArrayList<Unit> deckMusters = deck.allMusters();
        ArrayList<Unit> allMusters = new ArrayList<>();
        for (Unit unit : handMusters)
            hand.removeCard(unit.getName());
        for (Unit unit : deckMusters)
            deck.removeCard(unit.getName());
        allMusters.addAll(handMusters);
        allMusters.addAll(deckMusters);
        return allMusters;
    }

    public ArrayList<Unit> allMaidenShields() {
        ArrayList<Unit> handMaidens = hand.allMaidens();
        ArrayList<Unit> deckMaidens = deck.allMaidens();
        ArrayList<Unit> allMaidens = new ArrayList<>();
        for (Unit unit : handMaidens)
            hand.removeCard(unit.getName());
        for (Unit unit : deckMaidens)
            deck.removeCard(unit.getName());
        allMaidens.addAll(handMaidens);
        allMaidens.addAll(deckMaidens);
        return allMaidens;
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

    public void spyAbility() {
        deck.shuffle();
        Card card1 = deck.cardAt(0), card2 = deck.cardAt(1);
        hand.add(card1);hand.add(card2);
        deck.removeCard(card1.getName());deck.removeCard(card2.getName());
    }

    public boolean isCommanderUsed() {
        return commanderUsed;
    }

    public void setCommanderUsed(boolean commanderUsed) {
        this.commanderUsed = commanderUsed;
    }

    //for leaders abilities
    public void invaderAbility() {
        this.discardPiles.shuffle();
        Card card = discardPiles.cardAt(0);
        this.hand.add(card);
        this.discardPiles.remove(card);
    }

    public void shuffleDiscardPilesAndAdd() {
        ArrayList<Card> cards = discardPiles.getCards();
        discardPiles.shuffle();
        hand.addAll(discardPiles.getCards());
        discardPiles.makeEmpty();
    }
}
