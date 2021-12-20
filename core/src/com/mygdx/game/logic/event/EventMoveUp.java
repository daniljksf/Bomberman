package com.mygdx.game.logic.event;

import com.mygdx.game.model.Bomber;

public class EventMoveUp implements EventCommand {
    private final int x;
    private final int y;
    private final int frameId;

    public EventMoveUp() {
        x = 0;
        y = 1;
        frameId = 3;
    }

    @Override
    public void apply(Bomber bomber) {
        bomber.setFrameY(frameId);
        bomber.move(x, y);
    }
}
