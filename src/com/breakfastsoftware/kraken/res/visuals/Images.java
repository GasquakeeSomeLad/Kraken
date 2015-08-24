package com.breakfastsoftware.kraken.res.visuals;

import java.awt.image.BufferedImage;

/**
 * Created by SomeLad on 8/21/2015.
 */
public enum Images {
    ALPHABACKGROUND("/com/breakfastsoftware/kraken/res/visuals/alphaBackground.png"),
    BACKGROUND("/com/breakfastsoftware/kraken/res/visuals/background.png"),
    ENTITIES("/com/breakfastsoftware/kraken/res/visuals/entities.png"),
    LENKY("/com/breakfastsoftware/kraken/res/visuals/lenky.png"),
    LOGO("/com/breakfastsoftware/kraken/res/visuals/breakfast.png"),
    MENU("/com/breakfastsoftware/kraken/res/visuals/menu.png");

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
