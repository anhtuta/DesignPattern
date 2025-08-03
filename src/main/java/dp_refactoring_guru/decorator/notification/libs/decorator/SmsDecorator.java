package dp_refactoring_guru.decorator.notification.libs.decorator;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.impl.SmsNotifier;

public class SmsDecorator extends BaseNotifier {

    private final SmsNotifier smsNotifier;

    public SmsDecorator(Notifier notifier) {
        super(notifier);
        smsNotifier = new SmsNotifier(notifier.getRecipients());
    }

    @Override
    public void send(String message) {
        super.send(message);
        extra(message);
    }

    private void extra(String message) {
        smsNotifier.send(message);
    }
}
