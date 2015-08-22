package com.breakfastsoftware.kraken.res;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by SomeLad on 8/21/2015.
 */
public enum Images {
    IMAGE("No paths yet");

    protected BufferedImage image;
    protected int[] pixels;

    private Images(String path) {
        ImageLoader.loadImage(path);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public BufferedImage getImage() {
        return image;
    }

    public int[] getPixels() {
        return pixels;
    }
}
