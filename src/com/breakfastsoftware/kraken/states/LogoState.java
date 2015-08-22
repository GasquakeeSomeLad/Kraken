package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

public class LogoState extends ImagedState {
	protected int counter = 110;

	public LogoState(int scale) {
		super(scale);

		image = Images.LOGO.getImage();
	}

	public void update() {
		if (--counter < 0)
			Kraken.getStateManager().setState(new GameState(2));
	}

}