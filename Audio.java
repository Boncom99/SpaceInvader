import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Audio{
    Clip shoot;
    public Audio() {
        try {
            shoot= AudioSystem.getClip();
            shoot.open(AudioSystem.getAudioInputStream(new File("Sounds/shoot.wav")));
            shoot.start();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

    boolean close() {
        if (!shoot.isRunning())
        {
            shoot.close(); 
            return true;
        }
        return false;
    }
    void shoot() {
        shoot.start();
    }

}