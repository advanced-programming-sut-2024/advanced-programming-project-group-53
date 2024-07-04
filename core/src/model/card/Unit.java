package model.card;

public class Unit extends Card {
    private final UnitInformation information;

    public Unit(UnitInformation information) {
        this.information = information;
        super.setPower(information.power());
    }

    public UnitInformation getInformation() {
        return information;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getName() {
        return this.information.name();
    }

    @Override
    public Type getType() {
        return information.type();
    }

    @Override
    public Faction getFaction() {
        return information.faction();
    }

    @Override
    public boolean isHero() {
        return this.information.isHero();
    }

    @Override
    public String getAbility() {
        return information.ability().name();
    }

    @Override
    public String address() {
        return information.address();
    }

    public static Unit getInstanceByName(String cardName) {
        for (UnitInformation unitInfo : UnitInformation.values())
            if (unitInfo.name().equalsIgnoreCase(cardName))
                return new Unit(unitInfo);
        return null;
    }
}
