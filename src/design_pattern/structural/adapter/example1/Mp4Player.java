package design_pattern.structural.adapter.example1;

public class Mp4Player implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) throws Exception {
		throw new Exception("Mp4Player cannot play a vlc file!");
	}

	@Override
	public void playMp4(String fileName) {
		System.out.println("Playing mp4 file. Name: "+ fileName);
	}

}
