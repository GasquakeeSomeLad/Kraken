package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;
import com.breakfastsoftware.kraken.util.Camera;

import java.awt.*;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class GameState extends ImagedState {
    private Camera camera = new Camera(0, 0);

    public GameState(int scale) {
        super(scale);
    }

    public void render(Graphics2D g) {
        int width = Kraken.getGameWidth()/scale, height = Kraken.getGameHeight()/scale,
                imageWidth = Images.BACKGROUND.getImage().getWidth();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                pixels[i+j*width] = Images.BACKGROUND.getPixels()[(getX()+i)+(getY()+j)*imageWidth];
        super.render(g);
    }

    public int getX() {
        return camera.getX();
    }

    public int getY() {
        return camera.getY();
    }
}
