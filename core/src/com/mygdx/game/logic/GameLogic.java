package com.mygdx.game.logic;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Bomber;

import java.util.List;

public class GameLogic {
    private final List<Bomber> bombers;

    public GameLogic(List<Bomber> bombers) {
        this.bombers = bombers;
    }

    public void checkEvent() {
        for (Integer item: GDXController.getList1()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(0).action(GDXController.getEventTypeOne(item));
            }
        }
        for (Integer item: GDXController.getList2()) {
            if (Gdx.input.isKeyPressed(item)) {
                bombers.get(1).action(GDXController.getEventTypeTwo(item));
            }
        }
    }

    public void checkTime(long time){
        for(Bomber bomber : bombers){
            bomber.checkTimers(time);
        }
    }

    public void render(SpriteBatch batch){
        for(Bomber bomber : bombers){
            bomber.render(batch);
        }
    }

    public boolean gameEnd() {
        int count = 0;
        for (Bomber bomber: bombers) {
            count += (bomber.getLive())? 1 : 0;
        }
        return count == 1;
    }
}
