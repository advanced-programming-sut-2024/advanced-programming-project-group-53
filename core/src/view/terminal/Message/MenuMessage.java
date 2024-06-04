package view.terminal.Message;

public enum MenuMessage {
    INVALID_COMMAND("The command that you have entered is invalid."),
    INVALID_MENU("You can't enter this menu directly."),
    ENTER_LOGIN_MENU("You have entered the login menu."),
    ENTER_REGISTER_MENU("You have entered the register menu."),
    ENTER_MAIN_MENU("You have entered the main menu."),
    ENTER_GAME_MENU("You have entered the game menu."),
    ENTER_PROFILE_MENU("You have entered the profile menu."),
    ENTER_START_MENU("You have entered the start menu."),
    LOGIN_MENU("You are in login menu."),
    MAIN_MENU("You are in main menu."),
    GAME_MENU("You are in game menu."),
    PROFILE_MENU("You are in profile menu."),
    START_MENU("You are in start menu."),;
    private final String message;

    MenuMessage(String message) {
        this.message = message;
    }
    public String message() {
        return message;
    }
}
