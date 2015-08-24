package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.visuals.Sprite;

/**
 * Created by SomeLad on 8/24/2015.
 */
public class DeathEmote extends Entity {
    EntityManager em;

    public DeathEmote(int x, int y, EntityManager em) {
        super(x, y, Sprite.DEATHEMOTE);
        this.em = em;
    }

    public void update() {
        if (--y < -h)
            em.removeEmote(this);
    }
}
