package com.breakfastsoftware.kraken.input;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by SomeLad on 8/21/2015.
 */
public class Mouse implements MouseListener, MouseMotionListener {
    public static final int INVALID = -1;

    private boolean right = false, left = false;
    private int x = 0, y = 0, x2 = -1, y2 = -1;

    public void mousePressed(MouseEvent ev) {
        if (SwingUtilities.isLeftMouseButton(ev)) {
            left = true;
            x2 = ev.getX();
            y2 = ev.getY();
        }
        else
            right = true;
    }

    public void mouseReleased(MouseEvent ev) {
        if (SwingUtilities.isLeftMouseButton(ev)) {
            left = false;
            x2 = y2 = -1;
        }
        else
            right = false;
    }

    public void mouseDragged(MouseEvent ev) {
        x = ev.getX();
        y = ev.getY();
    }

    public void mouseMoved(MouseEvent ev) {
        x = ev.getX();
        y = ev.getY();
    }

    public void mouseClicked(MouseEvent ev) {}

    public void mouseEntered(MouseEvent ev) {}

    public void mouseExited(MouseEvent ev) {}

    public boolean left() {
        return left;
    }

    public boolean right() {
        return right;
    }

    public void unclick() {
        unclickLeft();
        unclickRight();
    }

    public void unclickLeft() {
        left = false;
    }

    public void unclickRight() {
        right = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }
}
