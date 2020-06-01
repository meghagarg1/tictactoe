package com.game.tictactoe.project.tictactoe.model;

import java.util.UUID;

public class GameModel {

    private String board;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private UUID uuid;
    private String status;

    public GameModel(String board, UUID uuid, String status) {
        this.board = board;
        this.uuid = uuid;
        this.status = status;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "GameModel{" +
                "board='" + board + '\'' +
                ", uuid=" + uuid +
                ", status='" + status + '\'' +
                '}';
    }
}
