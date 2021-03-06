package com.breakfastsoftware.kraken.res.visuals;

public class SpriteSheet {
	public static final SpriteSheet ENTITIES = new SpriteSheet(Images.ENTITIES);

	private Images image;
	
	public SpriteSheet(Images image) {
		this.image = image;
	}
	
	public int[] getPixels(int x, int y, int w, int h) {
		int[] pixels = new int[w*h];
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				pixels[i+j*w] = image.getPixels()[(i+x)+(j+y)*image.getImage().getWidth()];
			}
		}
		return pixels;
	}
}