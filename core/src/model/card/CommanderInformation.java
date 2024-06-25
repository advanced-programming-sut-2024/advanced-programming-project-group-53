package model.card;

public enum CommanderInformation {
    EredinBreaccGlas_BringerOfDeath(Faction.Monsters, "card/monsters/commander/monsters_eredin_silver.jpg"),
    EredinBreaccGlas_KingOfTheWildHunt(Faction.Monsters, "card/monsters/commander/monsters_eredin_bronze.jpg"),
    EredinBreaccGlas_DestroyerOfWorlds(Faction.Monsters, "card/monsters/commander/monsters_eredin_gold.jpg"),
    EredinBreaccGlas_CommanderOfTheRedRiders(Faction.Monsters, "card/monsters/commander/monsters_eredin_copper.jpg"),
    EredinBreaccGlas_TheTreacherous(Faction.Monsters, "card/monsters/commander/monsters_eredin_the_treacherous.jpg"),
    EmhyrVarEmreis_TheWhiteFlame(Faction.NilfgaardianEmpire, "card/nilfgaard/commander/nilfgaard_emhyr_silver.jpg"),
    EmhyrVarEmreis_TheRelentless(Faction.NilfgaardianEmpire, "card/nilfgaard/commander/nilfgaard_emhyr_gold.jpg"),
    EmhyrVarEmreis_EmperorOfNilfgaard(Faction.NilfgaardianEmpire, "card/nilfgaard/commander/nilfgaard_emhyr_bronze.jpg"),
    EmhyrVarEmreis_HisImperialMajesty(Faction.NilfgaardianEmpire, "card/nilfgaard/commander/nilfgaard_emhyr_copper.jpg"),
    EmhyrVarEmreis_InvaderOfTheNorth(Faction.NilfgaardianEmpire, "card/nilfgaard/commander/nilfgaard_emhyr_invader_of_the_north.jpg"),
    FrancescaFindabair_QueenOfDolBlathanna(Faction.Scoiatael, "card/scoiatael/commander/scoiatael_francesca_silver.jpg"),
    FrancescaFindabair_TheBeautiful(Faction.Scoiatael, "card/scoiatael/commander/scoiatael_francesca_gold.jpg"),
    FrancescaFindabair_DaisyOfTheValley(Faction.Scoiatael, "card/scoiatael/commander/scoiatael_francesca_copper.jpg"),
    FrancescaFindabair_PureBloodElf(Faction.Scoiatael, "card/scoiatael/commander/scoiatael_francesca_bronze.jpg"),
    FrancescaFindabair_HopeOfTheAenSeidhe(Faction.Scoiatael, "card/scoiatael/commander/scoiatael_francesca_hope_of_the_aen_seidhe.jpg"),
    Foltest_TheSiegeMaster(Faction.NorthernRealms, "card/realms/commander/realms_foltest_silver.jpg"),
    Foltest_TheSteelForged(Faction.NorthernRealms, "card/realms/commander/realms_foltest_gold.jpg"),
    Foltest_KingOfTemeria(Faction.NorthernRealms, "card/realms/commander/realms_foltest_copper.jpg"),
    Foltest_LordCommanderOfTheNorth(Faction.NorthernRealms, "card/realms/commander/realms_foltest_bronze.jpg"),
    Foltest_SonOfMedell(Faction.NorthernRealms, "card/realms/commander/realms_foltest_son_of_medell.jpg"),
    TWO_COMMANDER_SKELLIGE_CrachAnCraite(Faction.Skellige, "card/skellige/commander/skellige_crach_an_craite.jpg"),
    TWO_COMMANDER_SKELLIGE_KingBran(Faction.Skellige, "card/skellige/commander/skellige_king_bran.jpg");
    private final Faction faction;
    private final String address;

    CommanderInformation(Faction faction, String address) {
        this.faction = faction;
        this.address = address;
    }

    public Faction faction() {
        return this.faction;
    }

    public String address() {
        return this.address;
    }

    @Override
    public String toString() {
        switch (this) {
            case EredinBreaccGlas_BringerOfDeath:
                return "Eredin Breacc Glas : Bringer Of Death";
            case EredinBreaccGlas_CommanderOfTheRedRiders:
                return "Eredin Breacc Glas : Commander Of Red Riders";
            case EredinBreaccGlas_KingOfTheWildHunt:
                return "Eredin Breacc Glas : King Of The Wild Hunt";
            case EredinBreaccGlas_DestroyerOfWorlds:
                return "Eredin Breacc Glas : Destroyer Of Worlds";
            case EredinBreaccGlas_TheTreacherous:
                return "Eredin Breacc Glas : The Treacherous";
            case EmhyrVarEmreis_TheWhiteFlame:
                return "Emhyr Var Emreis : The White Flame";
            case EmhyrVarEmreis_TheRelentless:
                return "Emhyr Var Emreis : The Relentless";
            case EmhyrVarEmreis_EmperorOfNilfgaard:
                return "Emhyr Var Emreis : Emperor Of Nilfgaard";
            case EmhyrVarEmreis_HisImperialMajesty:
                return "Emhyr Var Emreis : His Imperial Majesty";
            case EmhyrVarEmreis_InvaderOfTheNorth:
                return "Emhyr Var Emreis : Invader Of The North";
            case FrancescaFindabair_QueenOfDolBlathanna:
                return "Francesca Findabair : Queen Of Dol Blathanna";
            case FrancescaFindabair_TheBeautiful:
                return "Francesca Findabair : The Beautiful";
            case FrancescaFindabair_PureBloodElf:
                return "Francesca Findabair : Pure Blood Elf";
            case FrancescaFindabair_DaisyOfTheValley:
                return "Francesca Findabair : Daisy Of The Valley";
            case FrancescaFindabair_HopeOfTheAenSeidhe:
                return "Francesca Findabair : HopeOf the Aen Seidhe";
            case Foltest_TheSiegeMaster:
                return "Foltest : The Siege Master";
            case Foltest_TheSteelForged:
                return "Foltest : The Steel Forged";
            case Foltest_SonOfMedell:
                return "Foltest : Son Of Medell";
            case Foltest_KingOfTemeria:
                return "Foltest : King Of Temeria";
            case Foltest_LordCommanderOfTheNorth:
                return "Foltest : Lord Commander Of North";
            case TWO_COMMANDER_SKELLIGE_KingBran:
                return "King Bran";
            case TWO_COMMANDER_SKELLIGE_CrachAnCraite:
                return "Crach An Craite";
            default:
                return "";
        }
    }
}
