package dp_refactoring_guru.proxy.youtube_cache;

import java.util.List;

/**
 * This is the Proxy class.
 * It implements the same interface as the Service class, so it can be used as a replacement for it.
 * The Proxy class is used to add caching functionality to the Service class.
 * The Proxy class has a reference to the Service class and delegates the calls to it.
 * The Proxy class can also add additional functionality, such as caching, logging, etc.
 * In this example, the CachedYouTubeClass adds caching functionality to the ThirdPartyYouTubeClass.
 * Túm lại thì: cái proxy này sẽ check cache trước, nếu cache có data thì return luôn,
 * không thì call real object để fetch data
 */
public class CachedYouTubeClass implements ThirdPartyYouTubeLib {
    private final ThirdPartyYouTubeLib service; // target object
    private List<Video> listCache;
    private Video videoCache;
    private final boolean needReset = false;

    public CachedYouTubeClass(ThirdPartyYouTubeLib service) {
        this.service = service;
    }

    @Override
    public List<Video> listVideos() {
        if (listCache == null || needReset) {
            System.out.println("[proxy] Cannot find videos from cache, call real object to fetch them...");
            listCache = service.listVideos();
        }
        return listCache;
    }

    @Override
    public Video getVideoInfo(String id) {
        if (videoCache == null || needReset) {
            System.out.println("[proxy] Cannot find video info from cache, call real object to fetch it...");
            videoCache = service.getVideoInfo(id);
        }
        return videoCache;
    }

    @Override
    public Video downloadVideo(String id) {
        return service.downloadVideo(id);
    }
}
