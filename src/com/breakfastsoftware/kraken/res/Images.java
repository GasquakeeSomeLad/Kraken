package com.breakfastsoftware.kraken.res;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by SomeLad on 8/21/2015.
 */
public enum Images {
    BACKGROUND("/com/breakfastsoftware/kraken/res/background.png"),
    CLOUD("/com/breakfastsoftware/kraken/res/cloud.png"),
    ENTITIES("/com/breakfastsoftware/kraken/res/kraken.png"),
    LOGO("/com/breakfastsoftware/kraken/res/breakfast.png"),
    MENU("/com/breakfastsoftware/kraken/res/menu.png");

    protected BufferedImage image;
    protected int[] pixels;

    private Images(String path) {
        image = ImageLoader.loadImage(path);
        pixels = new int[image.getWidth()*image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getPixels() {
        return pixels;
    }
}
