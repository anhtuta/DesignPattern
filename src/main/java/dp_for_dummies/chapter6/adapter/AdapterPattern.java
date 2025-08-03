package dp_for_dummies.chapter6.adapter;

// Phía UI chỉ gửi cho BE object kiểu Ace, và hiện tại BE cũng chỉ handle object kiểu Ace
interface Ace {

    public void setName(String n);

    public String getName();

}


class AceClass implements Ace {
    private String name;

    @Override
    public void setName(String n) {
        this.name = n;
    }

    @Override
    public String getName() {
        return name;
    }
}


// New update: phía BE lúc này muốn chuyển sang dùng object Acme, tức là nó
// chỉ có thể nhận Acme object để xử lý thôi!
interface Acme {

    public void setFirstName(String f);

    public void setLastName(String l);

    public String getFirstName();

    public String getLastName();

}


// How to solve: tạo 1 adapter để chuyển đổi object Ace được gửi từ phía FE
// sang object Acme mà BE có thể handle được.
// => Adapt Ace object để nó trông giống như Acme object
class AceToAcmeAdapter implements Acme {
    // adapter cần chứa 1 object Ace (object cần được chuyển đổi, cần được adapt (adapted object))
    Ace ace;
    String firstName;
    String lastName;

    public AceToAcmeAdapter(AceClass ace) {
        this.ace = ace;
        firstName = ace.getName().split(" ")[0];
        lastName = ace.getName().substring(firstName.length()).trim();
    }

    @Override
    public void setFirstName(String f) {
        this.firstName = f;
    }

    @Override
    public void setLastName(String l) {
        this.lastName = l;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}


public class AdapterPattern {

    public static void main(String[] args) {
        // Ace object được gửi từ UI
        AceClass ace = new AceClass();
        ace.setName("Tony Stark");

        // Phía BE: Acme object được chuyển đổi từ Ace object
        Acme acme = new AceToAcmeAdapter(ace);
        System.out.println(acme.getFirstName() + ", " + acme.getLastName());
    }
}
