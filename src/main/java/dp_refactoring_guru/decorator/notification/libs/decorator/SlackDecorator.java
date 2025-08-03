package dp_refactoring_guru.decorator.notification.libs.decorator;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.impl.SlackNotifier;

public class SlackDecorator extends BaseNotifier {

    private final SlackNotifier slackNotifier;

    public SlackDecorator(Notifier notifier) {
        super(notifier);
        slackNotifier = new SlackNotifier(notifier.getRecipients());
    }

    @Override
    public void send(String message) {
        super.send(message);
        extra(message);
    }

    private void extra(String message) {
        slackNotifier.send(message);
    }
}
