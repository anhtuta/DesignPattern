package dp_refactoring_guru.proxy.youtube_cache;

import java.util.List;

/**
 * This is the Service class, or target class
 */
public class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    private static final List<Video> mockDataInDb = List.of(
            new Video("1", "Video 1"),
            new Video("2", "Video 2"),
            new Video("3", "Video 3"),
            new Video("4", "Video 4"),
            new Video("5", "Video 5")
    );

    @Override
    public List<Video> listVideos() {
        System.out.println("[target] Fetching videos from database...");
        return mockDataInDb;
    }

    @Override
    public Video getVideoInfo(String id) {
        System.out.println("[target] Getting video info by id: " + id);
        return mockDataInDb.stream()
                .filter(video -> video.id().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Video downloadVideo(String id) {
        System.out.println("[target] Downloading video by id: " + id);
        return mockDataInDb.stream()
                .filter(video -> video.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
