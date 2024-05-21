package Model;

import Model.Card.Card;
import Model.Card.Faction;

import java.util.ArrayList;

public class Player {
    private User user;
    private ArrayList<Card> deck;
    private GameLog gameLog;
    private Faction faction;

    //todo: check if commander card is needed or not
    public void setFaction(Faction faction) {
        this.faction = faction;
        user.setLastFaction(faction);
    }

    public Faction getFaction() {
        return faction;
    }

    public void addToDeck(Card card) {
        //todo : check the size of deck or not?
        deck.add(card);
    }

    public void deleteFromDeck(Card card) {
        //todo: handle the occasion which the given card doesn't exist
        for (Card aCard : deck) {
            if (aCard.equals(card)) {
                deck.remove(aCard);
                break;
            }
        }
    }

}
