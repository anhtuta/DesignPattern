package design_pattern.dependency_injection;

public class UserController {

	private MessageService messageService;

	public UserController(MessageService messageService) {
		this.messageService = messageService;
	}

	public void sendMessage(String message) {
		messageService.sendMessage(message);
	}
}
