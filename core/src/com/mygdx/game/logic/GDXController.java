package com.mygdx.game.logic;


import com.badlogic.gdx.Input;
import com.mygdx.game.logic.event.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GDXController {
    private static Map<Integer, EventCommand> map1 = new HashMap<>();
    private static Map<Integer, EventCommand> map2 = new HashMap<>();

    private static List<Integer> list1 = new LinkedList<>();
    private static List<Integer> list2 = new LinkedList<>();

    static {
        map1.put(Input.Keys.A, new EventMoveLeft());
        map1.put(Input.Keys.W, new EventMoveUp());
        map1.put(Input.Keys.S, new EventMoveDown());
        map1.put(Input.Keys.D, new EventMoveRight());
        map1.put(Input.Keys.SPACE, new EventSetBomb());

        map2.put(Input.Keys.LEFT, new EventMoveLeft());
        map2.put(Input.Keys.UP, new EventMoveUp());
        map2.put(Input.Keys.DOWN, new EventMoveDown());
        map2.put(Input.Keys.RIGHT, new EventMoveRight());
        map2.put(Input.Keys.ENTER, new EventSetBomb());

        list1.add(Input.Keys.A); list1.add(Input.Keys.W);
        list1.add(Input.Keys.S); list1.add(Input.Keys.D);
        list1.add(Input.Keys.SPACE);

        list2.add(Input.Keys.LEFT); list2.add(Input.Keys.UP);
        list2.add(Input.Keys.DOWN); list2.add(Input.Keys.RIGHT);
        list2.add(Input.Keys.ENTER);
    }

    public static EventCommand getEventTypeOne(int key) {
        return map1.getOrDefault(key, new NoEvent());
    }

    public static List<Integer> getList1() {
        return list1;
    }

    public static List<Integer> getList2() {
        return list2;
    }

    public static EventCommand getEventTypeTwo(int key) {
        return map2.getOrDefault(key, new NoEvent());
    }
}
