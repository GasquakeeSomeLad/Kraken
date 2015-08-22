package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class ImagedState extends State {
    protected BufferedImage image;

    public ImagedState(int scale) {
        image = new BufferedImage(Kraken.getGameWidth()/scale, Kraken.getGameWidth()/scale, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics2D graphics) {

    }
}
