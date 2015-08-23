package com.breakfastsoftware.kraken.res.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Random;

/**
 * Created by SomeLad on 8/23/2015.
 */
public enum Sound {
    CANON("Explosion", 9),
    JAB("Jab", 4),
    START("Start", 1);

    private static Random generator = new Random();

    private int amount;
    private String name;
    private Clip[] clips;

    private Sound(String name, int amount) {
        if (this.ordinal() == 0) {
            System.out.println("\nSounds Loading...");
        }

        this.name = name;
        this.amount = amount;
        clips = new Clip[amount];

        try {
            for (int i = 1; i <= amount; i++) {
                System.out.println(name+i+".wav loaded");
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
        int val = generator.nextInt(amount);
        clips[val].setFramePosition(0);
        clips[val].start();
    }
}
