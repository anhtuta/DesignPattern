package dp_refactoring_guru.adapter.stock.external_libs;

public class AnalyticsLibraryImpl implements AnalyticsLibrary {
    @Override
    public void showAnalytics(JsonStock jsonStock) {
        System.out.printf(
                "Showing analytics for %s: open price = %f, close price = %f, change = %f, change percentage = %f%%%n",
                jsonStock.quote, jsonStock.openPrice, jsonStock.closePrice, jsonStock.closePrice - jsonStock.openPrice,
                (jsonStock.closePrice - jsonStock.openPrice) / jsonStock.openPrice * 100);
    }
}
