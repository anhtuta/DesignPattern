package dp_for_dummies.chapter9.state;

import java.util.Random;

class RentalAutomat {
    private final static int WAITING = 0;
    private final static int GOT_APPLICATION = 1;
    private final static int APARTMENT_RENTED = 2;
    private final static int FULLY_RENTED = 3;
    private Random random;
    private int numberApartments;
    private int state;

    public RentalAutomat(int n) {
        numberApartments = n;
        random = new Random(System.currentTimeMillis());
        state = WAITING;
    }

    // Hệ thống nhận đơn xin thuê nhà
    public void receiveApplication() {
        switch (state) {
            case FULLY_RENTED:
                System.out.println("Sorry, we’re fully rented.");
                break;
            case WAITING:
                state = GOT_APPLICATION;
                System.out.println("Thanks for the application.");
                break;
            case GOT_APPLICATION:
                System.out.println("We already got your application.");
                break;
            case APARTMENT_RENTED:
                System.out.println("Hang on, we’re renting you an apartment.");
                break;
        }
    }

    // Hệ thống xử lý đơn xin thuê nhà
    public void processApplication() {
        int yesno = random.nextInt() % 10;
        switch (state) {
            case FULLY_RENTED:
                System.out.println("Sorry, we’re fully rented.");
                break;
            case WAITING:
                System.out.println("You have to submit an application.");
                break;
            case GOT_APPLICATION:
                if (yesno > 4 && numberApartments > 0) {
                    System.out.println("Congratulations, you were approved.");
                    state = APARTMENT_RENTED;
                    rentApartment();
                } else {
                    System.out.println("Sorry, you were not approved.");
                    state = WAITING;
                }
                break;
            case APARTMENT_RENTED:
                System.out.println("Hang on, we’re renting you an apartment.");
                break;
        }
    }

    // Hệ thống thực hiện việc cho thuê nhà
    private void rentApartment() {
        switch (state) {
            case FULLY_RENTED:
                System.out.println("Sorry, we’re fully rented.");
                break;
            case WAITING:
                System.out.println("You have to submit an application.");
                break;
            case GOT_APPLICATION:
                System.out.println("You must have your application checked.");
                break;
            case APARTMENT_RENTED:
                System.out.println("Renting you an apartment....");
                numberApartments--;
                dispenseKeys();
                break;
        }
    }

    // Giao chìa khóa cho người thuê
    private void dispenseKeys() {
        switch (state) {
            case FULLY_RENTED:
                System.out.println("Sorry, we’re fully rented.");
                break;
            case WAITING:
                System.out.println("You have to submit an application.");
                break;
            case GOT_APPLICATION:
                System.out.println("You must have your application checked.");
                break;
            case APARTMENT_RENTED:
                System.out.println("Here are your keys!");
                state = WAITING;
                break;
        }
    }
}


/*
 * Hệ thống trên lưu mỗi state dưới 1 biến constant, có điều nếu sau này cần thêm mới state thì lại
 * phải sửa TOÀN BỘ code cũ (cả 4 method ở trên) => vi phạm nguyên lý **Open–closed principle**
 */
public class RentalAutomatSystem {
    public static void main(String[] args) {
        RentalAutomat rentalAutomat = new RentalAutomat(9);
        rentalAutomat.receiveApplication();
        rentalAutomat.processApplication();
    }
}
