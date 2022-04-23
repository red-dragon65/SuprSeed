package com.cruntchy.suprseed.Client.Scene1.Data;

public class GameOverData {

    private boolean gameOver = false;
    private boolean restart = false;

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isRestart() {
        return restart;
    }

    public void setRestart(boolean restart) {
        this.restart = restart;
    }
}
