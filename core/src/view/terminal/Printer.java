package view.terminal;

import model.game.User;

public interface Printer {
    static void print(String text) {
        System.out.println(text);
    }
    //This is for debug part.
    static void temporaryPrinter() {
        System.out.println("This field haven't completed yet!");
    }

    static void printUserInfo() {
        User user = User.getCurrentUser();
        if (user == null) {
            return;
        }
        System.out.println("Username:   " + user.getUsername());
        System.out.println("Nickname:   " + user.getNickname());
        System.out.println("MaxXP:      " + user.getMaxPoint());
        System.out.println("Rank:       " + user.getRank());
        System.out.println("Game Count: " + user.getGameCount());
        System.out.println("Draw Count: " + user.getDrawCount());
        System.out.println("Win Count:  " + user.getWinCount());
        System.out.println("Lose Count: " + user.getLoseCount());
    }

    static void passwordValidation(int state) {

    }
}
