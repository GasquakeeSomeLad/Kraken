package com.breakfastsoftware.kraken.states.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import com.breakfastsoftware.kraken.Kraken;

public class ImagedState extends State {
	
	protected BufferedImage image;
	protected int[] pixels;

    public ImagedState(int scale) {
        image = new BufferedImage(Kraken.getGameWidth() / scale, Kraken.getGameWidth() / scale, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public void update() {
    	
    }

    public void render(Graphics2D g) {
    	g.drawImage(image, 0, 0, null);
    }
}
