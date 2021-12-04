import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio{
    Clip audio;

    public Audio(int type) {
        switch (type) {
            case 1:
                try {
                    audio = AudioSystem.getClip();
                    audio.open(AudioSystem.getAudioInputStream(new File("Sounds/shoot.wav")));
                    audio.start();
                } catch (Exception e) {
                    System.out.println("" + e);
                }
            break;
            case 2:
                try {
                    audio= AudioSystem.getClip();
                    audio.open(AudioSystem.getAudioInputStream(new File("Sounds/explosion.wav")));
                    audio.start();
                } catch (Exception e) {
                    System.out.println("" + e);
                }
            break;
            case 3:
                try {
                    audio= AudioSystem.getClip();
                    audio.open(AudioSystem.getAudioInputStream(new File("Sounds/invaderkilled.wav")));
                    audio.start();
                } catch (Exception e) {
                    System.out.println("" + e);
                } 
            break;
        }
     
    }

    boolean close() {
        if (!audio.isRunning())
        {
            //audio.stop(); 
            //audio.close(); 
            return true;
        }
        return false;
    }
    void start() {
        audio.start();
    }
}