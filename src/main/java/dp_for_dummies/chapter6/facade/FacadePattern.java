package dp_for_dummies.chapter6.facade;

interface Printer {
    void initPrinter();

    void turnFanOn();

    void warmUp();

    void getData(String text);

    void formatData(String text);

    void checkPaperSupply();

    void runInternalDiagnostics();

    void print(String text);

    void cleanUp();
}


class FacadePrinter {
    private Printer printer;

    void print(String text) {
        printer.initPrinter();
        printer.turnFanOn();
        printer.warmUp();
        printer.getData(text);
        printer.formatData(text);
        printer.checkPaperSupply();
        printer.runInternalDiagnostics();
        printer.print(text);
        printer.cleanUp();
    }
}


public class FacadePattern {
}
