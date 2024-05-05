package dp_refactoring_guru.decorator.notification.libs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Recipient {
    private String name;
    private String email;
    private String phone;
    private String slackId;
    private String facebookId;
}
