package com.mygdx.game.logic.event;

import com.mygdx.game.model.Bomber;

public interface EventCommand {
    void apply(Bomber bomber);
}
