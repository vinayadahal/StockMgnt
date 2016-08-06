package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.Category;
import ManagedBean.Client;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClientModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<Client> list = new ArrayList();
    private final String tableName = "clients";

    public List<Client> getAllClientBean() {
        objSelect.select("*");
        objSelect.from(tableName);
        List<Map> result = objSelect.runQuery();
        for (Map client : result) {
            Client objClient = new Client();
            objClient.setId(client.get("id").toString());
            objClient.setName(client.get("name").toString());
            objClient.setPhone(client.get("phone").toString());
            objClient.setMobile(client.get("mobile").toString());
            objClient.setEmail(client.get("email").toString());
            objClient.setAddress(client.get("address").toString());
            list.add(objClient);
        }
        return list;
    }

    public Client getSingleClientBean(String id) {
        Client objClient = new Client();
        objSelect.select("*");
        objSelect.from(tableName);
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        for (Map client : result) {
            objClient.setId(client.get("id").toString());
            objClient.setName(client.get("name").toString());
            objClient.setPhone(client.get("phone").toString());
            objClient.setMobile(client.get("mobile").toString());
            objClient.setEmail(client.get("email").toString());
            objClient.setAddress(client.get("address").toString());
        }
        return objClient;
    }

    public int addClient(String name, String phone, String mobile, String email, String address) {
        objInsert.insert(tableName);
        String[] cols = {"name", "phone", "mobile", "email", "address"};
        String[] vals = {name, phone, mobile, email, address};
        objInsert.values(cols, vals);
        return objInsert.runUpdate();
    }

    public int updateClient(String name, String phone, String mobile, String email, String address, String id) {
        objUpdate.update(tableName);
        String[] col = {"name", "phone", "mobile", "email", "address"};
        String[] val = {name, phone, mobile, email, address};
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
