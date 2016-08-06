package ManagedBean;

import ApplicationModels.CategoryModel;
import Services.Commons;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbCategory")
@RequestScoped
public class Category {

    private String id;
    private String categoryName;

    public void setId(String category_id) {
        this.id = category_id;
    }

    public void setName(String category) {
        this.categoryName = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return categoryName;
    }

    public List<Category> getAll() {
        return new CategoryModel().getAllCategoryBean();
    }

    public void singleRecord() {
        CategoryModel objCategoryBean = new CategoryModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String categoryId = params.get("categoryId");
        this.id = objCategoryBean.getSingleCategoryBean(categoryId).getId();
        this.categoryName = objCategoryBean.getSingleCategoryBean(categoryId).getName();
    }

    public void add() {
        if (new CategoryModel().addCategory(this.categoryName) == 0) {
            System.out.println("Could not insert category for some reason....show error page!!!");
            return;
        }
        String successMsg = "Category Added: " + this.categoryName;
        new Commons().redirectPage("index", successMsg);
    }

    public void update() {
        if (this.categoryName == null || this.id == null) { // update record if both values are true
            System.out.println("Data are null....fatal error from category bean");
            return;
        }
        if (new CategoryModel().updateCategory(this.id, this.categoryName) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "Category Updated: " + this.categoryName;
        new Commons().redirectPage("index", successMsg);
    }

    public void delete(String id) {
        new CategoryModel().delete(id);
        String successMsg = "Category ID: " + id + " deleted";
        new Commons().redirectPage("index", successMsg);
    }

    public void showGrowl() { // contains growl message
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String message = null;
        if (ec.getFlash().containsKey("successMsg")) {
            message = ec.getFlash().get("successMsg").toString();//getting flash message
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (message != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
        }
    }

}
