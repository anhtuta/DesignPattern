package dp_refactoring_guru.adapter.stock;

import dp_refactoring_guru.adapter.stock.external_libs.AnalyticsLibrary;
import dp_refactoring_guru.adapter.stock.external_libs.AnalyticsLibraryImpl;

public class Application {
    public static void main(String[] args) {
        XmlStock apple = new XmlStock("APL", 154.23f, 165.68f);
        XmlStock btc = new XmlStock("BTC", 73212f, 62195f);

        AnalyticsLibrary analyticsLibrary = new AnalyticsLibraryImpl();
        ClientAnalytics client = new StockAdapter(analyticsLibrary);
        client.displayAnalytics(apple);
        client.displayAnalytics(btc);
    }
}
