package design_pattern.creational.prototype;

public class Test {

    public static void main(String[] args) {
        Address address = new Address("Số 456 Nguyễn Trãi", "Hà Đông", "Hà Nội");
        User user1 = new User("Anh Tu", "Ta", "Tuzaku", "tuzaku@yahoo.com", address);
        System.out.println(user1);

        User user2 = user1.shallowCopy();
        address.setStreet("Số 10 Trần Phú");

        System.out.println("\nShallow copy, Address của cả 2 user đều bị thay đổi:");
        System.out.println(user1);
        System.out.println(user2);

        User user3 = user1.deepCopy();
        address.setStreet("Số 1 Quang Trung");

        System.out.println("\nDeep copy, chỉ có address của user3 bị thay đổi:");
        System.out.println(user1);
        System.out.println(user3);
    }

}
