package com.tictactoe.tictactoe.service;

import com.tictactoe.tictactoe.exception.GameException;
import com.tictactoe.tictactoe.model.Game;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {
    private final Map<String, Game> gameRepository = new ConcurrentHashMap<>();

    @Override
    public Game createGame(int size) {
        if (size < 3) {
            throw new GameException("Board size must be at least 3");
        }

        Game game = new Game(size);
        gameRepository.put(game.getId(), game);
        return game;
    }

    @Override
    public Game getGame(String gameId) {
        Game game = gameRepository.get(gameId);
        if (game == null) {
            throw new GameException("Game not found: " + gameId);
        }
        return game;
    }

    @Override
    public Game makeMove(String gameId, int row, int col) {
        Game game = getGame(gameId);
        try {
            boolean moveMade = game.makeMove(row, col);
            if (!moveMade) {
                throw new GameException("Invalid move at position (" + row + ", " + col + ")");
            }
            return game;
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new GameException(e.getMessage());
        }
    }

    @Override
    public Game resetGame(String gameId) {
        Game game = getGame(gameId);
        game.reset();
        return game;
    }
}