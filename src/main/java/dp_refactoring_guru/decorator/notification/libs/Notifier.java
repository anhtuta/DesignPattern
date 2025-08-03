package dp_refactoring_guru.decorator.notification.libs;

import java.util.List;

public interface Notifier {
    List<Recipient> getRecipients();
    void send(String message);
}
