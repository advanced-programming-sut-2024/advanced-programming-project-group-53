package model.cards;

import model.card.Card;

import java.util.ArrayList;

public abstract class Cards {
    private final ArrayList<Card> cards;
    public Cards() {
        cards = new ArrayList<>();
    }
    public void add(Card card) {
        cards.add(card);
    }
    public void remove(Card card) {
        cards.remove(card);
    }
    public ArrayList<Card> getCards() {
        return cards;
    }
}
