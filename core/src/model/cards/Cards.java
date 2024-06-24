package model.cards;

import model.card.Card;
import model.card.Unit;
import model.card.UnitInformation;

import java.util.ArrayList;
import java.util.Iterator;

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

    public int specifiedCardCounter(String cardName) {
        int counter = 0;
        for (Card card : cards)
            if (card.getName().equalsIgnoreCase(cardName))
                counter++;
        return counter;
    }

    public boolean deleteCard(String cardName, int number) {
        int count = 0;
        Iterator<Card> itr = this.cards.iterator();
        while (itr.hasNext()) {
            String name = itr.next().getName();
            if (name.equalsIgnoreCase(cardName)) {
                count++;
                itr.remove();
            }
            if (count == number) return true;
        }
        return false;
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
