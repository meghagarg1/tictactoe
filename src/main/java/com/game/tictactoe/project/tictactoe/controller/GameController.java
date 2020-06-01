package com.game.tictactoe.project.tictactoe.controller;

import com.game.tictactoe.project.tictactoe.exception.CustomException;
import com.game.tictactoe.project.tictactoe.model.GameModel;
import com.game.tictactoe.project.tictactoe.model.GameStatus;
import com.game.tictactoe.project.tictactoe.service.GameService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.certpath.OCSPResponse;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/api/v1/games")
    public ResponseEntity<List<GameModel>> getGames() {
        List<GameModel> entities = new ArrayList<>();
            entities.add(new GameModel("XO--X--OX", UUID.randomUUID(), GameStatus.RUNNING.toString()));
            entities.add(new GameModel("XXX-X--OX", UUID.randomUUID(), GameStatus.X_WON.toString()));
            entities.add(new GameModel("OOO-X--OX", UUID.randomUUID(), GameStatus.O_WON.toString()));
            entities.add(new GameModel("XXXOOO-OX", UUID.randomUUID(), GameStatus.DRAW.toString()));
            entities.add(new GameModel("XX--X--OX", UUID.randomUUID(), GameStatus.RUNNING.toString()));
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping("/api/v1/games")
    public ResponseEntity<GameModel> startNewGame(@RequestBody GameModel board) {
        String boardstate = board.getBoard();
        if(boardstate.length()!=9 || !(boardstate.matches("^[xXoO0-]+$"))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GameModel entities = gameService.startNewGame(boardstate);
        return new ResponseEntity<>(entities, HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/games/")
    public ResponseEntity<GameModel> getAGame(@RequestParam(name = "game_id") String uuid) {
        UUID gameid;
        try {
            gameid = UUID.fromString(uuid);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GameModel entity = new GameModel("XO--X--OX", gameid, GameStatus.DRAW.toString());
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PutMapping("/api/v1/games")
        public ResponseEntity<GameModel> makeAMoveInGame(@RequestParam(name = "game_id", required = true) String uuid, @RequestBody GameModel board) {
            String boardstate = board.getBoard();
            if(boardstate.length()!=9 || !(boardstate.matches("^[xXoO0-]+$"))) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            UUID gameId = null;
            try {
                gameId = UUID.fromString(uuid);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            GameModel entities = gameService.makeMoveInGame(gameId, boardstate);
            return new ResponseEntity<>(entities, HttpStatus.CREATED);
        }

    @DeleteMapping("/api/v1/games")
    public ResponseEntity<String> deleteAGame(@RequestParam(name = "game_id", required = true) String uuid) throws CustomException{
        try {
            UUID gameId = UUID.fromString(uuid);
        }catch(Exception e){
            return new ResponseEntity<>("UUID cannot be parsed from Given String in Game Id.",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Game successfully deleted",HttpStatus.OK);
    }


}
