package model.game;

import model.card.*;

import java.util.ArrayList;
import java.util.Collections;

public class Playground {
    private final ArrayList<ArrayList<Card>> unitCardsGround;
    private final ArrayList<Special> spells;
    private final ArrayList<Special> specials;
    public static final int MAX_ROW_CAPACITY = 6;
    public ArrayList<Boolean> weatherCondition;
    public Playground() {
        unitCardsGround = new ArrayList<>();
        spells = new ArrayList<>();
        specials = new ArrayList<>(Collections.nCopies(6, null));
        weatherCondition = new ArrayList<>(Collections.nCopies(5, false));
        for (int i = 0; i < 6; i++) {
            unitCardsGround.add(new ArrayList<>());
        }
    }

    public void changeTurn() {
        for (int i = 0; i < 3; i++) {
            Collections.swap(unitCardsGround, i, 5 - i);
            Collections.swap(specials, i , 5 - i);
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
        if (row > 2 && !card.getAbility().equalsIgnoreCase(Ability.Spy.name()))
            return 0;
        //for spell selection
        if ((row == -1 &&
                (card.getType() == Type.Weather || card.getName().equalsIgnoreCase(SpecialInformation.Scorch.name()))))
            return 1;
        //for spy
        if(card.getAbility().equalsIgnoreCase(Ability.Spy.name()) && row < 6) return 2;
        //for valid combat range selection
        if ((row == 0 && card.getType() == Type.CloseCombat) || (row == 1 && card.getType() == Type.RangedCombat) ||
                (row == 2 && card.getType() == Type.Siege) || (card.getType() == Type.Agile && (row == 0 || row == 1)))
            return 3;
        //for decoy
        if (card.getName().equalsIgnoreCase(SpecialInformation.Decoy.name()) && index >= 0 &&
                index < unitCardsGround.get(row).size() && row < 3) return 4;
        //for specials in row
        if ((card.getName().equalsIgnoreCase(SpecialInformation.CommandersHorn.name()) ||
                card.getName().equalsIgnoreCase(SpecialInformation.Mardoeme.name())) && specials.get(row) == null)
            return 5;
        //completely invalid number
        if (row > 5 || row < 0) return 6;
        //default invalid code number
        return 7;
    }

    public void addUnit(Card card, int rowNumber) {
        unitCardsGround.get(rowNumber).add(Unit.getInstanceByName(card.getName()));
    }
    //place none spy cards.
    public void placeNoneSpyUnit(Card card, int rowNumber, Player currentPlayer, Player opponent) {
        addUnit(card, rowNumber);
        if (card.getAbility().equalsIgnoreCase(Ability.MoralBoost.name())) {
            for (Card unitCard : unitCardsGround.get(rowNumber))
                if (!unitCard.getName().equalsIgnoreCase(card.getName()))
                    unitCard.increasePower(1);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Mardroeme.name())) {
            mardoemeAbility(rowNumber);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.Muster.name())) {
            //I won't handle that part that if we play all muster cards of our deck and hand , check for if row 9 card capacity filled or not.
            ArrayList<Unit> allMusterCardsInHandAndDeck = currentPlayer.allMusters();
            if (card.getName().equalsIgnoreCase(UnitInformation.Cerys.name())) {
                ArrayList<Unit> allMaindens = currentPlayer.allMaidenShields();
                for (Unit unit : allMaindens)
                    placeNoneSpyUnit(unit, rowNumber, currentPlayer, opponent);
            }
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
            scorchAbility(currentPlayer, opponent, card);
            return;
        }
        if (card.getAbility().equalsIgnoreCase(Ability.CommandersHorn.name()))
            commandersHornAbility(rowNumber);
    }

    public void placeSpyUnitCard(int row, Card card, Player currentPlayer) {
        Unit unit = Unit.getInstanceByName(card.getName());
        assert unit != null;
        addUnit(unit, row);
        currentPlayer.spyAbility();
    }

    public void placeSpecialCard(int row, Card card) {
        specials.set(row, (Special) card);
        if (card.getName().equalsIgnoreCase(SpecialInformation.CommandersHorn.name()))
            commandersHornAbility(row);
        else if (card.getName().equalsIgnoreCase(SpecialInformation.Mardoeme.name()))
            mardoemeAbility(row);
    }

    public void placeSpellCard(Card card, Player player1, Player player2) {
        spells.add((Special) card);
        if (card.getName().equalsIgnoreCase(SpecialInformation.ClearWeather.name()))
            clearWeatherAbility();
        else if (card.getName().equalsIgnoreCase(SpecialInformation.BitingFrost.name()))
            bitingFrostAbility();
        else if (card.getName().equalsIgnoreCase(SpecialInformation.ImpenetrableFog.name()))
            impenetrableFogAbility();
        else if (card.getName().equalsIgnoreCase(SpecialInformation.TorrentialRain.name()))
            torrentialRainAbility();
        else if (card.getName().equalsIgnoreCase(SpecialInformation.SkelligeStorm.name()))
            skelligeStormAbility();
        else if (card.getName().equalsIgnoreCase(SpecialInformation.Scorch.name()))
            for (int i = 0; i < 6; i++)
                removeCardsWithMaxPowerScorch(i, player1, player2);
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

    public void decoyAbility(int row, int index, Player currentPlayer) {
        Card card = unitCardsGround.get(row).get(index);
        currentPlayer.getHand().add(card);
        currentPlayer.getDiscardPiles().add(Special.getInstanceByName(SpecialInformation.Decoy.name()));
        unitCardsGround.get(row).remove(card);
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

    public void scorchAbility(Player player1, Player player2, Card card) {
        if (card.getName().equalsIgnoreCase(UnitInformation.Villentretenmerth.name()))
            removeCardsWithMaxPowerScorch(5, player1, player2);
        else if (card.getName().equalsIgnoreCase(UnitInformation.Toad.name()))
            removeCardsWithMaxPowerScorch(4, player1, player2);
        else if (card.getName().equalsIgnoreCase(UnitInformation.Schirru.name())) 
            removeCardsWithMaxPowerScorch(3, player1, player2);
        else if (card.getName().equalsIgnoreCase(UnitInformation.ClanDimunPirate.name()))
            for (int i = 3; i < 6; i++)
                removeCardsWithMaxPowerScorch(i, player1, player2);
    }

    public void commandersHornAbility(int row) {
        ArrayList<Card> unitCardRow = unitCardsGround.get(row);
        for (Card card : unitCardRow)
            card.setPower(card.getPower() * 2);
    }

    public void mardoemeAbility(int row) {
        replaceCards(new Unit(UnitInformation.Berserker), new Unit(UnitInformation.Vidkaarl), row);
        replaceCards(new Unit(UnitInformation.YoungBerserker), new Unit(UnitInformation.YoungVidkaarl), row);
    }

    public void clearWeatherAbility() {
        weatherCondition.set(0, true);
        if (weatherCondition.get(1)) {
            deactivationWeather(2);
            deactivationWeather(3);
            weatherCondition.set(1, false);

        }
        if (weatherCondition.get(2)) {
            deactivationWeather(1);
            deactivationWeather(4);
            weatherCondition.set(2, false);
        }
        if (weatherCondition.get(3)) {
            deactivationWeather(2);
            deactivationWeather(3);
            weatherCondition.set(3, false);
        }
        if (weatherCondition.get(4)) {
            deactivationWeather(1);
            deactivationWeather(2);
            deactivationWeather(3);
            deactivationWeather(4);
            weatherCondition.set(4, false);
        }
    }

    public void bitingFrostAbility() {
        activationForWeather(0);
        activationForWeather(5);
        weatherCondition.set(1, true);
    }

    public void impenetrableFogAbility() {
        activationForWeather(1);
        activationForWeather(4);
        weatherCondition.set(2, true);
    }

    public void torrentialRainAbility() {
        activationForWeather(2);
        activationForWeather(3);
        weatherCondition.set(3, true);
    }

    public void skelligeStormAbility() {
        activationForWeather(1);
        activationForWeather(2);
        activationForWeather(3);
        activationForWeather(4);
        weatherCondition.set(4, true);
    }

    public void activationForWeather(int row) {
        ArrayList<Card> unitCardRow = unitCardsGround.get(row);
        for (Card card : unitCardRow)
            card.setPower(1);
    }

    public void deactivationWeather(int row) {
        //I won't handle that part that what would be the power of card after deactivation depend on environment.
        ArrayList<Card> unitCardRow = unitCardsGround.get(row);
        for (Card card : unitCardRow) {
            int power;
            if (card.isSpecial())
                power = (int) ((Special) card).power();
            else power = ((Unit) card).getInformation().power();
            card.setPower(power);
        }
    }
    public boolean suitableToScorch(int row) {
        ArrayList<Card> unitCardsInRow = unitCardsGround.get(row);
        int powerSum = 0;
        for (Card card : unitCardsInRow)
            if (!card.isHero())
                powerSum += card.getPower();
        return powerSum >= 10;
    }

    public boolean suitableToScorchWithHero(int row) {
        ArrayList<Card> unitCardsInRow = unitCardsGround.get(row);
        int powerSum = 0;
        for (Card card : unitCardsInRow)
            powerSum += card.getPower();
        return powerSum >= 10;
    }

    public void removeCardsWithMaxPowerScorch(int row, Player currentPlayer, Player opponent) {
        if (suitableToScorch(row)) {
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

    public void removeCardsWithMaxPowerScorchWithHero(int row, Player currentPlayer, Player opponent) {
        if (suitableToScorchWithHero(row)) {
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

    public void simpleRemoveCardWithMaxPower(int row, Player currentPlayer, Player opponent) {
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

    //methods for leader abilities
    public void setWeatherConditionAt(boolean condition, int place) {
        weatherCondition.set(place, condition);
    }

    public boolean haveCommandersHornAt(int row) {
        for (Card card : unitCardsGround.get(row))
            if (card.getAbility().equalsIgnoreCase(Ability.CommandersHorn.name()))
                return true;
        for (Special special : specials)
            if (special.getAbility().equalsIgnoreCase(Ability.CommandersHorn.name()))
                return true;
        return false;
    }

    public void spyCardBuff() {
        for (ArrayList<Card> row : unitCardsGround)
            for (Card card : row)
                if (card.getAbility().equalsIgnoreCase(Ability.Spy.name()))
                    card.setPower(card.getPower() * 2);
    }
}
