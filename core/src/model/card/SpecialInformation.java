package model.card;

public enum SpecialInformation {
    BitingFrost(Faction.Neutral, 0, 3, Type.Weather, Ability.None,"card/special/weather_frost.jpg"),
    ClearWeather(Faction.Neutral, 0, 3, Type.Weather, Ability.None,"card/special/weather_clear.jpg"),
    CommandersHorn(Faction.Neutral, 0, 3, Type.Spell, Ability.None,"card/special/special_horn.jpg"),
    Decoy(Faction.Neutral, 0, 3, Type.Spell, Ability.None,"card/special/special_decoy.jpg"),
    ImpenetrableFog(Faction.Neutral, 0, 3, Type.Weather, Ability.None,"card/special/weather_fog.jpg"),
    Mardoeme(Faction.Skellige, 0, 3, Type.Spell, Ability.Mardroeme,"card/special/special_mardroeme.jpg"),
    Scorch(Faction.Neutral, 0, 3, Type.Spell, Ability.None,"card/special/special_scorch.jpg"),
    SkelligeStorm(Faction.Neutral, 0, 3, Type.Weather, Ability.None,"card/special/weather_storm.jpg"),
    TorrentialRain(Faction.Neutral, 0, 3, Type.Weather, Ability.None,"card/special/weather_rain.jpg");
    private final Faction faction;
    private final int power;
    private final int maxNumber;
    private final Type type;
    private final Ability ability;
    private final String address;

    SpecialInformation(Faction faction, int power, int maxNumber, Type type, Ability ability, String address) {
        this.faction = faction;
        this.power = power;
        this.maxNumber = maxNumber;
        this.type = type;
        this.ability = ability;
        this.address = address;
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

    public String address() {
        return address;
    }
}
