package com.mygdx.game.model;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.model.item.AbstractItem;

public class Cell {
    private AbstractItem item;
    private final int x;
    private final int y;
    private Rectangle borderCell;
    private final int sizePx = 16;

    public Cell(AbstractItem item, int  x, int y) {
        this.item = item;
        this.x = x;
        this.y = y;
        borderCell = new Rectangle(x*sizePx, y*sizePx, sizePx,sizePx);
    }

    public void changeParams(Bomber bomber) {
        if(item != null && (bomber == null || containsBomber(bomber))) {
            item.changeParams(this, bomber);
        }
    }

    public boolean isAvailable(Bomber bomber) {
        if (item == null) return true;
        if (bomber == null || containsBomber(bomber)) {
            return item.isAvailable();
        }
        return true;
    }

    private boolean contains(float x, float y) {
        return borderCell.x < x && borderCell.x + borderCell.width > x && borderCell.y < y && borderCell.y + borderCell.height > y;
    }

    public boolean containsBomber(Bomber bomber){
        Rectangle bomberRect = bomber.getBoardBomber();
        return contains(bomberRect.x, bomberRect.y) ||
                contains(bomberRect.x, bomberRect.y+bomberRect.getHeight()) ||
                contains(bomberRect.x+bomberRect.getWidth(), bomberRect.y+bomberRect.getHeight()) ||
                contains(bomberRect.x+bomberRect.getWidth(), bomberRect.y);

    }
    public void render(SpriteBatch batch){
        if(item!=null) {
            item.render(batch, x*sizePx, y*sizePx);
        }
    }

    public void setItem(AbstractItem item) {
        this.item = item;
    }

    public boolean itemIsNull() {
        return item == null;
    }

    public AbstractItem getItem() {
        return item;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
