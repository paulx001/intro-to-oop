import java.io.*;
import javax.sound.sampled.*;

// class creates public sounds and plays them
public class Audio {

	// public constants of needed sounds
	public static final File MOUSE_CLICK = new File("sounds/click.wav");
	public static final File DING = new File("sounds/ding.wav");
	public static final File WRONG_ANSWER = new File("sounds/wrong.wav");
	public static final File FINISHED = new File("sounds/finished.wav");

	// global method plays sound (found at https://stackoverflow.com/questions/557903/how-can-i-wait-for-a-java-sound-clip-to-finish-playing-back)
	public static void Play(File soundFile) {

		// try opening sound file and playing
		
		try {

			Clip audioClip = AudioSystem.getClip();
			audioClip.open(AudioSystem.getAudioInputStream(soundFile)); // open sound file
			audioClip.start(); // start playing
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
	}
	
}
