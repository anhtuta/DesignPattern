# How to apply state pattern for a web app?

How to apply state pattern for a long, multi-request asynchronous business workflow?

In state pattern, we need to create a concrete class for each state, right?

Suppose we have lots of states for the flow of user registration, how can we use state pattern to manage all of them? Here are states for user registration flow:

- User enters email and password, then clicks "Register" button, state = "REGISTER_START"
- System checks if email is valid, state = "CHECKING_EMAIL"
  - If email is invalid, state = "EMAIL_INVALID"
  - If email is valid but exists, state = "EMAIL_EXISTS"
- System checks if password is valid, state = "CHECKING_PASSWORD"
  - If password is invalid, state = "PASSWORD_INVALID"
- If email and password are valid, system creates user account, and sends verification email, state = "WAITING_FOR_CONFIRMATION"
- User clicks the link in verification email, state = "WAITING_FOR_APPROVAL"
  - Admin approves the registration, state = "REGISTER_SUCCESS"
  - Admin rejects the registration, state = "REGISTER_REJECTED"
- If any error occurs during the process, state = "FAIL_BY_SYSTEM"

## Copilot's answer

For your Spring Boot registration flow, the main shift is this:

State pattern in web apps is usually not about keeping an in-memory object alive.  
It is about modeling a persisted workflow where each request/event moves one aggregate from one state to another safely.

Here are practical solution options you can choose from.

### Option 1: Enum State + Transition Service (Recommended starting point)

- Model `RegistrationState` as enum in DB.
- Keep transition rules in one domain service, for example `canTransition(from, event) -> to`.
- Each API call or async callback emits an event like `EMAIL_VALIDATED`, `PASSWORD_INVALID`, `USER_CONFIRMED`, `ADMIN_APPROVED`.
- Service validates transition and updates state atomically.

Good for:

- Most CRUD-ish business systems.
- Team wants simplicity and clear control.

Pros:

- Easy to read and debug.
- Minimal framework overhead.
- Works well with Spring Data + transactions.

Cons:

- Transition logic can become large if flow keeps growing.
- Less “pure OO State pattern” feeling.

### Option 2: Class-per-State Handlers (Classic State pattern adapted to backend)

- Keep state persisted as enum/string in DB.
- Map each state to a handler bean (`StateHandler`) in Spring.
- Runtime picks current handler by state and delegates behavior.
- Handlers decide allowed next states and side effects.

Good for:

- Many state-specific behaviors.
- You want clean separation and OCP-friendly design.

Pros:

- Very close to State pattern intent.
- Easy to add new states without editing giant `if/switch`.
- Better modularity than one big service.

Cons:

- More classes and wiring.
- Can be overkill if flow is still small.

### Option 3: Transition Table + Guard/Action Pipeline (Rule-driven state machine)

- Store transitions in config/table:
  - `from_state`, `event`, `guard`, `to_state`, `action`.
- Engine resolves transition dynamically.
- Guards are reusable business checks.
- Actions trigger side effects (send email, notify admin, etc.).

Good for:

- Flows that change often.
- Product/ops wants configurable behavior.

Pros:

- Very flexible.
- Clear auditable transition map.
- Great when state count keeps increasing.

Cons:

- More abstraction.
- Harder to debug if over-engineered.

### Option 4: Spring StateMachine framework

- Use Spring Statemachine with persisted state.
- Define states, events, guards, actions declaratively.
- Integrate with DB persistence and async events.

Good for:

- Complex workflow with many branching paths, timers, retries.

Pros:

- Built for this exact problem.
- Built-in concepts for guards/actions/interceptors.

Cons:

- Learning curve.
- Framework complexity may feel heavy for small flows.

### Option 5: Orchestrated Workflow/Saga style (for distributed/multi-service)

- Keep registration as a process managed by an orchestrator/service.
- Every step emits domain events; compensations/retries handled explicitly.
- Combine with outbox + message broker for reliability.

Good for:

- Multi-service flow (identity, email, admin portal, risk engine).
- Strong reliability and traceability needs.

Pros:

- Resilient for long-running async workflows.
- Handles failures/retries well.

Cons:

- Highest complexity.
- Needs messaging and operational maturity.

**What to always include regardless of option**

1. Persist state in DB per registration aggregate.
2. Enforce valid transitions (`from + event -> to`) centrally.
3. Make transitions idempotent (same callback/event can arrive twice).
4. Use optimistic locking/versioning to prevent race conditions.
5. Separate “state change” from “side effects” via outbox/events.
6. Add timeout states, for example stale confirmation.
7. Keep audit trail of transitions (`who`, `when`, `event`, `from`, `to`, `reason`).

