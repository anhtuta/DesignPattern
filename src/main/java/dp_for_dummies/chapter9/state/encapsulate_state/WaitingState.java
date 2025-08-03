package dp_for_dummies.chapter9.state.encapsulate_state;

public class WaitingState implements State {
    AutomatInterface automat;

    public WaitingState(AutomatInterface automat) {
        this.automat = automat;
    }

    @Override
    public String gotApplication() {
        automat.setState(automat.getGotApplicationState());
        return "Thanks for the application.";
    }

    @Override
    public String checkApplication() {
        return "You have to submit an application.";
    }

    @Override
    public String rentApartment() {
        return "You have to submit an application.";
    }

    @Override
    public String dispenseKeys() {
        return "You have to submit an application.";
    }
}
