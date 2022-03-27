package dp_for_dummies.chapter10.command;

// Interface dùng để thực thi 1 tập các action
public interface Command {
    // Command execute: dùng để thực thi 1 tập các action theo 1 trình tự nhất định
    // (class con sẽ tự implement tùy ý), user ko cần phải call manually
    // từng action một nữa, như vậy có thể tránh được call nhầm hoặc call ko đúng trình tự
    public void execute();
}

class ShutDownCommand implements Command {
    Receiver receiver;

    public ShutDownCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.connect();
        receiver.shutdown();
        receiver.disconnect();
        System.out.println();
    }
}

class RunDiagnosticsCommand implements Command {
    Receiver receiver;

    public RunDiagnosticsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.connect();
        receiver.diagnostics();
        receiver.disconnect();
        System.out.println();
    }
}

class RebootCommand implements Command {
    Receiver receiver;

    public RebootCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.connect();
        receiver.reboot();
        receiver.disconnect();
        System.out.println();
    }
}

