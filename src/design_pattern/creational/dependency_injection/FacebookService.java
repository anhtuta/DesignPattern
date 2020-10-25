package design_pattern.creational.dependency_injection;

public class FacebookService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("Send message using Facebook: " + message);
    }

}
