package design_pattern.structural.adapter.example1;

public class VlcPlayer implements AdvancedMediaPlayer {

	@Override
	public void playVlc(String fileName) {
		System.out.println("Playing vlc file. Name: " + fileName);
	}

	@Override
	public void playMp4(String fileName) throws Exception {
		throw new Exception("VlcPlayer cannot play a mp4 file!");
	}

}
