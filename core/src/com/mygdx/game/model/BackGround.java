package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BackGround {
    private Texture bg;
    private Vector2 position;

    public BackGround(){
        bg = new Texture("Back.png");
        position = new Vector2(0, 0);
    }

    public void render(SpriteBatch batch){
        batch.draw(bg, position.x, position.y);
    }
}
