package model.card;

public enum UnitInformation {//TODO: There is a description for these.
    Albrich(Faction.NilfgaardianEmpire, 2, 1, Type.RangedCombat, Ability.None, false),
    Arachas(Faction.Monsters, 4, 3, Type.CloseCombat, Ability.Muster, false),
    ArachasBehemoth(Faction.Monsters, 6, 1, Type.Siege, Ability.Muster, false),
    AssireVarAnahid(Faction.NilfgaardianEmpire, 6, 1, Type.RangedCombat, Ability.None, false),
    Ballista(Faction.NorthernRealms, 6, 2, Type.Siege, Ability.None, false),
    BarclayEls(Faction.Scoiatael, 6, 1, Type.Agile, Ability.None, false),
    Berserker(Faction.Skellige, 4, 1, Type.CloseCombat, Ability.Berserker, false),
    BirnaBran(Faction.Skellige, 2, 1, Type.CloseCombat, Ability.Medic, false),
    BlackInfantryArcher(Faction.NilfgaardianEmpire, 10, 2, Type.RangedCombat, Ability.None, false),
    BlueStripesCommando(Faction.NorthernRealms, 4, 3, Type.CloseCombat, Ability.TightBond, false),
    BlueboyLugos(Faction.Skellige, 6, 1, Type.CloseCombat, Ability.None, false),
    Botchling(Faction.Monsters, 4, 1, Type.CloseCombat, Ability.None, false),
    CahirMawrDyffrynAepCeallach(Faction.NilfgaardianEmpire, 6, 1, Type.CloseCombat, Ability.None, false),
    Catapult(Faction.NorthernRealms, 8, 2, Type.Siege, Ability.TightBond, false),
    CelaenoHarpy(Faction.Monsters, 2, 1, Type.Agile, Ability.None, false),
    Cerys(Faction.Skellige, 10, 1, Type.CloseCombat, Ability.Muster, true),
    CiaranAep(Faction.Scoiatael, 3, 1, Type.Agile, Ability.None, false),
    ClanAnCraite(Faction.Skellige, 6, 3, Type.CloseCombat, Ability.TightBond, false),
    ClanBrokvarArcher(Faction.Skellige, 6, 3, Type.RangedCombat, Ability.None, false),
    ClanDimunPirate(Faction.Skellige, 6, 1, Type.RangedCombat, Ability.Scorch, false),
    ClanDrummondShieldmaiden(Faction.Skellige, 4, 3, Type.CloseCombat, Ability.TightBond, false),
    ClanTordarrochArmorsmith(Faction.Skellige, 4, 1, Type.CloseCombat, Ability.None, false),
    Cockatrice(Faction.Monsters, 2, 1, Type.RangedCombat, Ability.None, false),
    Cow(Faction.All, 0, 1, Type.RangedCombat, Ability.Transformers, false),
    CroneBrewess(Faction.Monsters, 6, 1, Type.CloseCombat, Ability.Muster, false),
    CroneWeavess(Faction.Monsters, 6, 1, Type.CloseCombat, Ability.Muster, false),
    CroneWhispess(Faction.Monsters, 6, 1, Type.CloseCombat, Ability.Muster, false),
    Cynthia(Faction.NilfgaardianEmpire, 4, 1, Type.RangedCombat, Ability.None, false),
    Dandelion(Faction.All, 2, 1, Type.CloseCombat, Ability.CommandersHorn, false),
    DennisCranmer(Faction.Scoiatael, 6, 1, Type.CloseCombat, Ability.None, false),
    Dethmold(Faction.NorthernRealms, 6, 1, Type.RangedCombat, Ability.None, false),
    DolBlathannaArcher(Faction.Scoiatael, 4, 1, Type.RangedCombat, Ability.None, false),
    DolBlathannaScout(Faction.Scoiatael, 6, 3, Type.Agile, Ability.None, false),
    DonarAnHindar(Faction.Skellige, 4, 1, Type.CloseCombat, Ability.None, false),
    DragonHunter(Faction.NorthernRealms, 5, 3, Type.RangedCombat, Ability.TightBond, false),
    DraigBonDhu(Faction.Skellige, 2, 1, Type.Siege, Ability.CommandersHorn, false),
    Draug(Faction.Monsters, 10, 1, Type.CloseCombat, Ability.None, true),
    DunBannerMedic(Faction.NorthernRealms, 5, 1, Type.Siege, Ability.Medic, false),
    DwarvenSkirmisher(Faction.Scoiatael, 3, 3, Type.CloseCombat, Ability.Muster, false),
    EarthElemental(Faction.Monsters, 6, 1, Type.Siege, Ability.None, false),
    Eithne(Faction.Scoiatael, 10, 1, Type.RangedCombat, Ability.None, true),
    ElvenSkirmisher(Faction.Scoiatael, 2, 3, Type.RangedCombat, Ability.Muster, false),
    EmielRegis(Faction.All, 5, 1, Type.CloseCombat, Ability.None, false),
    Endrega(Faction.Monsters, 2, 1, Type.RangedCombat, Ability.None, false),
    Ermion(Faction.Skellige, 8, 1, Type.RangedCombat, Ability.Mardroeme, true),
    EsteradThyssen(Faction.NorthernRealms, 10, 1, Type.CloseCombat, Ability.None, true),
    EtolianAuxiliaryArchers(Faction.NilfgaardianEmpire, 1, 2, Type.RangedCombat, Ability.Medic, false),
    Fiend(Faction.Monsters, 6, 1, Type.CloseCombat, Ability.None, false),
    Filavandrel(Faction.Scoiatael, 6, 1, Type.Agile, Ability.None, false),
    FireElemental(Faction.Monsters, 6, 1, Type.Siege, Ability.None, false),
    Foglet(Faction.Monsters, 2, 1, Type.CloseCombat, Ability.None, false),
    Forktail(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    Frightener(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    FringillaVigo(Faction.NilfgaardianEmpire, 6, 1, Type.RangedCombat, Ability.None, false),
    Gargoyle(Faction.Monsters, 2, 1, Type.RangedCombat, Ability.None, false),
    GaunterODimm(Faction.All, 2, 1, Type.Siege, Ability.Muster, false),
    GaunterODimmDarkness(Faction.All, 4, 3, Type.RangedCombat, Ability.Muster, false),
    GeraltOfRivia(Faction.All, 15, 1, Type.CloseCombat, Ability.None, true),
    Ghoul(Faction.Monsters, 2, 1, Type.CloseCombat, Ability.Muster, false),
    GraveHag(Faction.Monsters, 5, 1, Type.RangedCombat, Ability.None, false),
    Griffin(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    Harpy(Faction.Monsters, 2, 1, Type.Agile, Ability.None, false),
    HavekarHealer(Faction.Scoiatael, 0, 3, Type.RangedCombat, Ability.Medic, false),
    HavekarSmuggler(Faction.Scoiatael, 5, 3, Type.CloseCombat, Ability.Muster, false),
    HeavyZerrikanianFireScorpion(Faction.NilfgaardianEmpire, 10, 1, Type.Siege, Ability.None, false),
    Hjalmar(Faction.Skellige, 10, 1, Type.RangedCombat, Ability.None, true),
    HolgerBlackhand(Faction.Skellige, 4, 1, Type.Siege, Ability.None, false),
    IceGiant(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    IdaEmeanAep(Faction.Scoiatael, 6, 1, Type.RangedCombat, Ability.None, false),
    Imlerith(Faction.Monsters, 10, 1, Type.CloseCombat, Ability.None, true),
    ImperaBrigadeGuard(Faction.NilfgaardianEmpire, 3, 4, Type.CloseCombat, Ability.TightBond, false),
    Iorveth(Faction.Scoiatael, 10, 1, Type.RangedCombat, Ability.None, true),
    IsengrimFaoiltiarna(Faction.Scoiatael, 10, 1, Type.CloseCombat, Ability.MoralBoost, false),
    JohnNatalis(Faction.NorthernRealms, 10, 1, Type.CloseCombat, Ability.None, true),
    KaedweniSiegeExpert(Faction.NorthernRealms, 1, 3, Type.Siege, Ability.MoralBoost, false),
    Kambi(Faction.Skellige, 11, 1, Type.CloseCombat, Ability.Transformers, true),
    Kayran(Faction.Monsters, 8, 1, Type.Agile, Ability.MoralBoost, true),
    KeiraMetz(Faction.NorthernRealms, 5, 1, Type.RangedCombat, Ability.None, false),
    Leshen(Faction.Monsters, 10, 1, Type.CloseCombat, Ability.None, true),
    LethoOfGulet(Faction.NilfgaardianEmpire, 10, 1, Type.CloseCombat, Ability.None, true),
    LightLongship(Faction.Skellige, 4, 3, Type.RangedCombat, Ability.Muster, false),
    MadmanLugos(Faction.Skellige, 6, 1, Type.CloseCombat, Ability.None, false),
    MahakamanDefender(Faction.Scoiatael, 5, 5, Type.CloseCombat, Ability.None, false),
    MennoCoehoorn(Faction.NilfgaardianEmpire, 10, 1, Type.CloseCombat, Ability.Medic, true),
    Milva(Faction.Scoiatael, 10, 1, Type.RangedCombat, Ability.MoralBoost, false),
    Morteisen(Faction.NilfgaardianEmpire, 3, 1, Type.CloseCombat, Ability.None, false),
    MorvranVoorhis(Faction.NilfgaardianEmpire, 10, 1, Type.Siege, Ability.None, true),
    MysteriousElf(Faction.All, 0, 1, Type.CloseCombat, Ability.Spy, true),
    NausicaaCavalryRider(Faction.NilfgaardianEmpire, 2, 3, Type.CloseCombat, Ability.TightBond, false),
    Nekker(Faction.Monsters, 2, 3, Type.CloseCombat, Ability.Muster, false),
    Olaf(Faction.Skellige, 12, 1, Type.Agile, Ability.MoralBoost, false),
    OlgierdVonEverc(Faction.All, 6, 1, Type.Agile, Ability.MoralBoost, false),
    PhilippaEilhart(Faction.NorthernRealms, 10, 1, Type.RangedCombat, Ability.None, true),
    PlagueMaiden(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    PoorFuckingInfantry(Faction.NorthernRealms, 1, 4, Type.CloseCombat, Ability.TightBond, false),
    PrinceStennis(Faction.NorthernRealms, 5, 1, Type.CloseCombat, Ability.Spy, false),
    Puttkammer(Faction.NilfgaardianEmpire, 3, 1, Type.RangedCombat, Ability.None, false),
    Rainfarn(Faction.NilfgaardianEmpire, 4, 1, Type.CloseCombat, Ability.None, false),
    RedanianFootSoldier(Faction.NorthernRealms, 1, 2, Type.CloseCombat, Ability.None, false),
    RenualdAepMatsen(Faction.NilfgaardianEmpire, 5, 1, Type.RangedCombat, Ability.None, false),
    Riordain(Faction.Scoiatael, 1, 1, Type.RangedCombat, Ability.None, false),
    RottenMangonel(Faction.NilfgaardianEmpire, 3, 1, Type.Siege, Ability.None, false),
    SabrinaGlevissing(Faction.NorthernRealms, 4, 1, Type.RangedCombat, Ability.None, false),
    Schirru(Faction.Scoiatael, 8, 1, Type.Siege, Ability.Scorch, false),
    Seasenthessis(Faction.Scoiatael, 10, 1, Type.RangedCombat, Ability.None, true),
    SheldonSkaggs(Faction.NorthernRealms, 4, 1, Type.RangedCombat, Ability.None, false),
    ShilardFitzOesterlen(Faction.NilfgaardianEmpire, 7, 1, Type.CloseCombat, Ability.Spy, false),
    SiegeEngineer(Faction.NilfgaardianEmpire, 6, 1, Type.Siege, Ability.None, false),
    SiegeTechnician(Faction.NilfgaardianEmpire, 0, 1, Type.Siege, Ability.Medic, false),
    SiegeTower(Faction.NorthernRealms, 6, 1, Type.Siege, Ability.None, false),
    SiegfriedOfDenesle(Faction.NorthernRealms, 5, 1, Type.CloseCombat, Ability.None, false),
    SigismundDijkstra(Faction.NorthernRealms, 4, 1, Type.CloseCombat, Ability.Spy, false),
    SileDeTansarville(Faction.NorthernRealms, 5, 1, Type.RangedCombat, Ability.None, false),
    StefanSkellen(Faction.NilfgaardianEmpire, 9, 1, Type.CloseCombat, Ability.Spy, false),
    Svanrige(Faction.Skellige, 4, 1, Type.CloseCombat, Ability.None, false),
    Sweers(Faction.NilfgaardianEmpire, 2, 1, Type.RangedCombat, Ability.None, false),
    Thaler(Faction.NorthernRealms, 1, 1, Type.Siege, Ability.Spy, false),
    TiborEggebracht(Faction.NilfgaardianEmpire, 10, 1, Type.RangedCombat, Ability.None, true),
    Toad(Faction.Monsters, 7, 1, Type.RangedCombat, Ability.Scorch, false),
    Toruviel(Faction.Scoiatael, 2, 1, Type.RangedCombat, Ability.None, false),
    Trebuchet(Faction.NorthernRealms, 6, 2, Type.Siege, Ability.None, false),
    TrissMerigold(Faction.All, 7, 1, Type.CloseCombat, Ability.None, true),
    Udalryk(Faction.Skellige, 4, 1, Type.CloseCombat, Ability.None, false),
    VampireBruxa(Faction.Monsters, 4, 1, Type.CloseCombat, Ability.Muster, false),
    VampireEkimmara(Faction.Monsters, 4, 1, Type.CloseCombat, Ability.Muster, false),
    VampireFleder(Faction.Monsters, 4, 1, Type.CloseCombat, Ability.Muster, false),
    VampireGarkain(Faction.Monsters, 4, 1, Type.CloseCombat, Ability.Muster, false),
    VampireKatakan(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.Muster, false),
    Vanhemar(Faction.NilfgaardianEmpire, 4, 1, Type.RangedCombat, Ability.None, false),
    VattierDeRideaux(Faction.NilfgaardianEmpire, 4, 1, Type.CloseCombat, Ability.Spy, false),
    VernonRoche(Faction.NorthernRealms, 10, 1, Type.CloseCombat, Ability.None, true),
    Ves(Faction.NorthernRealms, 5, 1, Type.CloseCombat, Ability.None, false),
    Vesemir(Faction.All, 6, 1, Type.CloseCombat, Ability.None, false),
    Vidkaarl(Faction.Skellige, 14, 0, Type.CloseCombat, Ability.MoralBoost, false),
    Villentretenmerth(Faction.All, 7, 1, Type.CloseCombat, Ability.Scorch, false),
    Vreemde(Faction.NilfgaardianEmpire, 2, 1, Type.CloseCombat, Ability.None, false),
    VriheddBrigadeRecruit(Faction.Scoiatael, 4, 1, Type.RangedCombat, Ability.None, false),
    VriheddBrigadeVeteran(Faction.Scoiatael, 5, 2, Type.Agile, Ability.None, false),
    WarLongship(Faction.Skellige, 6, 3, Type.Siege, Ability.TightBond, false),
    Werewolf(Faction.Monsters, 5, 1, Type.CloseCombat, Ability.None, false),
    Wyvern(Faction.Monsters, 2, 1, Type.RangedCombat, Ability.None, false),
    Yaevinn(Faction.Scoiatael, 6, 1, Type.Agile, Ability.None, false),
    YarpenZirgrin(Faction.NorthernRealms, 2, 1, Type.CloseCombat, Ability.None, false),
    YenneferOfVengerberg(Faction.All, 7, 1, Type.RangedCombat, Ability.Medic, true),
    YoungBerserker(Faction.Skellige, 2, 3, Type.RangedCombat, Ability.Berserker, false),
    YoungEmissary(Faction.NilfgaardianEmpire, 5, 2, Type.CloseCombat, Ability.TightBond, false),
    YoungVidkaarl(Faction.Skellige, 8, 0, Type.RangedCombat, Ability.TightBond, false),
    ZerrikanianFireScorpion(Faction.NilfgaardianEmpire, 5, 1, Type.Siege, Ability.None, false),
    ZoltanChivay(Faction.All, 5, 1, Type.CloseCombat, Ability.None, false);
    private final Faction faction;
    private final int power;
    private final int maxNumber;
    private final Type type;
    private final Ability ability;
    private final boolean isHero;

    UnitInformation(Faction faction, int power, int maxNumber, Type type, Ability ability, boolean isHero) {
        this.faction = faction;
        this.power = power;
        this.maxNumber = maxNumber;
        this.type = type;
        this.ability = ability;
        this.isHero = isHero;
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

    public boolean isHero() {
        return isHero;
    }

    public Ability ability() {
        return ability;
    }
}