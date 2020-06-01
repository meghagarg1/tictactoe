package com.game.tictactoe.project.tictactoe.service;

import com.game.tictactoe.project.tictactoe.model.GameModel;
import com.game.tictactoe.project.tictactoe.model.GameStatus;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public GameModel startNewGame(String board) {
        return new GameModel(board, UUID.randomUUID(), GameStatus.RUNNING.toString());
    }

    @Override
    public GameModel makeMoveInGame(UUID gameId, String boardstate) {
        return new GameModel(boardstate, gameId, GameStatus.RUNNING.toString());
    }
}
