package dp_refactoring_guru.state.user_registration.transition_matrix_enum;

public class Demo {
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user1 = new User();
        user1.setState(RegistrationState.WAITING_FOR_APPROVAL.name());
        User user2 = new User();
        user2.setState(RegistrationState.WAITING_FOR_APPROVAL.name());

        // Approve the user1
        userService.approvalUser(user1, true);
        System.out.println("User state after approval: " + user1.getState());

        // Reject the user1: Error: Cannot move from state [REGISTER_SUCCESS] to [REGISTER_REJECTED]
        // userService.approvalUser(user1, false);
        // System.out.println("User state after rejection: " + user1.getState());

        // Reject the user2: OK
        userService.approvalUser(user2, false);
        System.out.println("User state after rejection: " + user2.getState());
    }
}
