package com.breakfastsoftware.kraken.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Keyboard implements KeyListener {
    public boolean anykey = false;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent ev) {
        anykey = true;
    }

    public void keyReleased(KeyEvent ev) {
        anykey = false;
    }
}
