package com.breakfastsoftware.kraken.res;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Sprite {
    public final int[] PIXELS;

    public Sprite(SpriteSheet sheet, int x, int y, int w, int h) {
        PIXELS = sheet.getPixels(x, y, w, h);
    }
}
