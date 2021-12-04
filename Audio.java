import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio{
    Clip audio;

    public Audio(int type) {
        if (type == 1) {
            try {
                audio = AudioSystem.getClip();
                audio.open(AudioSystem.getAudioInputStream(new File("Sounds/shoot.wav")));
                audio.start();
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
         else if (type == 2) {

             try {
            audio= AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(new File("Sounds/explosion.wav")));
            audio.start();
            } catch (Exception e) {
            System.out.println("" + e);
            }
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