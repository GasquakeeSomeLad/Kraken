package com.breakfastsoftware.kraken.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.res.visuals.CustomFont;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;

public class CreditsState extends ImagedState {

	private Font font, font2;
	
    public CreditsState() {
        super(1);
        image = Images.MENU.getImage();
        font = CustomFont.BLACK.getFont(false, true, 35);
        font2 = CustomFont.BLACK.getFont(false, true, 25);

		Kraken.getKeyboard().releaseKey(KeyEvent.VK_ENTER);
    }
    
    public void update() {
    	if (Kraken.getKeyboard().keyDown(KeyEvent.VK_ENTER)) {
    		Kraken.getStateManager().setState(new MenuState());
    	}
    }
    
    public void render(Graphics2D g) {
    	super.render(g);
    	g.setFont(font);
    	g.setColor(new Color(40, 200, 120, 230));
    	g.drawString("People who made Lenky possible:", 190, 30);
    	g.setColor(new Color(40, 220, 160, 230));
    	g.drawString("Programmar: SomeLad,", 245, 110);
    	g.drawString("Programmar & Asshole: gasquakee,", 170, 180);
    	g.drawString("Font: Doug Miles", 288, 250);
    	g.setFont(font2);
    	g.drawString("(his work: http://www.1001fonts.com/blackchancery-font.html)", 105, 320);
    	g.setColor(new Color(150, 80, 80, 230));
    	g.setFont(font);
    	g.drawString("(Enter = these are cool people)", 215, 555);
    }
    
}