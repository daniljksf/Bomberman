package com.mygdx.game.model.item;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public interface AbstractItem {
    void changeParams(Cell cell, Bomber bomber);
    boolean isAvailable();
    void render(SpriteBatch batch, float x, float y);
}
