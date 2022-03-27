package dp_for_dummies.chapter10.command.multiple_commands;

import java.util.Stack;

/**
 * The invoker là class thực thi command, bạn cần phải load command 
 * cần thực thi vào và bắt nó run
 * command đó. Giả sử rằng việc undo command giống như stack, tức là bạn phải undo command gần nhất
 * được thực thi xong mới được phép undo command được thực thi trước đó. Như vậy có thể dùng Stack
 * để lưu các command
 */
public class Invoker {
    private Stack<Command> stackCommands;

    public Invoker() {
        stackCommands = new Stack<>();
    }

    public void setCommand(Command command) {
        stackCommands.add(command);
    }

    // Đổi tên thành execute cho trùng với tên method của Command
    public void execute() {
        stackCommands.peek().execute();
    }

    public void undo() {
        stackCommands.pop().undo();
    }
}
