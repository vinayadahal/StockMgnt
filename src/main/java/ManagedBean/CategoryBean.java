package ManagedBean;

import DataSource.Datasource;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import GetterAndSetter.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.sql.DataSource;
//import javax.annotation.Resource;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;

@ManagedBean(name = "mbCategory")
@Named(value = "categoryBean")
@Dependent
public class CategoryBean {
    
    Datasource objDatasource = new Datasource();
    
    public void getCategoryBean() {
        try {
            PreparedStatement ps = objDatasource.dbConnection.prepareStatement("select * from category");
            ResultSet rs = ps.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("id"));
            }
            System.out.println(ps);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
    }

//    @Resource(name = "jdbc/inventory")
//    private DataSource ds;
//    public void getCategoryBean() throws SQLException, NamingException {
//
//        try {
//            Context ctx = new InitialContext();
//            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/inventory");
//        } catch (NamingException e) {
//            System.out.println(e);
//        }
//
//        if (ds == null) {
//            throw new SQLException("SQL exception thrown: Can't get data source");
//        }
//
//        //get database connection
//        Connection con = ds.getConnection();
//        if (con == null) {
//            throw new SQLException("Can't get database connection");
//        }
//        PreparedStatement ps = con.prepareStatement("select * from category");
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()) {
//            System.out.println(rs.getString("name"));
//        }
//        System.out.println(ps);
//    }
}
