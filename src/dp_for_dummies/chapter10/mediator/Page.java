package dp_for_dummies.chapter10.mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Page {
    protected Mediator mediator;
    protected String response;    // y/n

    public Page(Mediator mediator) {
        this.mediator = mediator;
        this.response = "n";
    }

    public abstract void go();
}

class WelcomePage extends Page {

    public WelcomePage(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void go() {
        System.out.print("Do you want to shop? [y/n]?\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            response = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error");
        }
        if (response.equals("y")) {
            mediator.handle(PageState.WELCOME_SHOP);
        } else {
            mediator.handle(PageState.WELCOME_EXIT);
        }
    }
}

class ShopPage extends Page {

    public ShopPage(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void go() {
        System.out.print("Are you ready to purchase? [y/n]?\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            response = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error");
        }
        if (response.equals("y")) {
            mediator.handle(PageState.SHOP_PURCHASE);
        } else {
            mediator.handle(PageState.SHOP_EXIT);
        }
    }
}

class PurchasePage extends Page {

    public PurchasePage(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void go() {
        System.out.print("Buy the item now? [y/n]?\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            response = reader.readLine();
        } catch (IOException e) {
            System.err.println("Error");
        }
        if (response.equals("y")) {
            System.out.println("Thanks for your purchase.");
        }

        mediator.handle(PageState.PURCHASE_EXIT);
    }
}

class ExitPage extends Page {

    public ExitPage(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void go() {
        System.out.println("Please come again sometime.");
    }
}

