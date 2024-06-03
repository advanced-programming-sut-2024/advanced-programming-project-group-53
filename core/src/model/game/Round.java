package model.game;

public class Round {
    //probably these are only information that we need for rounds
    private final User currentPlayer;
    private double firstPlayerPoint, secondPlayerPoint;
    public Round(User currentPlayer, double firstPlayerPoint, double secondPlayerPoint) {
        this.currentPlayer = currentPlayer;
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

    public User getCurrentPlayer() {
        return currentPlayer;
    }
}
