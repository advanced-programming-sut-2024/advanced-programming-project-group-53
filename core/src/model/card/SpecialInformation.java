package model.card;

public enum SpecialInformation {
    BitingFrost(Faction.All,0,3,Type.Weather,Ability.None),
    ClearWeather(Faction.All,0,3,Type.Weather,Ability.None),
    CommandersHorn(Faction.All,0,3,Type.Spell,Ability.None),
    Decoy(Faction.All,0,3,Type.Spell,Ability.None),
    ImpenetrableFog(Faction.All,0,3,Type.Weather, Ability.None),
    Mardoeme(Faction.Skellige,0,3,Type.Spell, Ability.Mardroeme),
    Scorch(Faction.All,0,3,Type.Spell, Ability.None),
    SkelligeStorm(Faction.All,0,3,Type.Weather, Ability.None),
    TorrentialRain(Faction.All,0,3,Type.Weather, Ability.None),;
    private final Faction faction;
    private final int power;
    private final int maxNumber;
    private final Type type;
    private final Ability ability;

    SpecialInformation(Faction faction, int power, int maxNumber, Type type, Ability ability) {
        this.faction = faction;
        this.power = power;
        this.maxNumber = maxNumber;
        this.type = type;
        this.ability = ability;
    }

    public Faction faction() {
        return faction;
    }

    public int power() {
        return power;
    }

    public int maxNumber() {
        return maxNumber;
    }

    public Type type() {
        return type;
    }

    public Ability ability() {
        return ability;
    }
}
