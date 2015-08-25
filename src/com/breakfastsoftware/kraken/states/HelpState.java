package com.breakfastsoftware.kraken.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.audio.Sound;
import com.breakfastsoftware.kraken.res.visuals.CustomFont;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

public class HelpState extends ImagedState {
	
	private Font font;
	
	public HelpState() {
		super(1);
		image = Images.MENU.getImage();
        font = CustomFont.BLACK.getFont(false, true, 40);
		Kraken.getKeyboard().releaseKey(KeyEvent.VK_ENTER);
	}

	public void update() {
		if (Kraken.getKeyboard().keyDown(KeyEvent.VK_ENTER)) {
			Sound.FISH.play();
			Kraken.getStateManager().setState(new MenuState());
		}
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setFont(font);
    	g.setColor(new Color(40, 220, 160, 230));
    	g.drawString("Controls:", 15, 40);
		g.drawString("Movement: Move mouse around,", 40, 90);
		g.drawString("Recreational Biting: Left Click,", 40, 140);
		g.drawString("Jab: Right Click,", 40, 190);
		g.drawString("Jump: Space,", 40, 240);
		g.drawString("Turn music off: M,", 40, 290);
		g.drawString("Fancy Water: F", 40, 340);
		g.drawString("Objective:", 15, 400);
		g.drawString("Survive as many rounds of boats, blimps, ", 40, 445);
		g.drawString("and submarines as possible.", 40, 490);
		g.drawString("(Enter)", 674, 558);
	}

}