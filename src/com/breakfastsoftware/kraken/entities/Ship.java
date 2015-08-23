package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Sprite;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class Ship extends Entity {

    public Ship(int x, int y) {
        super(x, y, Sprite.SHIP);
    }

    @Override
    public void update() {

    }
}
