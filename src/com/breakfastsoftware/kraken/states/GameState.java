package com.breakfastsoftware.kraken.states;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import com.breakfastsoftware.kraken.Kraken;
import com.breakfastsoftware.kraken.entities.Cloud;
import com.breakfastsoftware.kraken.entities.Fish;
import com.breakfastsoftware.kraken.entities.Player;
import com.breakfastsoftware.kraken.entities.Ship;
import com.breakfastsoftware.kraken.entities.core.EntityManager;
import com.breakfastsoftware.kraken.res.visuals.Images;
import com.breakfastsoftware.kraken.states.core.ImagedState;
import com.breakfastsoftware.kraken.util.Camera;

public class GameState extends ImagedState {
	
	private Player player;
	private EntityManager em;
    private Camera camera;
    private Images backgroundImage = Images.BACKGROUND;
    private boolean lenky = false;
    private int fishTimer= 60 * 15;

    public GameState() {
        super(2);
        camera = new Camera(0, 0, Images.BACKGROUND.getImage().getWidth() - Kraken.getGameWidth()/scale,
                Images.BACKGROUND.getImage().getHeight()- Kraken.getGameHeight()/scale);
        player = new Player(150, 250, camera);

        em = new EntityManager();
        em.addCloud(new Cloud(-100, 10));
        em.addCloud(new Cloud(522, 13));
        em.addCloud(new Cloud(1101, 8));

        em.addShip(new Ship(70, player, em));
        em.addShip(new Ship(-20, player, em));
        em.addShip(new Ship(-110, player, em));
        em.addShip(new Ship(-200, player, em));
    }

    public void update() {
        player.update();
        if (em.noFish() && --fishTimer < 0) {
            em.setFish(new Fish(em, player));
            fishTimer = 60*15;
        }
        em.update();
        camera.setX(player.getX() - 120);
        camera.setY(player.getY() - 120);
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

        if (!lenky && Kraken.getKeyboard().keyDown(KeyEvent.VK_L)) {
            lenky = true;
            if (backgroundImage == Images.BACKGROUND)
                backgroundImage = Images.LENKY;
            else
                backgroundImage = Images.BACKGROUND;
        }
        else if (lenky && Kraken.getKeyboard().keyUp(KeyEvent.VK_L)) {
            lenky = false;
        }
        Kraken.getKeyboard().setKeyDown(0);
    }

    public void render(Graphics2D g) {
        int width = Kraken.getGameWidth()/scale, height = Kraken.getGameHeight()/scale,
                imageWidth = backgroundImage.getImage().getWidth();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                pixels[i+j*width] = backgroundImage.getPixels()[(getX()+i)+(getY()+j)*imageWidth];
            }
        }
        em.render(getX(), getY(), Kraken.getGameWidth()/scale, pixels);
        player.render(getX(), getY(), Kraken.getGameWidth()/scale, pixels);
        super.render(g);
    }

    public int getX() {
        return camera.getX();
    }

    public int getY() {
        return camera.getY();
    }
}
