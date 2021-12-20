package com.mygdx.game.model.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

public class Shield implements AbstractItem {
    private final Texture texture;

    public Shield() {
        texture = new Texture("Sheild.png");
    }

    @Override
    public void changeParams(Cell cell, Bomber bomber) {
        if (bomber != null) {
            bomber.addParam(0, 0, true);
            cell.setItem(null);
        }
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
