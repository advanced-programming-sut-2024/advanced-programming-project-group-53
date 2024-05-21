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
    }

    public void deleteFromDeck(Card card) {
    }

}
