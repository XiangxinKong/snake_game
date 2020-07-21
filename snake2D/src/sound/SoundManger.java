package sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * play the sound effect
 */
public class SoundManger extends Thread {
	/////
    private File soundFile;
    private AudioInputStream audioStream;

    
    public SoundManger(String soundFilePath) {
    	try {
            soundFile = new File(soundFilePath);
        } catch (Exception e) {
        	System.out.println("problem in catch0");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            audioStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (Exception e){
        	System.out.println("problem in catch2");
            e.printStackTrace();
            System.exit(1);
        }
	}
    protected void play() throws LineUnavailableException, IOException {
		Clip clip = AudioSystem.getClip();
		clip.open(audioStream);
		try {
			clip.start();
		} catch(Exception e) {
			System.out.println("problem in catch3");
			e.printStackTrace();
		} /*finally {
			System.out.println("problem in catch3");
			clip.close();
		}*/
		audioStream.close();
	}
    
    @Override
	public void run() {
		try {
			this.play();
		} catch (Exception e) {
			System.out.println("problem in catch4");
			e.printStackTrace();
		}
	}

}
