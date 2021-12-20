package com.mygdx.game.logic.event;

import com.mygdx.game.model.Bomber;

public class EventSetBomb implements EventCommand {
    public EventSetBomb() {}

    @Override
    public void apply(Bomber bomber) {
        bomber.setBomb();
    }
}
