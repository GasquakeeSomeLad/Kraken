package com.breakfastsoftware.kraken.res;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {

	public BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}