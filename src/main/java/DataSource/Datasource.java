package DataSource;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Datasource {

    public java.sql.Connection dbConnection;

    private void loadDriverClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: MySQL JDBC driver not found. Stacktrace follows:\n");
            System.out.println(ex);
        }
    }

    public Datasource() {
        try {
            String connectionURL = "jdbc:mysql://localhost/inventory";
            loadDriverClass();
            dbConnection = DriverManager.getConnection(connectionURL, "root", "");
        } catch (SQLException ex) {
            System.out.println("SQL Exception Caught:- " + ex);
        }
    }
}
