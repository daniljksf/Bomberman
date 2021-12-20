package com.mygdx.game.model.item;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animator;
import com.mygdx.game.model.Bomber;
import com.mygdx.game.model.Cell;

import java.util.concurrent.TimeUnit;

public class Bomb implements AbstractItem {
    private final int x;
    private final int y;
    private final int radius;
    private float timeAnimation = 2;
    private Texture texture;
    private Animator animator;
    private long timeCreating;
    private long animationTime;

    public Bomb(int x, int y, int radius, long timeCreate) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        texture = new Texture("bomb.png");
        this.timeCreating = timeCreate;
        this.animationTime = timeCreate;
        animator = new Animator(new TextureRegion(texture), 1, 3, timeAnimation);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getTimeCreating() {
        return timeCreating;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void changeParams(Cell cell, Bomber bomber) {}

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void render(SpriteBatch batch, float x, float y) {
        batch.draw(animator.getFrame(), x, y);
        long current = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        if(current - animationTime > 1){
            animationTime = current;
            animator.update(1);
        }
    }
}
