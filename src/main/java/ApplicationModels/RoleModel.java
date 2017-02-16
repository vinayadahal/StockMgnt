package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.Role;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<Role> list = new ArrayList();
    private final String tableName = "role";

    public List<Role> getAllRoleBean() {
        objSelect.select("*");
        objSelect.from(tableName);
        List<Map> result = objSelect.runQuery();
        for (Map product : result) {
            Role objRole = new Role();
            objRole.setId(product.get("id").toString());
            objRole.setRole(product.get("role").toString());
            list.add(objRole);
        }
        return list;
    }

    public Role getSingleUser(String id) {
        objSelect.select("*");
        objSelect.from(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        Role objRole = new Role();
        for (Map user : result) {
            objRole.setId(user.get("id").toString());
            objRole.setRole(user.get("role").toString());
        }
        return objRole;
    }

    public int addRole(String role) {
        objInsert.insert(tableName);
        String[] cols = {"role"};
        String[] vals = {role};
        objInsert.values(cols, vals);
        return objInsert.runUpdate();
    }

    public int updateRole(String role, String id) {
        objUpdate.update(tableName);
        String[] col = {"role"};
        String[] val = {role};
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
