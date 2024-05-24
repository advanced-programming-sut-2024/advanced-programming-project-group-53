package Control.View.Terminal;

import java.util.Scanner;

public interface TerminalRun extends Runnable {
    public void run(Scanner scanner);
}
