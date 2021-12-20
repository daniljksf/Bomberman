package com.mygdx.game.logic;


import com.mygdx.game.model.item.Bomb;
import com.mygdx.game.model.item.ExplodeWave;
import com.mygdx.game.model.Board;
import com.mygdx.game.model.Bomber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BombService {
    private final List<Bomber> bombers = new ArrayList<>();
    private final List<Bomb> bombs = new ArrayList<>();
    private final List<ExplodeWave> waves = new ArrayList<>();
    private final long timeExplodeBomb = 3;     // sc
    private final long timePropagateWave = 0;   //mlsc
    private final Board board;

    public BombService(Board board) {
        this.board = board;
    }

    public void createBomb(int radius, int x, int y, long timeCreate) {
        Bomb bomb = new Bomb(x, y, radius, timeCreate);
        board.setItem(x,y, bomb);
        bombs.add(bomb);
    }

    public void explode(long currentTime) {
        Iterator<Bomb> itBomb = bombs.iterator();
        while (itBomb.hasNext()) {
            Bomb bomb = itBomb.next();
            if (currentTime - bomb.getTimeCreating() >= timeExplodeBomb) {
                ExplodeWave wave = new ExplodeWave(bomb.getX(), bomb.getY(), bomb.getRadius(), currentTime, board);
                board.setItem(bomb.getX(), bomb.getY(), wave);
                waves.add(wave);
                itBomb.remove();
            }
        }
    }

    /**
     * Распространение волны по вектору
     * @param wave Нужнная волна
     * @param x  - Вектор по X
     * @param y  - Вектор по Y
     */
    private void propagateWave(ExplodeWave wave, int x, int y) {
        for (int i = 1; i <= wave.getRadius(); i++) {
            board.itemActivate(wave.getX() + i * x, wave.getY() + i * y, null);
            if (!board.isAvailableCell(wave.getX() + i * x, wave.getY() + i * y, null)) {
                break;
            }
            int finalI = i;
            bombers.stream().filter(bomber -> board.contains(wave.getX() + finalI * x,
                    wave.getY() + finalI * y, bomber)).forEach(Bomber::dead);
        }
    }

    public void explodeWave(long currentTime) {
        Iterator<ExplodeWave> itWave = waves.iterator();
        while (itWave.hasNext()) {
            ExplodeWave wave = itWave.next();

            bombers.stream().filter(x -> board.contains(wave.getX(), wave.getY(), x)).forEach(Bomber::dead);

            propagateWave(wave, 1, 0);
            propagateWave(wave, -1, 0);
            propagateWave(wave, 0, 1);
            propagateWave(wave, 0, -1);

            if (currentTime - wave.getTimeCreating() > timePropagateWave) {
                board.setItem(wave.getX(), wave.getY(), null);
                itWave.remove();
            }
        }
    }

    public void addBomber(Bomber bomber) {
        bombers.add(bomber);
    }
}
