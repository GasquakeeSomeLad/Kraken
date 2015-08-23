package com.breakfastsoftware.kraken.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.visuals.CustomFont;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

public class MenuState extends ImagedState {

	private Font titleFont, optionFont;
	private Color[] optionColors;
	private int optionPicked = 0;
	private boolean down = false, up = false;
	
    public MenuState() {
        super(1);
        image = Images.MENU.getImage();
        titleFont = CustomFont.BLACK.getFont(false, true, 35);
        optionFont = CustomFont.BLACK.getFont(false, true, 30);
        optionColors = new Color[4];
        for (int i = 0; i < optionColors.length; i++) {
        	optionColors[i] = new Color(255, 255, 255, 230);
        }
        optionColors[optionPicked] = new Color(0, 0, 0, 230);
    }
    
    public void update() {
    	if (!down && Kraken.getKeyboard().keyDown(KeyEvent.VK_DOWN)) {
			down = true;
    		if (optionPicked < 3) {
    			optionPicked++;
    		} else {
    			optionPicked = 0;
    		}
    		for (int i = 0; i < optionColors.length; i++) {
    	       	optionColors[i] = new Color(255, 255, 255, 230);
    		}
    		optionColors[optionPicked] = new Color(0, 0, 0, 230);
    	}
		else if (down && Kraken.getKeyboard().keyUp(KeyEvent.VK_DOWN)) {
			down = false;
		}
    	if (!up && Kraken.getKeyboard().keyDown(KeyEvent.VK_UP)) {
			up = true;
    		if (optionPicked > 0) {
    			optionPicked--;
    		} else {
    			optionPicked = 3;
    		}
    		for (int i = 0; i < optionColors.length; i++) {
    	       	optionColors[i] = new Color(255, 255, 255, 230);
    		}
    		optionColors[optionPicked] = new Color(0, 0, 0, 230);
    	}
		else if (up && Kraken.getKeyboard().keyUp(KeyEvent.VK_UP)) {
			up = false;
		}
    	if (Kraken.getKeyboard().keyDown(KeyEvent.VK_ENTER)) {
    		if (optionPicked == 0) {
    			Kraken.getStateManager().setState(new GameState());
    		} else if (optionPicked == 1) {
    			Kraken.getStateManager().setState(new CreditsState());
    		} else if (optionPicked == 2) {
    			Kraken.getStateManager().setState(new HelpState());
    		} else if (optionPicked == 3) {
    			System.exit(1);
    		}
    	}
    }
    
    public void render(Graphics2D g) {
    	super.render(g);
    	g.setColor(new Color(20, 210, 150, 230));
    	g.setFont(titleFont);
    	g.drawString("ADVENTURE'S OF LENKY", 125, 100);
    	g.setFont(optionFont);
    	g.setColor(optionColors[0]);
    	g.drawString("Play", 375, 200);
    	g.setColor(optionColors[1]);
    	g.drawString("Credits", 360, 280);
    	g.setColor(optionColors[2]);
    	g.drawString("Help", 375, 360);
    	g.setColor(optionColors[3]);
    	g.drawString("Quit (huh?)", 335, 440);
    }
    
}