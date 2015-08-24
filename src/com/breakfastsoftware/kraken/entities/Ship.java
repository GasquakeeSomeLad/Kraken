package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Ship extends Entity {
	
	protected EntityManager em;
	protected Player player;
    protected boolean move = false;
    protected int moveVal = -1;
    protected int bulletTime = 50;

    public Ship(int x, Player player, EntityManager em) {
        super(x, 210, (Math.random() > .5) ? Sprite.FRIGATE1 : Sprite.FRIGATE2);
        this.em = em;
        this.player = player;
        if (y < 0) {
            moveVal = 1;
            direction = RIGHT;
        }
    }

    public void update() {
        if (Calculations.collision(player.getX(), player.getY(), player.getWidth(), player.getHeight(), x, y, w, h)) {
            em.removeShip(this);
            return;
        }
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
        if (x-w > 0 && x < 1600 && Calculations.getDistanceX(player.getX(), x) < 300 && Calculations.getDistanceY(player.getY(), y) < 200) {
        	bulletTime--;
        	if (bulletTime <= 0) {
        		em.addBullet(new Bullet(x + 30, y + 30, player, em, true));
        		bulletTime = 50;
        	}
        }
    }
    
}