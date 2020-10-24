package design_pattern.structural.adapter.example1;

/*
 * We want to make AudioPlayer to play other formats as well. To 
 * attain this, we have created an adapter class MediaAdapter which 
 * implements the MediaPlayer interface and uses AdvancedMediaPlayer 
 * objects to play the required format.
 */
public class MediaAdapter implements MediaPlayer {
	//uses AdvancedMediaPlayer objects to play the required format.
	AdvancedMediaPlayer advancedMusicPlayer;

	public MediaAdapter(String audioType) {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer = new VlcPlayer();
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer = new Mp4Player();
		}
	}

	@Override
	public void play(String audioType, String fileName) throws Exception {
		if (audioType.equalsIgnoreCase("vlc")) {
			advancedMusicPlayer.playVlc(fileName);
		} else if (audioType.equalsIgnoreCase("mp4")) {
			advancedMusicPlayer.playMp4(fileName);
		}
	}

}
