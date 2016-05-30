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

    public List<Category> getCategoryBean() {
        System.out.println("Bean Called");
        List<Category> list = new ArrayList();
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
}
