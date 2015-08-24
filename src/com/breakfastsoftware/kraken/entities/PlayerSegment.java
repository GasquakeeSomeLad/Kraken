package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

/**
 * Created by SomeLad on 8/23/2015.
 */
public class PlayerSegment extends Entity {
    protected int difference = 0;
    protected Entity parent;

    public PlayerSegment(Entity parent, Sprite sprite) {
        super(parent.getX()-sprite.WIDTH, parent.getY()+2, sprite);
        this.parent = parent;
    }

    public PlayerSegment(Entity parent, int difference, Sprite sprite) {
        super(parent.getX()-sprite.WIDTH, parent.getY()+2, sprite);
        this.parent = parent;
        this.difference = difference;
    }

    @Override
    public void update() {
        if (parent.getY() > y+1) {
            y = parent.getY()-1+difference;
            if (h < 10) {
                y--;
            }
        }
        else if (parent.getY() < y-1-difference) {
            y = parent.getY()+1;
            if (h < 10) {
                y += 2;
            }
        }
        if (Calculations.collision(parent.getX(), parent.getY(), parent.getWidth(), parent.getHeight(), x, y, w, h));
        else if (parent.getX()-w > x) {
            direction = RIGHT;
            x = parent.getX()-w;
        }
        else if (parent.getX() < x) {
            direction = LEFT;
            x = parent.getX()+parent.getWidth();
        }
    }
}
