package dp_for_dummies.chapter9.state.encapsulate_state;


// Sau này nếu hệ thống cần thêm mới state, chỉ cần tạo class mới implement interface này
interface AutomatInterface {
    public void gotApplication();

    public void checkApplication();

    public void rentApartment();

    public void setState(State state);

    public State getWaitingState();

    public State getGotApplicationState();

    public State getApartmentRentedState();

    public State getFullyRentedState();

    // gets the current number of apartments for rent
    public int getCount();

    // sets the current number of apartments for rent
    public void setCount(int count);
}


class RentalAutomat implements AutomatInterface {

    private State waitingState;
    private State gotApplicationState;
    private State apartmentRentedState;
    private State fullyRentedState;
    private State state;
    private int count;

    public RentalAutomat(int n) {
        count = n;
        waitingState = new WaitingState(this);
        gotApplicationState = new GotApplicationState(this);
        apartmentRentedState = new ApartmentRentedState(this);
        waitingState = new WaitingState(this);
        state = waitingState;
    }

    @Override
    public void gotApplication() {
        System.out.println(state.gotApplication());
    }

    @Override
    public void checkApplication() {
        System.out.println(state.checkApplication());
    }

    @Override
    public void rentApartment() {
        System.out.println(state.rentApartment());
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    @Override
    public State getWaitingState() {
        return waitingState;
    }

    @Override
    public State getGotApplicationState() {
        return gotApplicationState;
    }

    @Override
    public State getApartmentRentedState() {
        return apartmentRentedState;
    }

    @Override
    public State getFullyRentedState() {
        return fullyRentedState;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}


// TODO: Solution này chưa rõ ràng lắm, cần làm lại!
public class RentalAutomatSystem {

    public static void main(String[] args) {
        AutomatInterface automat = new RentalAutomat(9);
        automat.gotApplication();
        automat.checkApplication();
        automat.rentApartment();
    }
}
