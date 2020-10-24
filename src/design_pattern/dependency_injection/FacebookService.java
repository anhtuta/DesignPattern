package design_pattern.dependency_injection;

public class FacebookService implements MessageService {

	@Override
	public void sendMessage(String message) {
		System.out.println("Send message using Facebook: " + message);
	}

}
