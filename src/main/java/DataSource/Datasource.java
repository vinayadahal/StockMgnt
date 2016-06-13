package DataSource;

import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class Datasource {

    public java.sql.Connection dbConnection;
    public BasicDataSource ds;

    private void loadDriverClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: MySQL JDBC driver not found. Stacktrace follows:\n");
            System.out.println(ex);
        }
    }

    public Datasource() {

        String connectionURL = "jdbc:mysql://localhost:3306/stockmgnt";
//            loadDriverClass();
//            dbConnection = DriverManager.getConnection(connectionURL, "root", "root");
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("root");
        ds.setUrl(connectionURL);
        
        // the settings below are optional -- dbcp can work with defaults
        ds.setInitialSize(1); // minimum connection at start of connection pool
        ds.setMinIdle(5); // min idle connection
        ds.setMaxIdle(10); // max idle connection
        ds.setMaxOpenPreparedStatements(100);
        try {
            dbConnection = ds.getConnection();
        } catch (SQLException ex) {
            System.out.println("Caught exception while creating connection:- " + ex);
        }

    }

    public void closeConnection() {
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("Exception while closing connection: " + ex);
        }
    }
}
