package model.card;

public enum CommanderInformation {
    EredinBreaccGlas_BringerOfDeath(Faction.Monsters),
    EredinBreaccGlas_KingOfTheWildHunt(Faction.Monsters),
    EredinBreaccGlas_DestroyerOfWorlds(Faction.Monsters),
    EredinBreaccGlas_CommanderOfTheRedRiders(Faction.Monsters),
    EredinBreaccGlas_TheTreacherous(Faction.Monsters),
    EmhyrVarEmreis_TheWhiteFlame(Faction.NilfgaardianEmpire),
    EmhyrVarEmreis_TheRelentless(Faction.NilfgaardianEmpire),
    EmhyrVarEmreis_EmperorOfNilfgaard(Faction.NilfgaardianEmpire),
    EmhyrVarEmreis_HisImperialMajesty(Faction.NilfgaardianEmpire),
    EmhyrVarEmreis_InvaderOfTheNorth(Faction.NilfgaardianEmpire),
    FrancescaFindabair_QueenOfDolBlathanna(Faction.Scoiatael),
    FrancescaFindabair_TheBeautiful(Faction.Scoiatael),
    FrancescaFindabair_DaisyOfTheValley(Faction.Scoiatael),
    FrancescaFindabair_PureBloodElf(Faction.Scoiatael),
    FrancescaFindabair_HopeOfTheAenSeidhe(Faction.Scoiatael),
    Foltest_TheSiegeMaster(Faction.NorthernRealms),
    Foltest_TheSteelForged(Faction.NorthernRealms),
    Foltest_KingOfTemeria(Faction.NorthernRealms),
    Foltest_LordCommanderOfTheNorth(Faction.NorthernRealms),
    Foltest_SonOfMedell(Faction.NorthernRealms),
    TWO_COMMANDER_SKELLIGE_CrachAnCraite(Faction.Skellige),
    TWO_COMMANDER_SKELLIGE_KingBran(Faction.Skellige);
    private final Faction faction;

    CommanderInformation(Faction faction) {
        this.faction = faction;
    }

    public Faction faction() {
        return this.faction;
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
