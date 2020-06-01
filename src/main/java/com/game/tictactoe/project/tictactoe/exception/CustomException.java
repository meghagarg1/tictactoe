package com.game.tictactoe.project.tictactoe.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Throwable {
    public CustomException(HttpStatus status, String s) {
        super(s);
    }
}
