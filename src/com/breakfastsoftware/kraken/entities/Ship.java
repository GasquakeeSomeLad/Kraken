package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class Ship extends Entity {
    boolean move = false;

    public Ship(int x) {
        super(x, 210, Sprite.FRIGATE1);
        if (Math.random() > .5)
            sprite = Sprite.FRIGATE2;
        direction = RIGHT;
    }

    @Override
    public void update() {
        move = !move;
        if (move)
            x++;
    }
}
