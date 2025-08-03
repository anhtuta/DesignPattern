package dp_for_dummies.chapter10.command;

// The invoker là class thực thi command,
// bạn cần phải load command cần thực thi vào và bắt nó run command đó
public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void run() {
        command.execute();
    }
}
