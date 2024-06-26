package model.game;

import model.card.Ability;
import model.card.Card;
import model.card.Special;
import model.card.Type;

import java.util.ArrayList;
import java.util.Collections;

public class Playground {
    private final ArrayList<ArrayList<Card>> unitCardsGround;
    private final ArrayList<Special> spells;
    private final ArrayList<Special> specials;
    public static final int MAX_ROW_CAPACITY = 6;
    public Playground() {
        unitCardsGround = new ArrayList<>();
        spells = new ArrayList<>();
        specials = new ArrayList<>(Collections.nCopies(10, null));
        for (int i = 0; i < 6; i++) {
            unitCardsGround.add(new ArrayList<>());
        }
    }

    public boolean rowNumberValidation(int rowNumber) {
        return rowNumber >= MAX_ROW_CAPACITY || rowNumber < 0;
    }
    public int showRowUnitLength(int rowNumber) {
        if (rowNumberValidation(rowNumber))
            return -1;
        return unitCardsGround.get(rowNumber).size();
    }

    public ArrayList<Special> getSpells() {
        return spells;
    }

    public ArrayList<Card> getUnitCardsInRow(int row) {
        if (rowNumberValidation(row)) return null;
        return this.unitCardsGround.get(row);
    }
    public Card getSpecialInRow(int row) {
        if (rowNumberValidation(row)) return null;
        return specials.get(row);
    }

    public ArrayList<Special> getSpecials() {
        return specials;
    }

    public int cardRangeChecker(Card card, int row) {
        //for checking using spy and row number validation
        if (row > 3 && !card.getAbility().equalsIgnoreCase(Ability.Spy.name()))
            return 0;
        //for individual valid combat range selection
        if ((row == -1 && card.getType() == Type.Spell) || (card.getType() == Type.Agile && (row == 1 || row == 2)))
            return 1;
        //for invalid combat range selection
        if ((row == 1 && card.getType() != Type.CloseCombat) || (row == 2 || card.getType() != Type.RangedCombat) ||
                (row == 3 && card.getType() != Type.Siege))
            return 2;
        //completely invalid number
        if (row > 6 || row < 1) return 3;
        //for spy
        if(card.getAbility().equalsIgnoreCase(Ability.Spy.name())) return 4;
        //for validation in general
        return 5;
    }


    public void addCard(Card card, int rowNumber) {
        //TODO : fill this with ds for add and removing cards.
    }
    //cards Ability methods
    //TODO : list all needed abilities in there

    public void changeTurn() {
        for (int i = 0; i < 3; i++) {
            Collections.swap(unitCardsGround, i, 5 - i);
            Collections.swap(specials, i , 5 - i);
        }
    }
}
