package com.breakfastsoftware.kraken.res;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Sprite {
    public static final Sprite CLOUD = new Sprite(SpriteSheet.TESTING, 0, 0, 238, 70),
    HEAD = new Sprite(SpriteSheet.ENTITIES, 0, 0, 32, 32);

    public final int WIDTH, HEIGHT, PIXELS[];

    public Sprite(SpriteSheet sheet, int x, int y, int w, int h) {
        PIXELS = sheet.getPixels(x, y, w, h);
        WIDTH = w;
        HEIGHT = h;
    }

}
