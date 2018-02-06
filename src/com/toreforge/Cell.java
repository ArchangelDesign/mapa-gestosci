package com.toreforge;

import java.util.HashMap;

public class Cell {
    private int x;
    private int y;
    private Long value;

    public int getX() {
        return x;
    }

    public Cell setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public Cell setY(int y) {
        this.y = y;
        return this;
    }

    public Long getValue() {
        return value;
    }

    public Cell setValue(Long value) {
        this.value = value;
        return this;
    }
}
