package lk.buyingandselling.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection dBConnection;
    private Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/buyingandselling","root","Ud74184318");
    }

    
    public static DBConnection getInstance() throws Exception {
        return (dBConnection != null) ? (dBConnection) : (dBConnection = new DBConnection());
    }

    public Connection getConnection() {
        return connection;
    }
}
