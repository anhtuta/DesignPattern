package dp_for_dummies.chapter4.observer.ob3_pass_reference_using_java_observer;

import java.util.concurrent.ThreadLocalRandom;

public class ObserverPattern {

    private static final String[] DB_OPERATIONS = {"INSERT", "UPDATE", "DELETE", "TRUNCATE"};

    static final String[] LOREM_WORDS = {"lorem", "ipsum", "dolor", "sit", "amet", "consectetur",
            "adipiscing", "elit", "curabitur", "vel", "hendrerit", "libero", "eleifend", "blandit",
            "nunc", "ornare", "odio", "ut", "orci", "gravida", "imperdiet", "nullam", "purus",
            "lacinia", "a", "pretium", "quis", "congue", "praesent", "sagittis", "laoreet"};

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    private static String getRandomInArray(String[] arr) {
        return arr[getRandomInt(0, arr.length - 1)];
    }

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws InterruptedException {
        // Không thể dùng Publisher dbHelper = new DatabasePublisher();
        // Vì interface Publisher làm gì có method editRecord.
        // Cũng ko thể add method editRecord vào interface Publisher,
        // vì method ý chả liên quan đến interface này
        DatabaseHelper dbHelper = new DatabaseHelper();
        Developer dev = new Developer();
        ProjectLeader leader = new ProjectLeader();
        Boss boss = new Boss();

        dbHelper.addObserver(dev);
        dbHelper.addObserver(leader);
        dbHelper.addObserver(boss);

        for (int i = 1; i <= 10; i++) {
            System.out.println("i = " + i);
            dbHelper.saveToDb(getRandomInArray(DB_OPERATIONS),
                    new Employee(i, getRandomInArray(LOREM_WORDS)));
            Thread.sleep(500);
        }
    }
}
