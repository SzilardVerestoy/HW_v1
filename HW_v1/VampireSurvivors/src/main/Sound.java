package main;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    

    Clip clip;
    String soundURL[] = new String[30];

    public Sound(){

        soundURL[0] = "res/sound/Begining.wav";
        soundURL[1] = "res/sound/Unholy.wav";
        soundURL[2] = "res/sound/Vempair.wav";
    }

    public void setFile(int i) {

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(soundURL[i]));
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {
        clip.stop();
    }
}
