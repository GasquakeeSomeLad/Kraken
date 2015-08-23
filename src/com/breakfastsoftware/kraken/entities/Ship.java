package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class Ship extends Entity {
    protected boolean move = false;
    protected int moveVal = 1;

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
            x+= moveVal;

        if (x < -500) {
            direction = RIGHT;
            moveVal = 1;
        }
        else if (x > 2900) {
            direction = LEFT;
            moveVal = -1;
        }
    }
}
