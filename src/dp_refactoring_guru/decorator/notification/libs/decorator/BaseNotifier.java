package dp_refactoring_guru.decorator.notification.libs.decorator;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.Recipient;

import java.util.List;

/**
 * Thực ra ko cần BaseNotifier cũng được, các decorator có thể implement Notifier trực tiếp,
 * nhưng chúng đều cần một Notifier wrappee để wrap.
 * Còn nữa, các decorator đều phải implement logic cho method send bằng cách delegate cho wrappee
 * (gọi tới method send của wrappee), sau đó làm thêm gì thì tuỳ chúng. Việc delegate cho wrappee
 * là giống nhau đối với các decorator (chúng chỉ khác nhau ở bước làm thêm extra gì đó)
 * Do đó, việc tạo ra BaseNotifier để giúp loại bỏ duplicated code ở các concrete decorator ở 2 chỗ sau:
 * - Object wrappee
 * - Delegate logic cho wrappee
 */
public abstract class BaseNotifier implements Notifier {

    // Wrappee
    private final Notifier notifier;

    protected BaseNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Override
    public void send(String message) {
        // Delegate the work to the wrappee
        notifier.send(message);
    }

    @Override
    public List<Recipient> getRecipients() {
        return notifier.getRecipients();
    }
}
