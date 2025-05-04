package com.tictactoe.tictactoe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cell {

    private int row;
    private int col;
    private String value;

    public Cell() {
        this.value = "";
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.value = "";
    }

    public boolean isEmpty() {
        return value == null || value.isEmpty();
    }

    public void mark(String symbol) {
        if (!isEmpty()) {
            throw new IllegalStateException("Cell is already marked");
        }
        this.value = symbol;
    }

    public void clear() {
        this.value = "";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }
}