package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Ship extends Entity {
	
	protected EntityManager em;
	protected Player player;
    protected boolean move = false;
    protected int moveVal = 1;
    protected int bulletTime = 50;

    public Ship(int x, Player player, EntityManager em) {
        super(x, 210, Sprite.FRIGATE1);
        this.em = em;
        this.player = player;
        if (Math.random() > .5) {
            sprite = Sprite.FRIGATE2;
        }
        direction = RIGHT;
    }

    public void update() {
        move = !move;
        if (move) {
        	x += moveVal;
        }
        if (x < -200) {
            direction = RIGHT;
            moveVal = 1;
        } else if (x > 1750) {
            direction = LEFT;
            moveVal = -1;
        }
        if (Calculations.getDistanceX(player.getX(), x) < 200 && Calculations.getDistanceY(player.getY(), y) < 150) {
        	bulletTime--;
        	if (bulletTime <= 0) {
        		em.addBullet(new Bullet(x + 30, y + 30, player, em, true));
        		bulletTime = 50;
        	}
        }
    }
    
}