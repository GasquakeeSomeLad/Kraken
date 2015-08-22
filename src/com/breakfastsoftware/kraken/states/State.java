package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;

import java.awt.*;

/**
 * Created by SomeLad on 8/21/2015.
 */
public abstract class State {

    public abstract void update();

    public abstract void render(Graphics2D graphics);
}