**How your listed states fit**
Your flow is a textbook finite-state workflow:

- Start: `REGISTER_START`
- Validation phases: `CHECKING_EMAIL`, `CHECKING_PASSWORD`
- Business outcomes: `EMAIL_INVALID`, `EMAIL_EXISTS`, `PASSWORD_INVALID`
- Long wait points: `WAITING_FOR_CONFIRMATION`, `WAITING_FOR_APPROVAL`
- Terminal states: `REGISTER_SUCCESS`, `REGISTER_REJECTED`, `FAIL_BY_SYSTEM`

The key for web apps is that wait points must survive restarts and delayed events, so state must be durable and transition-safe.

**Practical recommendation path**

1. Start with Option 1 (enum + transition service) to deliver quickly.
2. If logic per state gets crowded, refactor to Option 2 (handler per state).
3. If workflow becomes highly dynamic or cross-service, move toward Option 4 or 5.

## Google AI's answer

The Secret: Stateless State Classes

Instead of storing user data inside the state objects, we make the state classes stateless singletons (or Spring Beans). We pass the database data (`RegistrationContext`) into the state methods.

### 1. The State Interface

Every state class must implement this interface. It defines the entry points for external multi-request actions.

```java
public interface RegistrationState {
    // Action 1: User hits submit (handles validations)
    void process(RegistrationContext context);

    // Action 2: User clicks email link
    void confirmEmail(RegistrationContext context);

    // Action 3: Admin clicks Approve/Reject
    void reviewAdmin(RegistrationContext context, boolean approved);

    // A string identifier so we can save it to the DB
    String getName();
}
```

### 2. The Context Object (The Database Entity)

This class keeps the state name string. When an action occurs, it resolves that string into the concrete State object.

```java
public class RegistrationContext {
    private String id;
    private String email;
    private String password;
    private String stateName; // Saved to DB (e.g., "CHECK_EMAIL")

    // The current active state class instance
    private transient RegistrationState currentState;

    public RegistrationContext() {
        this.changeState(new RegisterStartState());
    }

    public void changeState(RegistrationState newState) {
        this.currentState = newState;
        this.stateName = newState.getName();
    }

    // Delegate methods directly to the active state class (NO SWITCH STATEMENTS)
    public void process() { currentState.process(this); }
    public void confirmEmail() { currentState.confirmEmail(this); }
    public void reviewAdmin(boolean approved) { currentState.reviewAdmin(this, approved); }

    // Getters and Setters for database fields...
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getStateName() { return stateName; }
    public void setStateName(String stateName) { this.stateName = stateName; }
}
```

### 3. Dedicated Concrete State Classes (No Switch Statements!)

Each class handles its own isolation logic and manually advances the context to the next state class.

```java
// State 1: Start
public class RegisterStartState implements RegistrationState {
    @Override
    public void process(RegistrationContext ctx) {
        ctx.changeState(new CheckEmailState());
        ctx.process(); // Chain automatically to the next step
    }
    @Override public void confirmEmail(RegistrationContext c) {}
    @Override public void reviewAdmin(RegistrationContext c, boolean a) {}
    @Override public String getName() { return "REGISTER_START"; }
}
// State 2: Check Email
public class CheckEmailState implements RegistrationState {
    @Override
    public void process(RegistrationContext ctx) {
        if (!ctx.getEmail().contains("@")) {
            ctx.changeState(new EmailInvalidState());
        } else if ("taken@test.com".equals(ctx.getEmail())) {
            ctx.changeState(new EmailExistsState());
        } else {
            ctx.changeState(new CheckPasswordState());
            ctx.process(); // Pass to next step automatically
        }
    }
    @Override public void confirmEmail(RegistrationContext c) {}
    @Override public void reviewAdmin(RegistrationContext c, boolean a) {}
    @Override public String getName() { return "CHECK_EMAIL"; }
}
// State 3: Waiting for Confirmation (Multi-request pause point)
public class WaitingConfirmationState implements RegistrationState {
    @Override public void process(RegistrationContext ctx) {} // Idle state

    @Override
    public void confirmEmail(RegistrationContext ctx) {
        // Only valid if clicked while in this state!
        ctx.changeState(new WaitingApprovalState());
        System.out.println("Email verified successfully.");
    }

    @Override public void reviewAdmin(RegistrationContext c, boolean a) {}
    @Override public String getName() { return "WAITING_FOR_CONFIRMATION"; }
}
// State 4: Terminal Error State
public class EmailInvalidState implements RegistrationState {
    @Override public void process(RegistrationContext ctx) { System.out.println("Error: Email is invalid."); }
    @Override public void confirmEmail(RegistrationContext c) {}
    @Override public void reviewAdmin(RegistrationContext c, boolean a) {}
    @Override public String getName() { return "EMAIL_INVALID"; }
}// Note: You would continue creating separate classes for CheckPasswordState, WaitingApprovalState, etc.
```

