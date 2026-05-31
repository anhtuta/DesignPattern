package dp_refactoring_guru.state.user_registration.transition_matrix_enum;

import lombok.Getter;
import lombok.Setter;

/**
 * A simple User entity to map to User table in the database.
 */
@Getter
@Setter
public class User {
    private String username;
    private String email;
    private String password;
    private String state;
    private boolean isApproved;

    public void save() {
        // Simulate saving the user to a database
        // System.out.println("Saved user with state: " + state);
    }
}
