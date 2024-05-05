package dp_refactoring_guru.decorator.datasource.decorator;

import dp_refactoring_guru.decorator.datasource.DataSource;

/**
 * Abstract base decorator
 */
public abstract class BaseDataSourceDecorator implements DataSource {

    private final DataSource wrappee;

    BaseDataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