### How the Registry Factory Resolves DB Data to Classes

To prevent keeping the objects in server memory across requests, we use a simple lookup Registry to convert the string stored in the database back into the concrete state object whenever a request comes in.

```java
import java.util.HashMap;
import java.util.Map;
public class StateRegistry {
    private static final Map<String, RegistrationState> registry = new HashMap<>();

    static {
        registry.put("REGISTER_START", new RegisterStartState());
        registry.put("CHECK_EMAIL", new CheckEmailState());
        registry.put("WAITING_FOR_CONFIRMATION", new WaitingConfirmationState());
        registry.put("EMAIL_INVALID", new EmailInvalidState());
        // Map all your classes here...
    }

    public static RegistrationState getState(String name) {
        return registry.getOrDefault(name, new RegisterStartState());
    }
}
```

### How the Flow Executes in a Multi-Request Web App

When a request hits your REST API, the controller pattern looks like this:

```java
public class RegistrationService {
    private RegistrationRepository repo = new RegistrationRepository();

    // EndPoint 1: POST /api/register
    public void registerUser(String email, String password) {
        RegistrationContext ctx = new RegistrationContext();
        ctx.setEmail(email);
        ctx.setPassword(password);

        ctx.process(); // Automatically travels through Start -> CheckEmail -> CheckPassword
        repo.save(ctx); // Saves string stateName: "WAITING_FOR_CONFIRMATION"
    }

    // EndPoint 2: GET /api/confirm?id=123 (Triggers hours later)
    public void confirmUserEmail(String registrationId) {
        // 1. Fetch data from DB
        RegistrationContext ctx = repo.findById(registrationId);

        // 2. Hydrate the correct class dynamically using the registry
        RegistrationState stateClass = StateRegistry.getState(ctx.getStateName());
        ctx.changeState(stateClass);

        // 3. Execute logic (Zero conditional switch logic here)
        ctx.confirmEmail();

        // 4. Save updated state string back to database ("WAITING_FOR_APPROVAL")
        repo.save(ctx);
    }
}
```

### Why this follows the true pattern:

1.  Zero Switch Statements: The code relies purely on Polymorphism. The context simply calls ctx.confirmEmail(). If the user is in EmailInvalidState, nothing happens because the method is empty. If they are in WaitingConfirmationState, the behavior executes.
2.  Encapsulated Behavior: The knowledge of "what state comes next" belongs entirely inside the individual state classes, satisfying the explicit intent of the pattern.

# How to only use transition map without applying state pattern to existing code?

Suppose I just want to manage the state transitions, so others when reading the code can easily understand which state comes from which state. We've already implemented the business logic for each API, and we're storing state in database, in state column.
inside each API, we did set state when processing the logic, something like

```java
void approvalUser(User user) {
    if (user.getState() != WAITING_FOR_APPROVAL) {
        throw new Exception...
    }
    user.setState(REGISTER_SUCCESS)
    user.setIsApproved(true)
    user.save()
}
```

Now, if we apply state pattern to our code, is this too complicated and overhead?
Is there any simple solution to move the state transition into another place, so everyone just needs to go there and understands which is the next state from a given state, and which transition is not allowed?

## Copilot's answer

Yes, for your current goal, full State pattern (one class per state) is likely overhead.

Your requirement is simpler:

1. Keep business logic where it is.
2. Centralize and validate transitions in one place.
3. Make allowed and forbidden transitions obvious to readers.

Best fit: a **lightweight Transition Registry** (state table), not full State pattern.

1. Create enums

- RegistrationState: REGISTER_START, CHECKING_EMAIL, WAITING_FOR_APPROVAL, etc.
- RegistrationEvent: EMAIL_VALID, EMAIL_INVALID, APPROVE, REJECT, SYSTEM_ERROR, etc.
  Using events makes intent clearer than jumping directly from state to state.

2. **Put transition rules in one class**

- One map/table that defines from + event -> to.
- Any missing pair means forbidden transition.

Example shape:

