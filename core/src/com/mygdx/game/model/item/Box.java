package com.mygdx.game.model.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Box implements AbstractItem {
    private Texture texture;
    private Map<Integer, AbstractItem> mapItems = new HashMap<>();

    public Box() {
        mapItems.put(Items.SWORD.getId(), new Sword());
        mapItems.put(Items.BOOTS.getId(), new Boots());
        mapItems.put(Items.SHIELD.getId(), new Shield());
        texture = new Texture("box.png");
    }

    @Override
    public void changeParams(Cell cell, Bomber bomber) {
        if (bomber == null) {
            Random rn = new Random();
            cell.setItem(mapItems.get(rn.nextInt(mapItems.size())));
            System.out.println("BOX ACTIVATE");
        }
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(texture, x, y);
    }
}
