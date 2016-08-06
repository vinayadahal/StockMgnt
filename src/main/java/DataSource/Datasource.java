package DataSource;

import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class Datasource {

    public java.sql.Connection dbConnection;
    public BasicDataSource ds;

    public Datasource() {
//        System.out.println("<<<<<<<<<<<<<<Datasource called>>>>>>>>>>");
        String connectionURL = "jdbc:mysql://localhost:3306/stockmgnt";
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("root");
        ds.setPassword("");
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
