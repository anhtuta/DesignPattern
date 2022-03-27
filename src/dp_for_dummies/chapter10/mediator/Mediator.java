package dp_for_dummies.chapter10.mediator;

// Object trung gian, các object khác thay vì tương tác với nhau
// sẽ tương tác qua object này
public interface Mediator {
    public void enterPage();
    public void handle(PageState state);
}

class MediatorClass implements Mediator {
    private WelcomePage welcomePage;
    private ShopPage shopPage;
    private PurchasePage purchasePage;
    private ExitPage exitPage;

    public MediatorClass() {
        welcomePage = new WelcomePage(this);
        shopPage = new ShopPage(this);
        purchasePage = new PurchasePage(this);
        exitPage = new ExitPage(this);
    }

    @Override
    public void enterPage() {
        welcomePage.go();
    }

    // Mediator sẽ chịu trách nhiệm tương tác giữa các page
    // (trong ví dụ này chỉ là chuyển trang)
    @Override
    public void handle(PageState state) {
        switch (state) {
            case WELCOME_SHOP:
                shopPage.go();
                break;
            case WELCOME_EXIT:
                exitPage.go();
                break;
            case SHOP_PURCHASE:
                purchasePage.go();
                break;
            case SHOP_EXIT:
                exitPage.go();
                break;
            case PURCHASE_EXIT:
                exitPage.go();
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + state);
        }
    }
}
