package dp_refactoring_guru.state.media_player;

/**
 * Concrete states provide the special implementation for all interface methods.
 * Tên trên website là LockedState.
 */
public class StoppedState extends State {

    StoppedState(Player player) {
        super(player);
        player.setPlaying(false);
    }

    @Override
    public String onStop() {
        if (player.isPlaying()) {
            player.changeState(new ReadyState(player));
            return "Stop playing";
        } else {
            return "Locked... Cannot stop";
        }
    }

    @Override
    public String onPlay() {
        player.changeState(new ReadyState(player));
        return "Ready";
    }

    @Override
    public String onNext() {
        return "Locked... Cannot play next track";
    }

    @Override
    public String onPrevious() {
        return "Locked... Cannot play previous track";
    }
}
