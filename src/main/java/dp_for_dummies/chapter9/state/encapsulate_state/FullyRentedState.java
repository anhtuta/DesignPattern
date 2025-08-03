package dp_for_dummies.chapter9.state.encapsulate_state;

public class FullyRentedState implements State {

    AutomatInterface automat;

    public FullyRentedState(AutomatInterface a) {
        automat = a;
    }

    @Override
    public String gotApplication() {
        return "Sorry, we’re fully rented.";
    }

    @Override
    public String checkApplication() {
        return "Sorry, we’re fully rented.";
    }

    @Override
    public String rentApartment() {
        return "Sorry, we’re fully rented.";
    }

    @Override
    public String dispenseKeys() {
        return "Sorry, we’re fully rented.";
    }

}
