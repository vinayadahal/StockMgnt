package ManagedBean;

import DataSource.Datasource;
import GetterAndSetter.Category;
import SystemModels.SelectModel;
import SystemModels.UpdateModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@ManagedBean(name = "mbCategory")
//@Named(value = "categoryBean")
//@RequestScoped
//@Dependent
public class CategoryBean {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
    UpdateModel objUpdate = new UpdateModel();
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

    public int updateCategory(String id, String value) {
        System.out.println("should be updated");
        objUpdate.update("category");
        String[] col = {"name"};
        String[] val = {value};
        System.out.println(value);
        System.out.println(id);
        objUpdate.set(col, val);
        String[] whereCol = {"id"};
        String[] whereVal = {id};
        objUpdate.where(whereCol, whereVal);
        int status = objUpdate.runQuery();
        return status;
    }

    public void setCategory() {

    }
}
