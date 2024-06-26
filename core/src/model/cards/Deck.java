package model.cards;

public class Deck extends Cards {
    public boolean checkDeckValidation() {
        return super.specialCounter() <= 10 && super.unitCounter() >= 22;
    }
}
