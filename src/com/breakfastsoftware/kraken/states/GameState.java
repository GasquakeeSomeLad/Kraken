package com.breakfastsoftware.kraken.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.Blimp;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.entities.Fish;
import com.breakfastsoftware.kraken.entities.Player;
import com.breakfastsoftware.kraken.entities.Ship;
import com.breakfastsoftware.kraken.entities.Submarine;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.Sound;
import com.breakfastsoftware.kraken.res.visuals.CustomFont;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;
import com.breakfastsoftware.kraken.util.Camera;

public class GameState extends ImagedState {
	
	private Player player;
	private EntityManager em;
    private Camera camera;
    private Images backgroundImage = Images.BACKGROUND;
    private int fishTimer = 60 * 15, round = 0, roundTime = 252;
    private boolean roundIndicator;
    private float submarines = 0.55f, ships = 1.6f, blimps = 0.45f;
    private int[] alphaPixels;
    private BufferedImage alphaImage;
    private Font font = CustomFont.BLACK.getFont(true, false, 20f), font2 = CustomFont.BLACK.getFont(true, false, 32f);

    public GameState() {
        super(2);
        alphaImage = new BufferedImage(Kraken.getGameWidth() / 2, Kraken.getGameHeight() / 2, BufferedImage.TYPE_INT_ARGB);
        alphaPixels = ((DataBufferInt) alphaImage.getRaster().getDataBuffer()).getData();
        camera = new Camera(0, 0, Images.BACKGROUND.getImage().getWidth() - Kraken.getGameWidth()/scale,
                Images.BACKGROUND.getImage().getHeight()- Kraken.getGameHeight()/scale);
        player = new Player(150, 250, camera);
        em = new EntityManager();
        em.addCloud(new Cloud(0, 24));
        em.addCloud(new Cloud(156, 20));
        em.addCloud(new Cloud(312, 44));
        em.addCloud(new Cloud(468, 13));
        em.addCloud(new Cloud(624, 51));
        em.addCloud(new Cloud(780, 35));
        em.addCloud(new Cloud(936, 16));
        em.addCloud(new Cloud(1092, 47));
        em.addCloud(new Cloud(1248, 20));
        em.addCloud(new Cloud(1384, 43));
        em.addCloud(new Cloud(1499, 17));
    }

    public void update() {
        player.update();
        if (em.getEnemyCount() == 0) {
            newRound();
        }
        if (em.noFish() && --fishTimer < 0) {
            em.setFish(new Fish(em, player));
            fishTimer = 60*15;
        }
        em.update();
        camera.setX(player.getCameraX() - Kraken.getGameWidth() / 4);
        camera.setY(player.getCameraY() - Kraken.getGameHeight() / 4);
        if (camera.getX() <= 0) {
        	camera.setX(0);
        }
        if (camera.getY() <= 0) {
        	camera.setY(0);
        }
        if (camera.getX() >= backgroundImage.getImage().getWidth() - Kraken.getGameWidth()/scale) {
        	camera.setX(backgroundImage.getImage().getWidth() - Kraken.getGameWidth() / scale);
        }
        if (camera.getY() >= backgroundImage.getImage().getHeight() - Kraken.getGameHeight()/scale) {
        	camera.setY(backgroundImage.getImage().getHeight() - Kraken.getGameHeight() / scale);
        }
        if (Kraken.getKeyboard().keyDown(KeyEvent.VK_L)) {
            Kraken.getKeyboard().releaseKey(KeyEvent.VK_L);
            if (backgroundImage == Images.BACKGROUND) {
                backgroundImage = Images.LENKY;
        	} else {
                backgroundImage = Images.BACKGROUND;
        	}
        }
    }

    private void newRound() {
    	roundIndicator = true;
        player.setPlayerHP(100);
        submarines*= 1.25f;
        ships*= 1.4f;
        blimps*=1.15f;
        if (++round == 1) {
            em.addShip(new Ship(-130, player, em));
            return;
        }
        Sound.NEWLEVEL.play();
        for (int i = (int) submarines; i > 0; i--) {
            em.addSubmarine(new Submarine(player, em));
        }
        int left = 0, right = 0;
        for (int i = (int) ships; i > 0; i--) {
            if (Math.random() > .5) {
                em.addShip(new Ship(-130-left, player, em));
                left += 70;
            } else {
                em.addShip(new Ship(1700+right, player, em));
                right += 70;
            }
        }
        int left2 = 0, right2 = 0;
        for (int i = (int) blimps; i > 0; i--) {
            if (Math.random() > .5) {
                em.addBlimp(new Blimp(-130-left2, player, em));
                left2 += 100;
            } else {
                em.addBlimp(new Blimp(1700+right2, player, em));
                right2 += 100;
            }
        }
    }

    public void render(Graphics2D g) {
        int width = Kraken.getGameWidth()/scale, height = Kraken.getGameHeight()/scale,
                imageWidth = backgroundImage.getImage().getWidth();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i+j*width] = backgroundImage.getPixels()[(getX()+i)+(getY()+j)*imageWidth];
                if (Kraken.fancy()) {
                    alphaPixels[i+j*width] = Images.ALPHABACKGROUND.getPixels()[(getX()+i)+(getY()+j)*imageWidth];
                }
            }
        }
        if (Kraken.fancy()) {
            em.fancyRender(getX(), getY(), Kraken.getGameWidth() / scale, pixels, alphaPixels);
        } else {
            em.render(getX(), getY(), Kraken.getGameWidth() / scale, pixels);
        }
        player.render(getX(), getY(), Kraken.getGameWidth() / scale, pixels);
        super.render(g);
        if (Kraken.fancy()) {
            g.drawImage(alphaImage, 0, 0, Kraken.getGameWidth(), Kraken.getGameHeight(), null);
        }
        g.setColor(new Color(32, 32, 32, 100));
        g.setFont(font);
        g.drawString("Round: " + round, 18, 496);
        g.drawString("Foes Left: " + em.getEnemyCount(), 18, 521);
        g.fillRect(13, 531, 100*scale, 29);
        g.setColor(new Color(128, 0, 0, 150));
        g.fillRect(13, 531, player.getPlayerHP() * scale, 29);
        if (roundIndicator) {
        	roundTime -= 2;
        	g.setFont(font2);
            g.setColor(new Color(10, 180, 120, roundTime));
        	g.drawString("Round: " + round, 335, 200+roundTime/3);
        	if (roundTime <= 0) {
        		roundTime = 252;
        		roundIndicator = false;
        	}
        }
    }

    public int getX() {
        return camera.getX();
    }

    public int getY() {
        return camera.getY();
    }

}