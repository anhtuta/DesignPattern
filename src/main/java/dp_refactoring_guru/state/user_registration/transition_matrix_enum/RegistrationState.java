package dp_refactoring_guru.state.user_registration.transition_matrix_enum;

import java.util.Set;

public enum RegistrationState {
    // 1. Define states and list EXACTLY which states they are allowed to transition into
    REGISTER_START(Set.of("CHECK_EMAIL", "FAIL_BY_SYSTEM")),

    CHECK_EMAIL(Set.of("EMAIL_INVALID", "EMAIL_EXISTS", "CHECK_PASSWORD", "FAIL_BY_SYSTEM")),

    CHECK_PASSWORD(Set.of("PASSWORD_INVALID", "WAITING_FOR_CONFIRMATION", "FAIL_BY_SYSTEM")),

    WAITING_FOR_CONFIRMATION(Set.of("WAITING_FOR_APPROVAL", "FAIL_BY_SYSTEM")),

    WAITING_FOR_APPROVAL(Set.of("REGISTER_SUCCESS", "REGISTER_REJECTED", "FAIL_BY_SYSTEM")),

    // Terminal States (cannot transition anywhere else)
    EMAIL_INVALID(Set.of()),
    EMAIL_EXISTS(Set.of()),
    PASSWORD_INVALID(Set.of()),
    REGISTER_SUCCESS(Set.of()),
    REGISTER_REJECTED(Set.of()),
    FAIL_BY_SYSTEM(Set.of());

    // Internal set storing the names of valid next states
    private final Set<String> nextAllowedStates;

    RegistrationState(Set<String> nextAllowedStates) {
        this.nextAllowedStates = nextAllowedStates;
    }

    /**
     * The Central Validation Guard.
     * Throws an exception if anyone tries to make an illegal jump.
     */
    public void validateTransitionTo(RegistrationState nextState) {
        if (!this.nextAllowedStates.contains(nextState.name())) {
            throw new IllegalStateException(
                    String.format("Illegal Transition: Cannot move from state [%s] to [%s]", this.name(), nextState.name()));
        }
    }
}