```java
public final class RegistrationTransitions {
    private static final Map<RegistrationState, Map<RegistrationEvent, RegistrationState>> RULES = Map.of(
        REGISTER_START, Map.of(SUBMIT, CHECKING_EMAIL),
        CHECKING_EMAIL, Map.of(EMAIL_INVALID, EMAIL_INVALID, EMAIL_EXISTS, EMAIL_EXISTS, EMAIL_OK, CHECKING_PASSWORD),
        CHECKING_PASSWORD, Map.of(PASSWORD_INVALID, PASSWORD_INVALID, PASSWORD_OK, WAITING_FOR_CONFIRMATION),
        WAITING_FOR_CONFIRMATION, Map.of(USER_CONFIRMED, WAITING_FOR_APPROVAL),
        WAITING_FOR_APPROVAL, Map.of(APPROVE, REGISTER_SUCCESS, REJECT, REGISTER_REJECTED)
    );

    public static RegistrationState next(RegistrationState from, RegistrationEvent event) {
        RegistrationState to = RULES.getOrDefault(from, Map.of()).get(event);
        if (to == null) throw new IllegalStateException("Forbidden transition: " + from + " --" + event + "-->");
        return to;
    }

    public static boolean canTransition(RegistrationState from, RegistrationEvent event) {
        return RULES.getOrDefault(from, Map.of()).containsKey(event);
    }
}
```

3. Use it inside existing APIs

- Keep your API logic.
- Replace direct `setState(...)` with:
  - `next = RegistrationTransitions.next(currentState, APPROVE)`
  - `setState(next)`

So your approval API becomes conceptually:

- validate domain conditions
- compute next state from central transition table
- persist

4. Why this is ideal for your case

- Very low complexity.
- One source of truth for transitions.
- Easy for new devs to read.
- Easy to test with a single transition test suite.
- No large refactor of current business logic.

5. Optional small upgrades
1. Add a method to print all transitions for docs/logs.
1. Add terminal state policy (forbid all except maybe SYSTEM_ERROR).
1. Add optimistic locking/version on user row to prevent race updates.
1. If rules grow a lot, move table to config (YAML/JSON) but keep same validator API.

Short answer: **do not jump to full State pattern now**. Use a central transition matrix/registry first. It gives almost all readability benefits with minimal overhead.

## Google AI's answer

Yes, you are 100% correct. If your business logic is already written inside your API handlers, **refactoring everything into the true GoF State Pattern would add massive, unnecessary complexity and overhead**.

The classic State Pattern is meant to encapsulate behavior that changes based on state.

If your only goal is to centralize and visualize allowed transitions (e.g., State A → State B), you don't need a behavioral design pattern — you need a **Declarative State Machine Pattern**.

To keep it clean, simple, and in one readable place without using huge nested if-else blocks or breaking your existing API logic, you can use a **Transition Map Matrix** inside your Enum [Java Enum](https://www.geeksforgeeks.org/system-design/state-design-pattern-in-java/).

### The Simple Solution: An Enum-Based Transition Matrix

Instead of creating 11 classes, you define the entire workflow's routing rules directly inside your RegistrationState enum using a Set or list of allowed next states.

This creates a single source of truth that anyone can read in 10 seconds to understand the entire workflow.

```java
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
                String.format("Illegal Transition: Cannot move from state [%s] to [%s]", this.name(), nextState.name())
            );
        }
    }
}
```

### How to Cleanly Integrate it Into Your Existing APIs

You do not need to change how your APIs execute their database logic. You simply add a single call to the validateTransitionTo() guard method right before saving.

This ensures consistency and centralizes safety checks, preventing accidental state skips.

```java
void approvalUser(User user, boolean approved) {
    // 1. Parse current state string from DB into the Enum
    RegistrationState currentState = RegistrationState.valueOf(user.getState());

    // 2. Determine target next state based on business logic
    RegistrationState nextState = approved ? RegistrationState.REGISTER_SUCCESS : RegistrationState.REGISTER_REJECTED;

    // 3. One line checks the Enum matrix. Throws Exception automatically if illegal!
    currentState.validateTransitionTo(nextState);

    // 4. If valid, proceed with your existing logic safely
    user.setState(nextState.name());
    user.setIsApproved(approved);
    user.save();
}
```

## Why this addresses your exact problem:

1.  Zero Overhead: You don't create a single extra class, interface, or factory.
2.  Perfect Readability: The entire system workflow architecture is written as plain configuration text at the top of the RegistrationState.java file. Anyone looking at the code can immediately tell that WAITING_FOR_APPROVAL can only transition to REGISTER_SUCCESS, REGISTER_REJECTED, or FAIL_BY_SYSTEM.
3.  No If-Else Mess: You completely eliminate defensive code blocks like `if (user.getState() != WAITING_FOR_APPROVAL)` from your API endpoints. The enum checks it automatically.
