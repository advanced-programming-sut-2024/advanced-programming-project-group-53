package model.card;

public class Unit extends Card {
    private final UnitInformation information;

    public Unit(UnitInformation information) {
        this.information = information;
    }

    public UnitInformation getInformation() {
        return information;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
}
