package ManagedBean;

import DataSource.Datasource;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import GetterAndSetter.Category;
import SystemModels.SelectModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author bidahal
 */
@ManagedBean(name = "mbCategory")
@Named(value = "categoryBean")
@RequestScoped
//@Dependent
public class CategoryBean {

    Datasource objDatasource = new Datasource();
    SelectModel objSelect = new SelectModel();
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

    public void updateCategory(Object category) {
        System.out.println("called...but no idea about the object :(");
        System.out.println(category);
    }

    public void setCategory() {

    }
}
