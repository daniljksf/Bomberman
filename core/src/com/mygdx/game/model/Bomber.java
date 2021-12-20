package com.mygdx.game.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Animator;
import com.mygdx.game.logic.BombService;
import com.mygdx.game.logic.event.EventCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Bomber {
    //это для преобрзования координат в [][], соотвествует размеру квадратика одной клетки
    private int sizePx = 16;
    //позиция бомбера на карте для отрисовки
    private Vector2 pos;
    //класс ответственный за анимацию движения
    private Animator bomberAnimation;
    //спрайт бомбера
    private Texture textureBomber;
    private Texture textureDead;
    //границы бомбера
    private Rectangle borderBomber;

    //скорость передвижения
    private float speed              = 0.5f;
    private final float maxSpeed     = 0.8f;
    private float speedCycleAnimation = 12;
    private float speedAnimation     = 0.3f;
    //радиус взрыва
    private int radius               = 1;
    //бессмертие
    private long timeGetImmortal;
    private final long timeActiveImmortal  = 10;
    private boolean immortal         = false;
    private boolean live             = true;
    //максимальное количество бомб
    private int maxCountBombs        = 1;
    //текущее количество бомб
    private int curCountBombs        = 0;

    //карта
    private final Board board;

    //сервис ответственный за взрыв бомбы
    private final BombService bombService;

    private long reloadingSetBombTime = 2;
    private List<Long> createdBombTime = new ArrayList<>();

    public Bomber(int x, int y, Board board, BombService bombService, String pathToTextureBomber, String pathToTextureDead) {
        this.board = board;
        this.bombService = bombService;
        bombService.addBomber(this);

        pos = new Vector2(sizePx * x, sizePx * y);
        textureBomber = new Texture(pathToTextureBomber);
        textureDead = new Texture(pathToTextureDead);

        bomberAnimation = new Animator(new TextureRegion(textureBomber), 4, 6, speedCycleAnimation);
        borderBomber = new Rectangle(pos.x+3, pos.y+1, 10,10);
    }

    public void action(EventCommand event) {
        if (!live) { return; }
        event.apply(this);
    }

    public void setBomb() {
        if (curCountBombs != maxCountBombs) {
            long timeCreate = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
            createdBombTime.add(timeCreate);
            bombService.createBomb(radius, getXCenter(), getYCenter(), timeCreate);
            curCountBombs++;
        }
    }

    public void checkTimers(long currentTime){
        if(curCountBombs > 0){
            Iterator<Long> iterator = createdBombTime.iterator();
            while (iterator.hasNext()){
                if(currentTime - iterator.next() > reloadingSetBombTime){
                    curCountBombs--;
                    iterator.remove();
                }
            }
        }
        if (currentTime - timeGetImmortal >= timeActiveImmortal) {
            immortal = false;
        }
    }

    //отрисовывает бомбера
    public void render(SpriteBatch batch){
        if(!live&&bomberAnimation.getFrameX() < 3) {
            bomberAnimation.update(1);
            batch.draw(bomberAnimation.getFrame(), pos.x, pos.y);
        }
        batch.draw(bomberAnimation.getFrame(), pos.x, pos.y);
    }

    public void addParam(int radius, float speed, boolean immortal) {
        this.radius += radius;
        this.speed = Math.min(speed + this.speed, maxSpeed);
        this.immortal |= immortal;
        timeGetImmortal = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    }

    public Rectangle getBoardBomber() {
        return borderBomber;
    }

    public void move(int x, int y) {
        int xCenter = getXCenter();
        int yCenter = getYCenter();
        if (    board.isAvailableCell(xCenter + x + y, yCenter + y + x, this) &&
                board.isAvailableCell(xCenter + x - y, yCenter + y - x, this) &&
                board.isAvailableCell(xCenter + x, yCenter + y, this)) {
            board.itemActivate(xCenter + x, yCenter + y, this);
            pos.x += x * speed;
            pos.y += y * speed;
            borderBomber.x += x * speed;
            borderBomber.y += y * speed;
            bomberAnimation.update(speedAnimation);
        }
    }

    public Texture getTextureBomber() {
        return textureBomber;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isImmortal() {
        return immortal;
    }

    public void setImmortal(boolean immortal) {
        this.immortal = immortal;
    }

    public int getMaxCountBombs() {
        return maxCountBombs;
    }

    public void setMaxCountBombs(int maxCountBombs) {
        this.maxCountBombs = maxCountBombs;
    }

    public int getCurCountBombs() {
        return curCountBombs;
    }

    public void setCurCountBombs(int curCountBombs) {
        this.curCountBombs = curCountBombs;
    }

    public int getXCenter() {
        Vector2 centerBomber = new Vector2();
        borderBomber.getCenter(centerBomber);
        return (int)centerBomber.x/sizePx;
    }

    public int getYCenter() {
        Vector2 centerBomber = new Vector2();
        borderBomber.getCenter(centerBomber);
        return (int)centerBomber.y/sizePx;
    }

    public void setFrameY(int id) {
        bomberAnimation.setFrameY(id);
    }

    public Vector2 getPosition(){
        return pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bomber bomber = (Bomber) o;
        return Float.compare(bomber.speed, speed) == 0 && radius == bomber.radius && immortal == bomber.immortal && maxCountBombs == bomber.maxCountBombs && curCountBombs == bomber.curCountBombs && Objects.equals(pos, bomber.pos) && Objects.equals(bomberAnimation, bomber.bomberAnimation) && Objects.equals(textureBomber, bomber.textureBomber) && Objects.equals(borderBomber, bomber.borderBomber) && Objects.equals(board, bomber.board) && Objects.equals(bombService, bomber.bombService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, bomberAnimation, textureBomber, borderBomber, speed, radius, immortal, maxCountBombs, curCountBombs, board, bombService);
    }

    public void dead() {
        if (immortal) {
            return;
        }
        bomberAnimation = new Animator(new TextureRegion(textureDead), 1, 5, speedCycleAnimation);
        this.live = false;
    }

    public void updateDead()
    {
        bomberAnimation.update(1);
    }
    public boolean getLive() { return live; }
}
