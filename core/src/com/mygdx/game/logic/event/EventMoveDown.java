package com.mygdx.game.logic.event;

import com.mygdx.game.model.Bomber;

public class EventMoveDown implements EventCommand {
    private final int x;
    private final int y;
    private final int frameId;

    public EventMoveDown() {
        x = 0;
        y = -1;
        frameId = 0;
    }

    @Override
    public void apply(Bomber bomber) {
        bomber.setFrameY(frameId);
        bomber.move(x, y);
    }
}
