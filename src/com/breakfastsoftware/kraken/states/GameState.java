package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.core.Entity;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;
import com.breakfastsoftware.kraken.util.Camera;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class GameState extends ImagedState {
    //TEMP
    Entity cloud1 = new Cloud(-100, 10), cloud2 = new Cloud(517, 13), cloud3 = new Cloud(1262, 8);

    private boolean left, right, up, down;
    private Camera camera;

    public GameState() {
        super(2);
        camera = new Camera(0, 0, Images.BACKGROUND.getImage().getWidth() - Kraken.getGameWidth()/scale,
                Images.BACKGROUND.getImage().getHeight()- Kraken.getGameHeight()/scale);
    }

    public void update() {
        updateKeys();

        if (left)
            camera.move(-2, 0);
        if (right)
            camera.move(2, 0);
        if (up)
            camera.move(0, -2);
        if (down)
            camera.move(0, 2);

        cloud1.update(); cloud2.update(); cloud3.update();
    }

    private void updateKeys() {
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_A))
            left = true;
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_D))
            right = true;
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_W))
            up = true;
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_S))
            down = true;

        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_A))
            left = false;
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_D))
            right = false;
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_W))
            up = false;
        if (Kraken.getKeyboard().keyUp(KeyEvent.VK_S))
            down = false;

    }

    public void render(Graphics2D g) {
        int width = Kraken.getGameWidth()/scale, height = Kraken.getGameHeight()/scale,
                imageWidth = Images.BACKGROUND.getImage().getWidth();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                pixels[i+j*width] = Images.BACKGROUND.getPixels()[(getX()+i)+(getY()+j)*imageWidth];

        cloud1.render(getX(), getY(), Kraken.getGameWidth()/scale, pixels);
        cloud2.render(getX(), getY(), Kraken.getGameWidth() / scale, pixels);
        cloud3.render(getX(), getY(), Kraken.getGameWidth()/scale, pixels);

        super.render(g);
    }

    public int getX() {
        return camera.getX();
    }

    public int getY() {
        return camera.getY();
    }
}
