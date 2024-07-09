package model.game;

public class Round {
    //probably these are only information that we need for rounds
    private final String currentPlayerName;
    private double firstPlayerPoint, secondPlayerPoint;
    public Round(User currentPlayer, double firstPlayerPoint, double secondPlayerPoint) {
        this.currentPlayerName = currentPlayer.username();
        this.firstPlayerPoint = firstPlayerPoint;
        this.secondPlayerPoint = secondPlayerPoint;
    }

    public double getFirstPlayerPoint() {
        return firstPlayerPoint;
    }

    public void setFirstPlayerPoint(int firstPlayerPoint) {
        this.firstPlayerPoint = firstPlayerPoint;
    }

    public double getSecondPlayerPoint() {
        return secondPlayerPoint;
    }

    public void setSecondPlayerPoint(int secondPlayerPoint) {
        this.secondPlayerPoint = secondPlayerPoint;
    }

    public String getCurrentPlayer() {
        return currentPlayerName;
    }

    @Override
    public String toString() {
        return "Current Player: " + currentPlayerName + "\n" +
                "First Player Points: " + firstPlayerPoint + "\n" +
                "Second Player Points: " + secondPlayerPoint + "\n";
    }
}
