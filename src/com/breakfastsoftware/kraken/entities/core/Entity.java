package com.breakfastsoftware.kraken.entities.core;

import com.breakfastsoftware.kraken.res.visuals.Sprite;

public abstract class Entity {
    public static final int RIGHT = 0, LEFT = 1;

    protected int x, y, w, h;
    protected int dx, dy, direction = LEFT;

    protected Sprite sprite;

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        w = sprite.WIDTH;
        h = sprite.HEIGHT;
        this.sprite = sprite;
    }

    public abstract void update();

    public void render(int cameraX, int cameraY, int screenWidth, int[] pixels) {
        int renderX = x - cameraX, renderY = (y) - cameraY;
        if (renderX < -sprite.WIDTH || renderX > 400)
            return;
        if (renderY < -sprite.HEIGHT || renderY > 300)
            return;

        int i = 0, k = 0, renderWidth=sprite.WIDTH, renderHeight=sprite.HEIGHT;
        if (renderX < 0)
            i = -renderX;
        if (renderY < 0)
            k = -renderY;
        if (renderX > 400-renderWidth)
            renderWidth = 400-renderX;
        if (renderY > 300-renderHeight)
            renderHeight = 300-renderY;

        if (direction == LEFT) {
            for (; i < renderWidth; i++)
                for (int j = k; j < renderHeight; j++)
                    if (sprite.PIXELS[i+j*sprite.WIDTH] != 0xFFFF00FF)
                        pixels[(renderX+i)+(renderY+j)*screenWidth] = sprite.PIXELS[i+j*sprite.WIDTH];
        }
        else if (direction == RIGHT) {
            for (; i < renderWidth; i++)
                for (int j = k; j < renderHeight; j++)
                    if (sprite.PIXELS[(sprite.WIDTH-i-1)+j*sprite.WIDTH] != 0xFFFF00FF)
                        pixels[(renderX+i)+(renderY+j)*screenWidth] = sprite.PIXELS[(sprite.WIDTH-i-1)+j*sprite.WIDTH];
        }
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        this.dx = dx;
        this.dy = dy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return w;
    }

    public void setWidth(int w) {
        this.w = w;
    }

    public int getHeight() {
        return h;
    }

    public void setHeight(int h) {
        this.h = h;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDirection() {
        return direction;
    }

    public int getSegmentY() {
        return y;
    }
}