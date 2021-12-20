package com.mygdx.game.logic.event;

import com.mygdx.game.model.Bomber;

public class EventMoveLeft implements EventCommand {
    private final int x;
    private final int y;
    private final int frameId;

    public EventMoveLeft() {
        x = -1;
        y = 0;
        frameId = 2;
    }

    @Override
    public void apply(Bomber bomber) {
        bomber.setFrameY(frameId);
        bomber.move(x, y);
    }
}
