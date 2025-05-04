package com.tictactoe.tictactoe.controller;

import com.tictactoe.tictactoe.exception.GameException;
import com.tictactoe.tictactoe.model.Game;
import com.tictactoe.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index() {
        return "game";
    }

    @PostMapping("/game")
    public String createGame(@RequestParam(defaultValue = "3") int size, Model model) {
        try {
            Game game = gameService.createGame(size);
            return "redirect:/game/" + game.getId();
        } catch (GameException e) {
            model.addAttribute("error", e.getMessage());
            return "game";
        }
    }

    @GetMapping("/game/{id}")
    public String getGame(@PathVariable String id, Model model) {
        try {
            Game game = gameService.getGame(id);
            model.addAttribute("game", game);
            return "game";
        } catch (GameException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/";
        }
    }

    @PostMapping("/api/game")
    @ResponseBody
    public ResponseEntity<Game> createGameApi(@RequestParam(defaultValue = "3") int size) {
        try {
            Game game = gameService.createGame(size);
            return ResponseEntity.ok(game);
        } catch (GameException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/api/game/{id}")
    @ResponseBody
    public ResponseEntity<Game> getGameApi(@PathVariable String id) {
        try {
            Game game = gameService.getGame(id);
            return ResponseEntity.ok(game);
        } catch (GameException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/game/{id}/move")
    @ResponseBody
    public ResponseEntity<Game> makeMove(
            @PathVariable String id,
            @RequestParam int row,
            @RequestParam int col) {
        try {
            Game game = gameService.makeMove(id, row, col);
            return ResponseEntity.ok(game);
        } catch (GameException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/api/game/{id}/reset")
    @ResponseBody
    public ResponseEntity<Game> resetGame(@PathVariable String id) {
        try {
            Game game = gameService.resetGame(id);
            return ResponseEntity.ok(game);
        } catch (GameException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(GameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleGameException(GameException e) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", e.getMessage());
        return errorResponse;
    }
}