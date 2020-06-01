package com.game.tictactoe.project.tictactoe.service;

import com.game.tictactoe.project.tictactoe.model.GameModel;
import net.minidev.json.JSONObject;

import java.util.List;
import java.util.UUID;

public interface GameService {
    GameModel startNewGame(String board);
    GameModel makeMoveInGame(UUID gameId, String boardstate);
}
