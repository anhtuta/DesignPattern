package dp_refactoring_guru.decorator.notification.libs.decorator;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.impl.FacebookNotifier;

public class FacebookDecorator extends BaseNotifier {

    private final FacebookNotifier facebookNotifier;

    public FacebookDecorator(Notifier notifier) {
        super(notifier);
        facebookNotifier = new FacebookNotifier(notifier.getRecipients());
    }

    @Override
    public void send(String message) {
        super.send(message);
        extra(message);
    }

    private void extra(String message) {
        facebookNotifier.send(message);
    }
}
