package dp_refactoring_guru.decorator.notification.libs.impl;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.Recipient;

import java.util.List;

public class SlackNotifier implements Notifier {

    private final List<Recipient> recipients;

    public SlackNotifier(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public void send(String message) {
        getRecipients()
                .stream()
                .filter(recipient -> recipient.getSlackId() != null)
                .forEach(
                        recipient -> System.out.printf("[Slack] Send notification to %s: %s%n", recipient.getSlackId(), message));
    }
}
