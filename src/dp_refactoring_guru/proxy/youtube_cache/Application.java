package dp_refactoring_guru.proxy.youtube_cache;

public class Application {
    public static void main(String[] args) {
        ThirdPartyYouTubeLib target = new ThirdPartyYouTubeClass();
        ThirdPartyYouTubeLib proxy = new CachedYouTubeClass(target);
        YouTubeManager manager = new YouTubeManager(proxy);
        // The first time, the proxy object will call the target object to fetch the data
        manager.reactOnUserInput("1");
        System.out.println("=====================================");
        // The second time, the proxy object will use the cached data
        manager.reactOnUserInput("1");
    }
}
