package dp_refactoring_guru.proxy.youtube_cache;

public class YouTubeManager {

    private final ThirdPartyYouTubeLib service;

    public YouTubeManager(ThirdPartyYouTubeLib service) {
        this.service = service;
    }

    public void renderVideoPage(String id) {
        Video video = service.getVideoInfo(id);
        System.out.println("[manager] Rendering video page...");
        System.out.println("[manager] Video: " + video);
    }

    public void renderListPanel() {
        System.out.println("[manager] Rendering list panel...");
        service.listVideos().forEach(System.out::println);
    }

    public void reactOnUserInput(String id) {
        renderVideoPage(id);
        renderListPanel();
    }
}
