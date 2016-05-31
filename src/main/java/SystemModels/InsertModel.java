package SystemModels;

import DataSource.Datasource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InsertModel {

    public String queryInsert;
    public List<String> columns = new ArrayList<>();
    public List<String> values = new ArrayList<>();
    Datasource objConnect = new Datasource();

    public void insert(String table) {
        queryInsert = "INSERT INTO " + table;
    }

    public void values(String[] cols, String[] vals) {
        queryInsert += " (";
        if (cols.length == vals.length) {
            for (int i = 0; i < vals.length; i++) {
                if (vals.length > i + 1) {
                    queryInsert += "`" + cols[i] + "`" + ", ";
                    columns.add(cols[i]);
                    values.add(vals[i]);
                } else {
                    queryInsert += "`" + cols[i] + "`" + ")";
                    columns.add(cols[i]);
                    values.add(vals[i]);
                    break;
                }
            }
            queryInsert += "VALUES(";
            for (int i = 0; i < vals.length; i++) {
                if (vals.length > i + 1) {
                    queryInsert += "?,";
                } else {
                    queryInsert += "?);";
                    break;
                }
            }
        } else {
            System.out.println("Number of columns don't match with values.");
        }
    }

    public int runUpdate() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PreparedStatement prepStmt = objConnect.dbConnection.prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS);
        for (int i = 0; i < values.size(); i++) {
            prepStmt.setString(i + 1, values.get(i));
        }
        prepStmt.executeUpdate(); // executes query
        int last_inserted_id = 0;

        ResultSet rs = prepStmt.getGeneratedKeys();
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }
        return last_inserted_id;
    }
}
