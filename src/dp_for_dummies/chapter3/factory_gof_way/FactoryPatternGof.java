package dp_for_dummies.chapter3.factory_gof_way;

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


class SecureOracleConnection extends Connection {
    public SecureOracleConnection() {}

    @Override
    public String description() {
        return "Oracle secure";
    }
}


class SecureSqlServerConnection extends Connection {
    public SecureSqlServerConnection() {}

    @Override
    public String description() {
        return "SQL Server secure";
    }
}


class SecureMySqlConnection extends Connection {
    public SecureMySqlConnection() {}

    @Override
    public String description() {
        return "MySQL secure";
    }
}

// Abstract factory pattern
abstract class ConnectionFactory {
    public ConnectionFactory() {}

    protected abstract Connection createConnection(String type);
}


class NormalFactory extends ConnectionFactory {
    @Override
    public Connection createConnection(String type) {
        if (type.equals("Oracle")) {
            return new OracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SqlServerConnection();
        } else {
            return new MySqlConnection();
        }
    }
}


class SecureFactory extends ConnectionFactory {
    @Override
    public Connection createConnection(String type) {
        if (type.equals("Oracle")) {
            return new SecureOracleConnection();
        } else if (type.equals("SQL Server")) {
            return new SecureSqlServerConnection();
        } else {
            return new SecureMySqlConnection();
        }
    }
}


// GoF = Gang of Four: 4 gã nghĩ ra 23 cái design pattern,
// nên nhiều nơi gọi là GoF design patterns
public class FactoryPatternGof {
    public static void main(String[] args) {
        ConnectionFactory factory1 = new SecureFactory();
        Connection conn1 = factory1.createConnection("SQL Server");
        System.out.println(conn1.description());

        ConnectionFactory factory2 = new NormalFactory();
        Connection conn2 = factory2.createConnection("Oracle");
        System.out.println(conn2.description());
    }
}
