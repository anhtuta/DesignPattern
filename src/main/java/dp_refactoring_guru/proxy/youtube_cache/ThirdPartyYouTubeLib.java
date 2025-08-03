package dp_refactoring_guru.proxy.youtube_cache;

import java.util.List;

public interface ThirdPartyYouTubeLib {
    List<Video> listVideos();
    Video getVideoInfo(String id);
    Video downloadVideo(String id);
}
