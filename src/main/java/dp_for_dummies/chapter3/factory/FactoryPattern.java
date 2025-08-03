package dp_for_dummies.chapter3.factory;

abstract class Connection {
    public Connection() {}

    public String description() {
        return "Generic";
    }
}


class OracleConnection extends Connection {
    public OracleConnection() {}

    @Override
    public String description() {
        return "Oracle";
    }
}


class SqlServerConnection extends Connection {
    public SqlServerConnection() {}

    @Override
    public String description() {
        return "SQL Server";
    }
}


class MySqlConnection extends Connection {
    public MySqlConnection() {}

    @Override
    public String description() {
        return "MySQL";
    }
}


// A factory class is a factory class, and that’s it. It’s not designed to be extended.
// Trong sách đặt tên là "FirstFactory"
final class ConnectionFactory {
    protected String type;

    public ConnectionFactory(String t) {
        type = t;
    }

    public Connection createConnection() {
        if (type.equals("Oracle")) {
            return new OracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SqlServerConnection();
        } else {
            return new MySqlConnection();
        }
    }
}


public class FactoryPattern {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory("SQL Server");
        Connection conn = factory.createConnection();
        System.out.println(conn.description());
    }
}
