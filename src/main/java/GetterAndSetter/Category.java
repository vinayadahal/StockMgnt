package GetterAndSetter;

import ManagedBean.CategoryBean;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean(name = "mbCategory")
@Named(value = "Category")
@RequestScoped
public class Category {

    private String id;
    private String categoryName;
//    public

    public void setId(String category_id) {
        this.id = category_id;
    }

    public void setName(String category) {
        this.categoryName = category;
    }

    public String update() { // this is action listener so no need to add get/set
        System.out.println("SetUpdate");
        CategoryBean objCategoryBean = new CategoryBean();
        objCategoryBean.updateCategory(this.id, this.categoryName);
        return "index?faces-redirect=true";
    }

    public void setSingleRecord(String cateName) {
        System.out.println("SetRecord");
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String category = params.get("categoryName");
        String categoryId = params.get("categoryId");
        this.id = categoryId;
        this.categoryName = category;
        System.out.println("single record: " + this.categoryName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return categoryName;
    }

    public List<Category> getAll() {
        CategoryBean objCategoryBean = new CategoryBean();
        return objCategoryBean.getAllCategoryBean();
    }

    public String getSingleRecord() {
        CategoryBean objCategoryBean = new CategoryBean();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String categoryId = params.get("categoryId");
//        this.id = objCategoryBean.getSingleCategoryBean(categoryId).getId();
        return objCategoryBean.getSingleCategoryBean(categoryId).getName();
    }

}
