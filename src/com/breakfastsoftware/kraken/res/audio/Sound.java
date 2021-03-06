package com.breakfastsoftware.kraken.res.audio;

import com.breakfastsoftware.kraken.Kraken;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;

/**
 * Created by SomeLad on 8/23/2015.
 */
public enum Sound {
    CANON("Explosion", 9),
    DAMAGE("Damage", 4),
    DESTROY("Destroy", 5),
    FISH("Fish", 3),
    JAB("Jab", 4),
    JUMP("Jump", 1),
    MOVE("Move", 1),
    NEWLEVEL("NewLevel", 1),
    START("Start", 1),;

    private static Random generator = new Random();

    private int amount;
    private Clip[] clips;

    private Sound(String name, int amount) {
        this.amount = amount;
        clips = new Clip[amount];

        try {
            for (int i = 1; i <= amount; i++) {
                AudioInputStream stream = AudioSystem.getAudioInputStream(Sound.class.getResource(name + i + ".wav"));
                clips[i-1] = AudioSystem.getClip();
                clips[i-1].open(stream);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void play() {
        if (!Kraken.playSound())
            return;
        int val = generator.nextInt(amount);
        clips[val].setFramePosition(0);
        clips[val].start();
    }
}
