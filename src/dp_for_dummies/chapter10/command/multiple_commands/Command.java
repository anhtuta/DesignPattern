package dp_for_dummies.chapter10.command.multiple_commands;

// Interface dùng để thực thi 1 tập các action
public interface Command {
    // Command execute: dùng để thực thi 1 tập các action theo 1 trình tự nhất định
    // (class con sẽ tự implement tùy ý), user ko cần phải call manually
    // từng action một nữa, như vậy có thể tránh được call nhầm hoặc call ko đúng trình tự
    public void execute();

    // Command undo: undo các action vừa thực thi ở command execute
    public void undo();
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

    @Override
    public void undo() {
        System.out.println("Undoing shutdown command...");
        receiver.connect();
        receiver.reboot();
        receiver.disconnect();
        System.out.println("Undoing successfully!\n");
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

    @Override
    public void undo() {
        System.out.println("Cannot undo diagnostic command!!!\n");
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

    @Override
    public void undo() {
        System.out.println("Undoing reboot command...");
        receiver.connect();
        receiver.shutdown();
        receiver.disconnect();
        System.out.println("Undoing successfully!\n");
    }
}

