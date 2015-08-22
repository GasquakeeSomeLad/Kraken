package com.breakfastsoftware.kraken.states;

import com.breakfastsoftware.kraken.res.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

public class LogoState extends ImagedState {

	public LogoState(int scale) {
		super(scale);

		image = Images.LOGO.getImage();
	}

}