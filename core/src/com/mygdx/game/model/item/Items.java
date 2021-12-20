package com.mygdx.game.model.item;

public enum Items {
    SWORD(0),
    BOOTS(1),
    SHIELD(2),
    ;

    private final int id;

    Items(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
