package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.User;
import ManagedBean.UserRole;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRoleModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<UserRole> list = new ArrayList();
    private final String tableName = "user_role";

    public List<UserRole> getAllUserRoleBean() {
        objSelect.select("*");
        objSelect.from(tableName);
        List<Map> result = objSelect.runQuery();
        for (Map userRole : result) {
            UserRole objUserRole = new UserRole();
            objUserRole.setId(userRole.get("id").toString());
            objUserRole.setUsername(userRole.get("username").toString());
            objUserRole.setPassword(userRole.get("password").toString());
            objUserRole.setRole(userRole.get("role").toString());
            objUserRole.setUserId(userRole.get("user_id").toString());
            list.add(objUserRole);
        }
        return list;
    }

    public UserRole getSingleUser(String id) {
        objSelect.select("*");
        objSelect.from(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        UserRole objUserRole = new UserRole();
        for (Map userRole : result) {
            objUserRole.setId(userRole.get("id").toString());
            objUserRole.setUsername(userRole.get("username").toString());
            objUserRole.setPassword(userRole.get("password").toString());
            objUserRole.setRole(userRole.get("role").toString());
            objUserRole.setUserId(userRole.get("user_id").toString());
        }
        return objUserRole;
    }

    public int addUser(String fname, String lname, String phone, String email) {
        objInsert.insert(tableName);
        String[] cols = {"first_name", "last_name", "phone", "email"};
        String[] vals = {fname, lname, phone, email};
        objInsert.values(cols, vals);
        return objInsert.runUpdate();
    }

    public int updateUser(String fname, String lname, String phone, String email, String id) {
        objUpdate.update(tableName);
        String[] col = {"first_name", "last_name", "phone", "email"};
        String[] val = {fname, lname, phone, email};
        objUpdate.set(col, val);
        String[] whereCol = {"id"};
        String[] whereVal = {id};
        objUpdate.where(whereCol, whereVal);
        return objUpdate.runQuery();
    }

    public void delete(String id) {
        objDelete.delete(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objDelete.where(cols, vals);
        objDelete.runQuery();
    }

}
