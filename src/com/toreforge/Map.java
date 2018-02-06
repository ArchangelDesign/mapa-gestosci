package com.toreforge;

import java.util.HashMap;

public class Map {
    HashMap<Integer, Row> rows = new HashMap<>();

    public Long getValue(int x, int y) {
        return rows.get(y).get(x).getValue();
    }

    public void putValue(int x, int y, Long value) {
        Cell c = new Cell();
        c.setValue(value).setX(x).setY(y);
        if (!rows.containsKey(y)) {
            rows.put(y, new Row());
        }

        rows.get(y).put(x, c);
    }
}
