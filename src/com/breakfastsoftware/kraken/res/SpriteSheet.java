package com.breakfastsoftware.kraken.res;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage(int x, int y, int w, int h) {
		BufferedImage img;
		img = image.getSubimage(x, y, w, h);
		return img;
	}
	
}