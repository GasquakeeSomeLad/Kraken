package com.breakfastsoftware.kraken.util;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class Calculations {
    private Calculations() {}

    public static boolean collision(int x, int y, int w, int h, int x2, int y2, int w2, int h2) {
        if (x + w <= x2) {
            return false;
        }
        else if (y + h <= y2) {
            return false;
        }
        else if (x2 + w2 <= x) {
            return false;
        }
        else if (y2 + h2 <= y) {
            return false;
        }
        return true;
    }
}
