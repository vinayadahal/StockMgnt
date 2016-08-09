package SystemConfig;

import DataSource.Datasource;
import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.dbcp.BasicDataSource;

public class BootStrap implements ServletContextListener {

    public static java.sql.Connection dbConnection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("<<<<<<Creating Database Connection>>>>>>");
        BasicDataSource ds = new Datasource().createConnection();
        try {
            dbConnection = ds.getConnection();
        } catch (SQLException ex) {
            System.out.println("EXCEPTION::::: " + ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("<<<<<<Closing Database Connection>>>>>>");
        try {
            dbConnection.close();
        } catch (SQLException ex) {
            System.out.println("Exception::: " + ex);
        }
    }
}
