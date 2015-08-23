package com.breakfastsoftware.kraken.res.visuals;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImageLoader {
	private static ImageLoader loader = new ImageLoader();

	private ImageLoader() {}

	public static BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(loader.getClass().getResourceAsStream(path));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
	
}