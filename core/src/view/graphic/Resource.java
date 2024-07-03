package view.graphic;

public enum Resource {
    LABEL("skin/label/label.json"),
    SCROLL_PANE("skin/scrollPane/scrollPane.json"),
    TEXT_FIELD("skin/textField/textField.json"),
    CHANGE_COMMANDER_ON("button/changeCommander/on.png"),
    CHANGE_COMMANDER_OFF("button/changeCommander/off.png"),
    CHANGE_COMMANDER_CLICKED("button/changeCommander/clicked.png"),
    CHANGE_EMAIL_ON("button/changeEmail/on.png"),
    CHANGE_EMAIL_OFF("button/changeEmail/off.png"),
    CHANGE_EMAIL_CLICKED("button/changeEmail/clicked.png"),
    CHANGE_FACTION_ON("button/changeFaction/on.png"),
    CHANGE_FACTION_OFF("button/changeFaction/off.png"),
    CHANGE_FACTION_CLICKED("button/changeFaction/clicked.png"),
    CHANGE_NICKNAME_ON("button/changeNickname/on.png"),
    CHANGE_NICKNAME_OFF("button/changeNickname/off.png"),
    CHANGE_NICKNAME_CLICKED("button/changeNickname/clicked.png"),
    CHANGE_PASSWORD_ON("button/changePassword/on.png"),
    CHANGE_PASSWORD_OFF("button/changePassword/off.png"),
    CHANGE_PASSWORD_CLICKED("button/changePassword/clicked.png"),
    CHANGE_TURN_ON("button/changeTurn/on.png"),
    CHANGE_TURN_OFF("button/changeTurn/off.png"),
    CHANGE_TURN_CLICKED("button/changeTurn/clicked.png"),
    CHANGE_USERNAME_ON("button/changeUsername/on.png"),
    CHANGE_USERNAME_OFF("button/changeUsername/off.png"),
    CHANGE_USERNAME_CLICKED("button/changeUsername/clicked.png"),
    CHOOSE_DECK_ON("button/chooseDeck/on.png"),
    CHOOSE_DECK_OFF("button/chooseDeck/off.png"),
    CHOOSE_DECK_CLICKED("button/chooseDeck/clicked.png"),
    END_TURN_ON("button/endTurn/on.png"),
    END_TURN_OFF("button/endTurn/off.png"),
    END_TURN_CLICKED("button/endTurn/clicked.png"),
    EXIT_ON("button/exit/on.png"),
    EXIT_OFF("button/exit/off.png"),
    EXIT_CLICKED("button/exit/clicked.png"),
    FORGET_PASSWORD_ON("button/forgetPassword/on.png"),
    FORGET_PASSWORD_OFF("button/forgetPassword/off.png"),
    FORGET_PASSWORD_CLICKED("button/forgetPassword/clicked.png"),
    HISTORY_ON("button/history/on.png"),
    HISTORY_OFF("button/history/off.png"),
    HISTORY_CLICKED("button/history/clicked.png"),
    LOGIN_ON("button/login/on.png"),
    LOGIN_OFF("button/login/off.png"),
    LOGIN_CLICKED("button/login/clicked.png"),
    LOGIN_MENU_ON("button/loginMenu/on.png"),
    LOGIN_MENU_OFF("button/loginMenu/off.png"),
    LOGIN_MENU_CLICKED("button/loginMenu/clicked.png"),
    LOGOUT_ON("button/logout/on.png"),
    LOGOUT_OFF("button/logout/off.png"),
    LOGOUT_CLICKED("button/logout/clicked.png"),
    MAIN_MENU_ON("button/mainMenu/on.png"),
    MAIN_MENU_OFF("button/mainMenu/off.png"),
    MAIN_MENU_CLICKED("button/mainMenu/clicked.png"),
    NORMAL_ON("button/normal/on.png"),
    NORMAL_OFF("button/normal/off.png"),
    NORMAL_CLICKED("button/normal/clicked.png"),
    PROFILE_ON("button/profile/on.png"),
    PROFILE_OFF("button/profile/off.png"),
    PROFILE_CLICKED("button/profile/clicked.png"),
    RANKING_ON("button/ranking/on.png"),
    RANKING_OFF("button/ranking/off.png"),
    RANKING_CLICKED("button/ranking/clicked.png"),
    REGISTER_ON("button/register/on.png"),
    REGISTER_OFF("button/register/off.png"),
    REGISTER_CLICKED("button/register/clicked.png"),
    REGISTER_MENU_ON("button/registerMenu/on.png"),
    REGISTER_MENU_OFF("button/registerMenu/off.png"),
    REGISTER_MENU_CLICKED("button/registerMenu/clicked.png"),
    SAVE_ON("button/save/on.png"),
    SAVE_OFF("button/save/off.png"),
    SAVE_CLICKED("button/save/clicked.png"),
    SPECIFIED_ON("button/specified/on.png"),
    SPECIFIED_OFF("button/specified/off.png"),
    SPECIFIED_CLICKED("button/specified/clicked.png"),
    START_GAME_ON("button/startGame/on.png"),
    START_GAME_OFF("button/startGame/off.png"),
    START_GAME_CLICKED("button/startGame/clicked.png"),
    LOGIN_BACKGROUND("background/login.jpg"),
    REGISTER_BACKGROUND("background/register.jpg"),
    MAIN_BACKGROUND("background/main.jpg"),
    PROFILE_BACKGROUND("background/profile.jpg"),
    HISTORY_BACKGROUND("background/history.jpg"),
    START_BACKGROUND("background/start.jpeg"),
    RANKING_BACKGROUND("background/ranking.jpeg"),
    GAME_BACKGROUND("background/game.png"),
    ;
    private final String address;

    Resource(String address) {
        this.address = address;
    }

    public String address() {
        return address;
    }
}
