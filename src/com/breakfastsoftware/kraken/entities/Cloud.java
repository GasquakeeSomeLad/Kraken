package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.res.Sprite;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class Cloud extends Entity {
    protected int counter = 5;

    public Cloud(int x, int y) {
        super(x, y, Sprite.CLOUD);
    }

    @Override
    public void update() {
        if (--counter < 0) {
            x++;
            counter = 5;
        }
        if (x > Images.BACKGROUND.getImage().getWidth())
            x = -sprite.WIDTH;
    }
}
