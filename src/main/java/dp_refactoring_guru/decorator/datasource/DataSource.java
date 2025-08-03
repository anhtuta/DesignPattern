package dp_refactoring_guru.decorator.datasource;

/**
 * A common data interface, which defines read and write operations
 */
public interface DataSource {
    void writeData(String data);
    String readData();
}
