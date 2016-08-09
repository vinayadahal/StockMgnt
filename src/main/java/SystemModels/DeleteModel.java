package SystemModels;

import DataSource.Datasource;
import SystemConfig.BootStrap;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteModel {

    public String query;
    public List<String> value = new ArrayList<>();

    public void delete(String tableName) {
        query = "DELETE FROM " + tableName;
    }

    public void where(String[] id, String[] val) {
        query += " WHERE ";
        if (id.length == val.length) {
            for (int i = 0; i < val.length; i++) {
                if (val.length > i + 1) {
                    query += "`" + id[i] + "`" + " = ? AND ";
                    value.add(val[i]);
                } else {
                    query += "`" + id[i] + "`" + " = ?";
                    value.add(val[i]);
                    break;
                }
            }
        } else {
            System.out.println("Number of element in args doesn't match in query.");
        }
    }

    public int runQuery() {
//        Datasource objConnect = new Datasource();
        try {
            PreparedStatement prepStmt = BootStrap.dbConnection.prepareStatement(query);
            for (int i = 0; i < value.size(); i++) {
                prepStmt.setString(i + 1, value.get(i));
            }
            value.clear();
            int status = prepStmt.executeUpdate();
            prepStmt.close();
//            objConnect.closeConnection();
            return status;
        } catch (SQLException ex) {
            System.out.println("Exception caught on DeleteModel runQuery >>> " + ex);
        }
//        objConnect.closeConnection();
        return 0;
    }

}
