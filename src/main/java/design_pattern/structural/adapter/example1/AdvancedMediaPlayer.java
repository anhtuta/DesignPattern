package design_pattern.structural.adapter.example1;

/*
 * We are having another interface AdvancedMediaPlayer and 
 * concrete classes implementing the AdvancedMediaPlayer 
 * interface. These classes can play vlc and mp4 format files.
 * These classes are: Mp4Player and VlcPlayer
 */
public interface AdvancedMediaPlayer {
	public void playVlc(String fileName) throws Exception;

	public void playMp4(String fileName) throws Exception;
}
