package com.tictactoe.tictactoe.model;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class Player {

    private String id;
    private String symbol;

    public Player() {
        this.id = UUID.randomUUID().toString();
    }

    public Player(String symbol) {
        this.id = UUID.randomUUID().toString();
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;
        return symbol.equals(player.symbol);
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }
}