package dp_refactoring_guru.decorator.notification.libs.impl;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.Recipient;

import java.util.List;

public class FacebookNotifier implements Notifier {

    private final List<Recipient> recipients;

    public FacebookNotifier(List<Recipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public void send(String message) {
        getRecipients().forEach(
                recipient -> System.out.printf("[Facebook] Send notification to %s: %s%n", recipient.getFacebookId(), message));
    }
}
