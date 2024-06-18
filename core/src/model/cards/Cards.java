package model.cards;

import model.card.Card;
import model.card.Unit;

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
    public int size() {
        return this.cards.size();
    }
    public Card cardAt(int index) {
        if (index >= 0 && index < cards.size())
            return cards.get(index);
        return null;
    }
    public ArrayList<Card> getCards() {
        return cards;
    }

    public int unitCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (!card.isSpecial()) counter++;
        }
        return counter;
    }

    public int specialCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (card.isSpecial()) counter++;
        }
        return counter;
    }
    public int heroCounter() {
        int counter = 0;
        for (Card card : cards) {
            if (card.isHero()) counter++;
        }
        return counter;
    }
}
