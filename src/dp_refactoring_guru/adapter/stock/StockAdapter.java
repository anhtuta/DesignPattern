package dp_refactoring_guru.adapter.stock;

import dp_refactoring_guru.adapter.stock.external_libs.AnalyticsLibrary;
import dp_refactoring_guru.adapter.stock.external_libs.JsonStock;

public class StockAdapter implements ClientAnalytics {

    // Adaptee
    private final AnalyticsLibrary analyticsLibrary;

    public StockAdapter(AnalyticsLibrary analyticsLibrary) {
        this.analyticsLibrary = analyticsLibrary;
    }

    @Override
    public void displayAnalytics(XmlStock xmlStock) {
        JsonStock jsonStock = convertToServiceFormat(xmlStock);
        analyticsLibrary.showAnalytics(jsonStock);
    }

    /**
     * Converts XML data to JSON format. This method name is similar to the method in the photo (readme file)
     */
    private JsonStock convertToServiceFormat(XmlStock xmlStock) {
        JsonStock jsonStock = new JsonStock();
        jsonStock.quote = xmlStock.quote;
        jsonStock.openPrice = xmlStock.openPrice;
        jsonStock.closePrice = xmlStock.closePrice;
        return jsonStock;
    }
}
