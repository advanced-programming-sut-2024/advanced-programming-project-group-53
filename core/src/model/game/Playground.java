package model.game;

import model.card.Card;

import java.util.ArrayList;

public class Playground {
    private final ArrayList<ArrayList<Card>> unitCardsGround;
    private final ArrayList<Card> spells;
    public Playground() {
        unitCardsGround = new ArrayList<>();
        spells = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            unitCardsGround.add(new ArrayList<>());
        }
    }

    public boolean rowNumberValidation(int rowNumber) {
        return rowNumber >= 6 || rowNumber < 0;
    }
    public int showRowUnitLength(int rowNumber) {
        if (rowNumberValidation(rowNumber)) {
            return -1;
        }
        return unitCardsGround.get(rowNumber).size();
    }

    public void addCard(Card card, int rowNumber) {
        //TODO : complete that to how to work with place in this method(in rows) .
        if (rowNumberValidation(rowNumber)) {
            //TODO : complete this field .
            return;
        }
        if (unitCardsGround.get(rowNumber).size() >= 9) {
            //TODO : complete this section with view terminal .
            return;
        }
        unitCardsGround.get(rowNumber).add(card);
    }

    public ArrayList<Card> getSpells() {
        return spells;
    }
}
