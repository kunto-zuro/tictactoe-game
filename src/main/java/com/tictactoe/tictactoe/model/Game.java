package com.tictactoe.tictactoe.model;


import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import java.time.LocalDateTime;

@Getter
@Setter
public class Game {

    private String id;
    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameStatus status;
    private Player winner;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Game() {
        this.id = UUID.randomUUID().toString();
    }

    public Game(int size) {
        this.id = UUID.randomUUID().toString();
        this.board = new Board(size);
        this.playerX = new Player("X");
        this.playerO = new Player("O");
        this.currentPlayer = playerX; // X always starts
        this.status = GameStatus.IN_PROGRESS;
        this.startTime = LocalDateTime.now();
    }

    public boolean makeMove(int row, int col) {
        if (status != GameStatus.IN_PROGRESS || !board.isCellEmpty(row, col)) {
            return false;
        }

        board.markCell(row, col, currentPlayer.getSymbol());

        if (checkWin(row, col)) {
            status = GameStatus.WINNER;
            winner = currentPlayer;
            endTime = LocalDateTime.now();
        } else if (board.isFull()) {
            status = GameStatus.DRAW;
            endTime = LocalDateTime.now();
        } else {
            currentPlayer = (currentPlayer.equals(playerX)) ? playerO : playerX;
        }

        return true;
    }

    private boolean checkWin(int row, int col) {
        return board.checkRow(row, currentPlayer.getSymbol()) ||
                board.checkColumn(col, currentPlayer.getSymbol()) ||
                board.checkDiagonals(currentPlayer.getSymbol());
    }

    public void reset() {
        board.reset();
        currentPlayer = playerX;
        status = GameStatus.IN_PROGRESS;
        winner = null;
        startTime = LocalDateTime.now();
        endTime = null;
    }

    public String getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Player getWinner() {
        return winner;
    }
}
