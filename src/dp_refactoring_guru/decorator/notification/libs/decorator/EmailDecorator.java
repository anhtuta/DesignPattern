package dp_refactoring_guru.decorator.notification.libs.decorator;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.impl.EmailNotifier;

public class EmailDecorator extends BaseNotifier {

    // Theo cấu trúc thì ko có field này, hàm extra bên dưới sẽ send notification via email
    // nhưng việc đó giống với bên EmailNotifier nên tạo biến này để call luôn cho đõ lặp code
    private final EmailNotifier emailNotifier;

    public EmailDecorator(Notifier notifier) {
        super(notifier);
        emailNotifier = new EmailNotifier(notifier.getRecipients());
    }

    @Override
    public void send(String message) {
        super.send(message);
        extra(message);
    }

    private void extra(String message) {
        emailNotifier.send(message);
    }
}
