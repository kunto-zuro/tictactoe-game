package com.tictactoe.tictactoe.service;

import com.tictactoe.tictactoe.model.Game;

public interface GameService {
    Game createGame(int size);
    Game getGame(String gameId);
    Game makeMove(String gameId, int row, int col);
    Game resetGame(String gameId);
}