package model.game;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class Playground {
    private final ArrayList<ArrayList<Card>> unitCardsGround;
    private final ArrayList<Special> spells;
    private final ArrayList<Special> specials;
    public static final int MAX_ROW_CAPACITY = 6;
    public Playground() {
        unitCardsGround = new ArrayList<>();
        spells = new ArrayList<>();
        specials = new ArrayList<>(Collections.nCopies(6, null));
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

    public int cardRangeChecker(Card card, int row, int index) {
        if (!card.isSpecial() && unitCardsGround.get(row).size() == 9) return -1;
        //for checking using spy and row number validation
        if (row > 3 && !card.getAbility().equalsIgnoreCase(Ability.Spy.name()))
            return 0;
        //for spell selection
        if ((row == -1 &&
                (card.getType() == Type.Weather || card.getName().equalsIgnoreCase(SpecialInformation.Scorch.name()))))
            return 1;
        //for spy
        if(card.getAbility().equalsIgnoreCase(Ability.Spy.name()) && row < 7) return 2;
        //for valid combat range selection
        if ((row == 1 && card.getType() == Type.CloseCombat) || (row == 2 && card.getType() == Type.RangedCombat) ||
                (row == 3 && card.getType() == Type.Siege) || (card.getType() == Type.Agile && (row == 1 || row == 2)))
            return 3;
        //for decoy
        if (card.getName().equalsIgnoreCase(SpecialInformation.Decoy.name()) && index >= 0 &&
                index < unitCardsGround.get(row).size()) return 4;
        //for specials in row
        if (card.getName().equalsIgnoreCase(SpecialInformation.CommandersHorn.name()) ||
                card.getName().equalsIgnoreCase(SpecialInformation.Mardoeme.name())) return 5;
        //completely invalid number
        if (row > 6 || row < 1) return 6;
        //default invalid code number
        return 7;
    }

    public void addNoneSpyUnit(Card card, int rowNumber) {
        unitCardsGround.get(rowNumber).add(Unit.getInstanceByName(card.getName()));
    }
    //place none spy cards.
    public void placeNoneSpyUnit(Card card, int rowNumber, Player currentPlayer, Player opponent) {
        addNoneSpyUnit(card, rowNumber);
        if (card.getAbility().equalsIgnoreCase(Ability.MoralBoost.name())) {
            for (Card unitCard : unitCardsGround.get(rowNumber))
                if (!unitCard.getName().equalsIgnoreCase(card.getName()))
                    unitCard.increasePower(1);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Mardroeme.name())) {
            replaceCards(new Unit(UnitInformation.Berserker), new Unit(UnitInformation.Vidkaarl), rowNumber);
            replaceCards(new Unit(UnitInformation.YoungBerserker), new Unit(UnitInformation.YoungVidkaarl), rowNumber);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Muster.name())) {
            //I won't handle that part that if we play all muster cards of our deck and hand , check for if row 9 card capacity filled or not.
            ArrayList<Unit> allMusterCardsInHandAndDeck = currentPlayer.allMusters();
            for (Unit unit : allMusterCardsInHandAndDeck)
                placeNoneSpyUnit(unit, rowNumber, currentPlayer, opponent);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Medic.name())) {
            currentPlayer.medicAbility();
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.TightBond.name())) {
            tightBondAbility(rowNumber);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Scorch.name())) {
            scorchAbility(currentPlayer, opponent);
        }
    }

    public void replaceCards(Card currentCard, Card replace, int rowNumber) {
        ArrayList<Card> row = unitCardsGround.get(rowNumber);
        ArrayList<Integer> indexes = getCardsIndex(currentCard.getName(), row);
        for (Integer index : indexes)
            row.set(index, Unit.getInstanceByName(replace.getName()));
    }

    public ArrayList<Integer> getCardsIndex(String cardName, ArrayList<Card> cards) {
        int counter = 0;
        ArrayList<Integer> allIndexes = new ArrayList<>();
        for (Card card : cards) {
            if (card.getName().equalsIgnoreCase(cardName))
                allIndexes.add(counter);
            counter++;
        }
        return allIndexes;
    }
    //cards Ability methods
    //TODO : list all needed abilities in there

    public void changeTurn() {
        for (int i = 0; i < 3; i++) {
            Collections.swap(unitCardsGround, i, 5 - i);
            Collections.swap(specials, i , 5 - i);
        }
    }

    public void tightBondAbility(int row) {
        ArrayList<Card> unitCardRow = unitCardsGround.get(row);
        int counter = 0;
        for (Card card : unitCardRow)
            if (card.getAbility().equalsIgnoreCase(Ability.TightBond.name()))
                counter++;
        for (Card card : unitCardRow)
            if (card.getAbility().equalsIgnoreCase(Ability.TightBond.name()))
                card.setPower(card.getPower() * counter);
    }

    public void scorchAbility(Player player1, Player player2) {
        ArrayList<Card> unitCardsInRow;
        for (int i = 0; i < 6; i++)
            if (suitableToScorch(i)) removeCardsWithMaxPower(i, player1, player2);
    }

    public boolean suitableToScorch(int row) {
        ArrayList<Card> unitCardsInRow = unitCardsGround.get(row);
        int powerSum = 0;
        for (Card card : unitCardsInRow)
            if (!card.isHero())
                powerSum += card.getPower();
        return powerSum >= 10;
    }

    public void removeCardsWithMaxPower(int row, Player currentPlayer, Player opponent) {
        ArrayList<Card> unitCardsInRow = unitCardsGround.get(row);
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        Card cardMax = Unit.getInstanceByName(UnitInformation.Cow.name());
        for (Card unitCard : unitCardsInRow) {
            assert cardMax != null;
            if (unitCard.getPower() >= cardMax.getPower()) {
                cardsToRemove.add(unitCard);
                cardMax = unitCard;
            }
        }
        for (Card card : cardsToRemove) {
            if (row > 2) opponent.addToDiscardPiles(card);
            else currentPlayer.addToDiscardPiles(card);
            unitCardsInRow.remove(card);
        }
    }
}
