package view.message;

import model.game.User;
import model.view.Show;

public interface Printer {
    Show show = Show.GRAPHICAL;

    static void print(String text) {
        if (show != Show.GRAPHICAL)
            System.out.println(text);//TODO: handle the bloody message destination.
    }
}
