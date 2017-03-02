package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.Product;
import ManagedBean.User;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<User> list = new ArrayList();
    private final String tableName = "user";

    public List<User> getAllUserBean() {
        objSelect.select("*");
        objSelect.from(tableName);
        List<Map> result = objSelect.runQuery();
        for (Map product : result) {
            User objUser = new User();
            objUser.setId(product.get("id").toString());
            objUser.setFirstName(product.get("first_name").toString());
            objUser.setLastName(product.get("last_name").toString());
            objUser.setEmail(product.get("email").toString());
            objUser.setPhone(product.get("phone").toString());
            list.add(objUser);
        }
        return list;
    }

    public Map<String, String> getAllCategoryList() {
        objSelect.select("*");
        objSelect.from("category");
        List<Map> result = objSelect.runQuery();
        Map<String, String> CategoryList = new HashMap<>();
        for (Map category : result) {
            CategoryList.put(category.get("name").toString(), category.get("id").toString());
        }
        return CategoryList;
    }

    public User getSingleUser(String id) {
        objSelect.select("*");
        objSelect.from(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        User objUser = new User();
        for (Map user : result) {
            objUser.setId(user.get("id").toString());
            objUser.setFirstName(user.get("first_name").toString());
            objUser.setLastName(user.get("last_name").toString());
            objUser.setPhone(user.get("phone").toString());
            objUser.setEmail(user.get("email").toString());
            objUser.setUsername(user.get("username").toString());
            objUser.setPassword(user.get("password").toString());
        }
        return objUser;
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
