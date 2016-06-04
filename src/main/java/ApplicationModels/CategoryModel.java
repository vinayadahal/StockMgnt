package ApplicationModels;

import DataSource.Datasource;
import ManagedBean.Category;
import SystemModels.DeleteModel;
import SystemModels.InsertModel;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryModel {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    InsertModel objInsert = new InsertModel();
    UpdateModel objUpdate = new UpdateModel();
    DeleteModel objDelete = new DeleteModel();
    List<Category> list = new ArrayList();

    public List<Category> getAllCategoryBean() {
        System.out.println("Bean Called");
        objSelect.select("*");
        objSelect.from("category");
        List<Map> result = objSelect.runQuery();
        for (Map category : result) {
            Category objCategory = new Category();
            objCategory.setId(category.get("id").toString());
            objCategory.setName(category.get("name").toString());
            list.add(objCategory);
        }
        return list;
    }

    public Category getSingleCategoryBean(String id) {
        Category objCategory = new Category();
        objSelect.select("*");
        objSelect.from("category");
        String[] cols = {"id"};
        String[] vals = {id};
        objSelect.where(cols, vals);
        List<Map> result = objSelect.runQuery();
        for (Map category : result) {
            objCategory.setId(category.get("id").toString());
            objCategory.setName(category.get("name").toString());
        }
        return objCategory;
    }

    public int addCategory(String value) {
        objInsert.insert("category");
        String[] cols = {"name"};
        String[] vals = {value};
        objInsert.values(cols, vals);
        return objInsert.runUpdate();
    }

    public int updateCategory(String id, String value) {
        objUpdate.update("category");
        String[] col = {"name"};
        String[] val = {value};
        System.out.println(value);
        System.out.println(id);
        objUpdate.set(col, val);
        String[] whereCol = {"id"};
        String[] whereVal = {id};
        objUpdate.where(whereCol, whereVal);
        return objUpdate.runQuery();
    }

    public void delete(String id) {
        objDelete.delete("category");
        String[] cols = {"id"};
        String[] vals = {id};
        objDelete.where(cols, vals);
        objDelete.runQuery();
    }

}
