package dp_refactoring_guru.state.user_registration.transition_matrix_enum;

public class UserService {
    public void approvalUser(User user, boolean approved) {
        // 1. Parse current state string from DB into the Enum
        RegistrationState currentState = RegistrationState.valueOf(user.getState());

        // 2. Determine target next state based on business logic
        RegistrationState nextState = approved ? RegistrationState.REGISTER_SUCCESS : RegistrationState.REGISTER_REJECTED;

        // 3. One line checks the Enum matrix. Throws Exception automatically if illegal!
        currentState.validateTransitionTo(nextState);

        // 4. If valid, proceed with your existing logic safely
        user.setState(nextState.name());
        user.setApproved(approved);
        user.save();
    }
}
