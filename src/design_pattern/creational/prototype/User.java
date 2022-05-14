package design_pattern.creational.prototype;

/*
 * Prototype Pattern là một mẫu tạo dựng (Creation Pattern).
 * Prototype Pattern được dùng khi việc tạo một object tốn nhiều
 * chi phí và thời gian trong khi bạn đã có một object tương tự tồn tại.
 * 
 * Prototype Pattern cung cấp cơ chế để copy từ object ban đầu sang
 * object mới và thay đổi giá trị một số thuộc tính nếu cần.
 * 
 * Trong Java cung cấp sẵn mẫu prototype pattern này bằng việc implement
 * interface Cloneable và sử dụng method clone() để copy object.
 * 
 * Vì object cần copy có thể có các object con khác bên trong nên xảy ra 2 trường hợp:
 * Trường hợp 1: Các object con bên trong chỉ được copy reference (tức là object
 * ban đầu và object tạo mới đều trỏ tới chung 1 object con bên trong) (Shallow copy)
 * Trường hợp 2: Các object con bên trong cũng được copy lại toàn bộ các
 * thuộc tính. (Deep copy)
 * 
 * Prototype pattern refers to creating duplicate object while keeping
 * performance in mind. This type of design pattern comes under creational
 * pattern as this pattern provides one of the best ways to create an object.
 * 
 * This pattern involves implementing a prototype interface which tells
 * to create a clone of the current object. This pattern is used when
 * creation of object directly is costly. For example, an object is to
 * be created after a costly database operation. We can cache the object,
 * returns its clone on next request and update the database as and when
 * needed thus reducing database calls.
 */
public class User {
    private String firstName;
    private String lastName;
    private String displayName;
    private String email;
    private Address address;

    public User() {}

    public User(String firstName, String lastName, String displayName, String email,
            Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.email = email;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User shallowCopy() {
        User user =
                new User(this.firstName, this.lastName, this.displayName, this.email, this.address);
        return user;
    }

    public User deepCopy() {
        Address address = new Address(
                this.getAddress().getStreet(), this.getAddress().getDistrict(),
                this.getAddress().getProvince());
        User user = new User(this.firstName, this.lastName, this.displayName, this.email, address);
        return user;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", displayName="
                + displayName + ", email="
                + email + ", address=" + address + "]";
    }
}
