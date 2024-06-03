package view.terminal;

import java.util.Scanner;

public interface TerminalRun extends Runnable {
    public void run(Scanner scanner);
}
