package model.card;

public class Special extends Card {
    private final SpecialInformation specialInformation;

    public Special(SpecialInformation specialInformation) {
        this.specialInformation = specialInformation;
        super.setPower(0);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getName() {
        return this.specialInformation.name();
    }

    @Override
    public Type getType() {
        return specialInformation.type();
    }

    @Override
    public Faction getFaction() {
        return specialInformation.faction();
    }

    @Override
    public boolean isHero() {
        return false;
    }

    @Override
    public String getAbility() {
        return specialInformation.ability().name();
    }

    @Override
    public String address() {
        return specialInformation.address();
    }

    public static Special getInstanceByName(String cardName) {
        for (SpecialInformation specialInfo : SpecialInformation.values())
            if (specialInfo.name().equalsIgnoreCase(cardName))
                return new Special(specialInfo);
        return null;
    }
}
