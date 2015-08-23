package com.breakfastsoftware.kraken.res.visuals;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Sprite {
    public static final Sprite CLOUD = new Sprite(SpriteSheet.TESTING, 0, 0, 238, 70),
    PLAYERHEAD = new Sprite(SpriteSheet.ENTITIES, 1, 5, 22, 22),
    PLAYERSEGMENT = new Sprite(SpriteSheet.ENTITIES, 23, 7, 9, 10),
    FRIGATE1 = new Sprite(SpriteSheet.ENTITIES, 0, 64, 64, 32),
    FRIGATE2 = new Sprite(SpriteSheet.ENTITIES, 0, 96, 64, 32),
    BULLET = new Sprite(SpriteSheet.ENTITIES, 121, 36, 3, 3),
    FISH = new Sprite(SpriteSheet.ENTITIES, 96, 32, 21, 13);

    public final int WIDTH, HEIGHT, PIXELS[];

    public Sprite(SpriteSheet sheet, int x, int y, int w, int h) {
        PIXELS = sheet.getPixels(x, y, w, h);
        WIDTH = w;
        HEIGHT = h;
    }

}
