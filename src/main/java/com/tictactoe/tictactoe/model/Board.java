package com.tictactoe.tictactoe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {

    private int size;
    private Cell[][] cells;

    public Board() {
    }

    public Board(int size) {
        if (size < 3) {
            throw new IllegalArgumentException("Board size must be at least 3");
        }

        this.size = size;
        this.cells = new Cell[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        validatePosition(row, col);
        return cells[row][col].isEmpty();
    }

    public void markCell(int row, int col, String symbol) {
        validatePosition(row, col);
        cells[row][col].mark(symbol);
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Invalid position: (" + row + ", " + col + ")");
        }
    }

    public boolean checkRow(int row, String symbol) {
        for (int j = 0; j < size; j++) {
            if (!symbol.equals(cells[row][j].getValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkColumn(int col, String symbol) {
        for (int i = 0; i < size; i++) {
            if (!symbol.equals(cells[i][col].getValue())) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonals(String symbol) {
        // Check main diagonal (top-left to bottom-right)
        boolean mainDiag = true;
        for (int i = 0; i < size; i++) {
            if (!symbol.equals(cells[i][i].getValue())) {
                mainDiag = false;
                break;
            }
        }

        if (mainDiag) {
            return true;
        }

        for (int i = 0; i < size; i++) {
            if (!symbol.equals(cells[i][size - 1 - i].getValue())) {
                return false;
            }
        }

        return true;
    }

    public void reset() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].clear();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public Cell[][] getCells() {
        return cells;
    }
}