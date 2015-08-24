package com.breakfastsoftware.kraken.res.visuals;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Sprite {
    public static final Sprite CLOUD1 = new Sprite(SpriteSheet.ENTITIES, 64, 64, 64, 32),
    CLOUD2 = new Sprite(SpriteSheet.ENTITIES, 64, 96, 64, 32),
    PLAYERHEAD = new Sprite(SpriteSheet.ENTITIES, 1, 5, 22, 22),
    PLAYERHEADCLOSING = new Sprite(SpriteSheet.ENTITIES, 128, 35, 22, 17),
    PLAYERHEADCLOSED = new Sprite(SpriteSheet.ENTITIES, 151, 55, 22, 13),
    PLAYERHEADDOWN = new Sprite(SpriteSheet.ENTITIES, 128, 52, 22, 24),
    PLAYERHEADUP = new Sprite(SpriteSheet.ENTITIES, 130, 102, 22, 22),
    PLAYERSEGMENT1 = new Sprite(SpriteSheet.ENTITIES, 23, 7, 5, 10),
    PLAYERSEGMENT2 = new Sprite(SpriteSheet.ENTITIES, 28, 7, 4, 10),
    PLAYERLEGS = new Sprite(SpriteSheet.ENTITIES, 32, 7, 9, 8),
    PLAYERFEET = new Sprite(SpriteSheet.ENTITIES, 41, 9, 9, 6),
    PLAYERTAIL = new Sprite(SpriteSheet.ENTITIES, 50, 11, 9, 4),
    FRIGATE1 = new Sprite(SpriteSheet.ENTITIES, 0, 64, 64, 32),
    FRIGATE2 = new Sprite(SpriteSheet.ENTITIES, 0, 96, 64, 32),
    SUBMARINE = new Sprite(SpriteSheet.ENTITIES, 61, 2, 66, 24),
    BLIMP = new Sprite(SpriteSheet.ENTITIES, 128, 0, 61, 27),
    FRIGATE1DEAD = new Sprite(SpriteSheet.ENTITIES, 192, 32, 64, 32),
    FRIGATE2DEAD = new Sprite(SpriteSheet.ENTITIES, 192, 64, 64, 32),
    SUBMARINEDEAD = new Sprite(SpriteSheet.ENTITIES, 189, 2, 66, 24),
    BLIMPDEAD = new Sprite(SpriteSheet.ENTITIES, 192, 96, 61, 27),
    BULLET = new Sprite(SpriteSheet.ENTITIES, 121, 36, 3, 3),
    MISSILE = new Sprite(SpriteSheet.ENTITIES, 119, 40, 9, 3),
    BOMB = new Sprite(SpriteSheet.ENTITIES, 111, 47, 4, 9),
    DEATHEMOTE = new Sprite(SpriteSheet.ENTITIES, 120, 46, 5, 6),
    FISH = new Sprite(SpriteSheet.ENTITIES, 96, 32, 21, 13);

    public final int WIDTH, HEIGHT, PIXELS[];

    public Sprite(SpriteSheet sheet, int x, int y, int w, int h) {
        PIXELS = sheet.getPixels(x, y, w, h);
        WIDTH = w;
        HEIGHT = h;
    }

}
