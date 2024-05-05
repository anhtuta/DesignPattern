package dp_refactoring_guru.decorator.notification.libs.impl;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.Recipient;

import java.util.List;

/**
 * The IntelliJ IDEA suggested to use the record instead of the class.
 * Let it convert the class to the record and see.
 */
public record SmsNotifier(List<Recipient> recipients) implements Notifier {

    @Override
    public List<Recipient> getRecipients() {
        return recipients;
    }

    @Override
    public void send(String message) {
        assert getRecipients() != null;
        getRecipients().forEach(
                recipient -> System.out.printf("[SMS] Send notification to %s: %s%n", recipient.getPhone(), message));
    }
}
