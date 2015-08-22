package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

/**
 * Created by SomeLad on 8/22/2015.
 */
public class MenuState extends ImagedState {

    public MenuState() {
        super(1);

        image = Images.MENU.getImage();
    }
}
