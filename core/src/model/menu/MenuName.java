package model.menu;

public enum MenuName {
    GameMenu,
    LoginMenu,
    MainMenu,
    ProfileMenu,
    StartMenu;

    public static MenuName getMenu(String menuName) {
        switch (menuName) {
            case "GameMenu":
            case "Game":
                return GameMenu;
            case "LoginMenu":
            case "Login":
                return LoginMenu;
            case "MainMenu":
            case "Main":
                return MainMenu;
            case "ProfileMenu":
            case "Profile":
                return ProfileMenu;
            case "StartMenu":
            case "Start":
                return StartMenu;
            default:
                return null;
        }
    }
}
