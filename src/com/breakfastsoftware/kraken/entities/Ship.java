package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Ship extends Entity {
	
	protected EntityManager em;
	protected Player player;
    protected boolean move = false, alive = true;
    protected int moveVal = -1;
    protected int bulletTime = 30;

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
        if (!alive) {
            if (++y > 1200) {
                em.removeDead(this);
            }
            return;
        }
        else if (Calculations.collision(player.getX(), player.getY(), player.getWidth(), player.getHeight(), x, y, w, h)) {
            Sound.DESTROY.play();
            alive = false;
            em.addEmote(new DeathEmote(x+w/2, y+h/2, em));
            em.removeShip(this);
            move(0, h);
            if (sprite == Sprite.FRIGATE1) {
                sprite = Sprite.FRIGATE1DEAD;
            }
            else {
                sprite = Sprite.FRIGATE2DEAD;
            }
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
        if (x < 1600 && Calculations.getDistanceX(player.getX(), x) < 110 && Calculations.getDistanceY(player.getY(), y) < 200) {
        	bulletTime--;
        	if (bulletTime <= 0) {
        		em.addBullet(new Bullet(x + 30, y + 30, player, em, true, false));
        		bulletTime = 30;
        	}
        }
    }
    
}