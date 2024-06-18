package model.card;

public enum Faction {
    Monsters,
    NilfgaardianEmpire,
    NorthernRealms,
    Scoiatael,
    Skellige,
    Neutral,
    All;

    @Override
    public String toString() {
        switch (this) {
            case Monsters:
                return "Monsters";
            case NilfgaardianEmpire:
                return "Nilfgaardian Empire";
            case NorthernRealms:
                return "Northern Realms";
            case Scoiatael:
                return "Scoia'tael";
            case Skellige:
                return "Skellige";
            case Neutral:
                return "Neutral";
            default:
                return null;
        }
    }

    public static Faction getFactionByName(String name) {
        for (Faction faction : Faction.values()) {
            if (faction.name().equalsIgnoreCase(name)) {
                if (faction == Faction.All) {
                    return null;
                } else return faction;
            }
        }
        return null;
    }
}
