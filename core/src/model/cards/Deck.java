package model.cards;

import model.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Deck extends Cards {
    private boolean hasSelected = false;
    public boolean checkDeckValidation() {
        return super.specialCounter() <= 10 && super.unitCounter() >= 22;
    }

    public ArrayList<Card> shuffleAndSelect() {
        if (hasSelected) return null;
        ArrayList<Card> cards = super.getCards();
        Collections.shuffle(cards);
        ArrayList<Card> cardsForHand = new ArrayList<>();
        Iterator<Card> iterator = cards.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            cardsForHand.add(iterator.next());
            iterator.remove();
            counter++;
            if (counter == 10) break;
        }
        setHasSelected(true);
        return cardsForHand;
    }

    public boolean isHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(boolean hasSelected) {
        this.hasSelected = hasSelected;
    }
}
