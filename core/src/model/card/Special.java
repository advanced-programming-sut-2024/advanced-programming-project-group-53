package model.card;

public class Special extends Card {
    private final SpecialInformation specialInformation;

    public Special(SpecialInformation specialInformation) {
        this.specialInformation = specialInformation;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
