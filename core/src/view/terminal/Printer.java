package view.terminal;

import model.game.User;
import model.view.Show;

public interface Printer {
    Show show = Show.GRAPHICAL;

    static void print(String text) {
        if (show != Show.GRAPHICAL)
            System.out.println(text);//TODO: handle the bloody message destination.
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
        //TODO : fill this with states of short length and no special characters .
    }
}
