package dp_refactoring_guru.decorator.notification;

import dp_refactoring_guru.decorator.notification.libs.Notifier;
import dp_refactoring_guru.decorator.notification.libs.Recipient;
import dp_refactoring_guru.decorator.notification.libs.decorator.EmailDecorator;
import dp_refactoring_guru.decorator.notification.libs.decorator.FacebookDecorator;
import dp_refactoring_guru.decorator.notification.libs.decorator.SlackDecorator;
import dp_refactoring_guru.decorator.notification.libs.decorator.SmsDecorator;
import dp_refactoring_guru.decorator.notification.libs.impl.EmailNotifier;
import dp_refactoring_guru.decorator.notification.libs.impl.SlackNotifier;
import dp_refactoring_guru.decorator.notification.libs.impl.SmsNotifier;

import java.util.List;

public class Application {

    private static List<Recipient> buildRecipients() {
        Recipient r1 = new Recipient();
        r1.setName("Goku");
        r1.setEmail("goku@gmail.com");
        r1.setPhone("1234567890");
        r1.setSlackId("sl-aaa-123-456");
        r1.setFacebookId("fb-bbb-987-321");
        Recipient r2 = new Recipient();
        r2.setName("Vegeta");
        r2.setEmail("vegeta@yahoo.com");
        r2.setPhone("0987654321");
        r2.setFacebookId("fb-ccc-654-321");
        return List.of(r1, r2);
    }

    public static void main(String[] args) {
        //===== Old way, must create all the notifiers and send notifications one by one.
        String message = "Hello there!";
        List<Recipient> recipients = buildRecipients();

        Notifier notifier1 = new EmailNotifier(recipients);
        Notifier notifier2 = new SmsNotifier(recipients);
        Notifier notifier3 = new SlackNotifier(recipients);
        notifier1.send(message);
        notifier2.send(message);
        notifier3.send(message);

        //===== Using decorator =====
        System.out.println("\n===== Using decorator 1 =====\n");
        Notifier stack1 = new EmailNotifier(recipients);
        SmsDecorator smsDecorator = new SmsDecorator(stack1);
        SlackDecorator slackDecorator = new SlackDecorator(smsDecorator);
        // Email -> SMS -> Slack
        slackDecorator.send(message);

        System.out.println("\n===== Using decorator 2 =====\n");
        Notifier stack2 = new SlackNotifier(recipients);
        FacebookDecorator facebookDecorator = new FacebookDecorator(stack2);
        EmailDecorator emailDecorator = new EmailDecorator(facebookDecorator);
        SmsDecorator smsDecorator1 = new SmsDecorator(emailDecorator);
        // Slack -> Facebook -> Email -> SNS
        smsDecorator1.send(message);

        // Re-write it, now we can bring stack3 to everywhere and send notifications,
        // without initializing all the decorators again.
        System.out.println("\n===== Using decorator 3 =====\n");
        Notifier stack3 = new SmsDecorator(new EmailDecorator(new FacebookDecorator(new SlackNotifier(recipients))));
        // Slack -> Facebook -> Email -> SNS
        stack3.send(message);

        // Add more stack, just for testing...
        System.out.println("\n===== Using decorator 4 =====\n");
        Notifier stack4 = new EmailDecorator(new FacebookDecorator(stack3));
        // Slack -> Facebook -> Email -> SNS -> Facebook -> Email
        stack4.send(message);
    }
}
