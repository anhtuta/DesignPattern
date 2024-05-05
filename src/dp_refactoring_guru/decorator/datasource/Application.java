package dp_refactoring_guru.decorator.datasource;

import dp_refactoring_guru.decorator.datasource.decorator.BaseDataSourceDecorator;
import dp_refactoring_guru.decorator.datasource.decorator.CompressionDecorator;
import dp_refactoring_guru.decorator.datasource.decorator.EncryptionDecorator;
import dp_refactoring_guru.decorator.datasource.impl.FileDataSource;

public class Application {
    public static void main(String[] args) {
        // Must create a folder first
        String outputFilePath = "/Users/tuta/Temp/OutputDemo.txt";
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";

        BaseDataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(new FileDataSource(outputFilePath)));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource(outputFilePath);

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
