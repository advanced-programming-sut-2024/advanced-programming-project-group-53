package model.card;

public enum Faction {
    Monsters("card/faction/monsters.jpg"),
    NilfgaardianEmpire("card/faction/nilfgaard.jpg"),
    NorthernRealms("card/faction/realms.jpg"),
    Scoiatael("card/faction/scoiatael.jpg"),
    Skellige("card/faction/skellige.jpg"),
    Neutral("");
    private final String address;

    Faction(String address) {
        this.address = address;
    }

    public String address() {
        return address;
    }

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
            default:
                return "";
        }
    }

    public static Faction getFactionByName(String name) {
        for (Faction faction : Faction.values())
            if (faction.name().equalsIgnoreCase(name))
                return faction;
        return null;
    }
}
