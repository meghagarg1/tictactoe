package com.game.tictactoe.project.tictactoe.model;

public enum GameStatus {
    RUNNING("Running"),
    X_WON("X_WON"),
    O_WON("O_WON"),
    DRAW("DRAW");

    private final String status;

    GameStatus(String s) {
        status = s;
    }

    public String toString() {
        return this.status;
    }

}
