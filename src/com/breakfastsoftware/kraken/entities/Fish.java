package com.breakfastsoftware.kraken.entities;

import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.res.visuals.Sprite;
import com.breakfastsoftware.kraken.util.Calculations;

public class Fish extends Entity {

    protected int counter = 60 * 30;
    protected Player player;
    protected EntityManager em;

    public Fish(EntityManager em, Player player) {
        super((int)(Math.random() * Images.BACKGROUND.getImage().getWidth()),
                (int)(Math.random() * ((Images.BACKGROUND.getImage().getHeight()-400)))+400, Sprite.FISH);
        this.em = em;
        this.player = player;
    }

    public void update() {
        if (--counter < 0) {
            em.setFish(null);
        } else if (Calculations.collision(player.getX(), player.getY(), player.getWidth(), player.getHeight(),
                x, y, sprite.HEIGHT, sprite.HEIGHT)) {
            if (player.getPlayerHP() <= 85) {
                player.setPlayerHP(player.getPlayerHP()+15);
                em.setFish(null);
                Sound.FISH.play();
            }
        }
    }

}