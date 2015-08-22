package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;
import com.breakfastsoftware.kraken.states.core.StateManager;

public class LogoState extends ImagedState {
	protected int counter = 70;

	public LogoState(int scale) {
		super(scale);

		image = Images.LOGO.getImage();
	}

	public void update() {
		if (--counter < 0)
			Kraken.getStateManager().setState(new MenuState(1));
	}

}