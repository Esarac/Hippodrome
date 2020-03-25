package thread;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class MusicThread extends Thread {
	
	private final static String musicFile = "med/mlptheme.mp3";    
	final static Media sound = new Media(new File(musicFile).toURI().toString());
	final static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	public MusicThread() {

        setDaemon(true);

        mediaPlayer.setOnEndOfMedia( () -> {
            mediaPlayer.seek(Duration.ZERO);
        });
    }
	

	public void run() {

		mediaPlayer.play();
		mediaPlayer.setVolume(0.05);
	}
	
	
	
}