package GetterAndSetter;

import ManagedBean.CategoryBean;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@ManagedBean(name = "mbCategory")
@Named(value = "Category")
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
        CategoryBean objCategoryBean = new CategoryBean();
        return objCategoryBean.getAllCategoryBean();
    }

    public String getSingleRecord() {
        CategoryBean objCategoryBean = new CategoryBean();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String categoryId = params.get("categoryId");
        return objCategoryBean.getSingleCategoryBean(categoryId).getName();
    }

    @PostConstruct
    public void update() { // this is action listener so no need to add get/set
        System.out.println("SetUpdate");
        CategoryBean objCategoryBean = new CategoryBean();
        Map<String, String> requestParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (requestParams.get("categoryId") == null || requestParams.get("category") == null) { // update record if both values are true
            return;
        }
        if (objCategoryBean.updateCategory(requestParams.get("categoryId"), requestParams.get("category")) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "Category updated: " + requestParams.get("category");
        redirectPage("index", successMsg);

    }

    public void redirectPage(String pageName, String successMsg) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        try {
            ec.getFlash().put("param1", successMsg); //setting flash message
            ec.redirect(ec.getRequestContextPath() + "/category/" + pageName + ".xhtml");
        } catch (IOException ex) {
            System.out.println("Caught IO Exception >>> " + ex);
        }
    }

    public void showGrowl() { // contains growl message
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

        String message = null;
        if (ec.getFlash().containsKey("param1")) {
            message = ec.getFlash().get("param1").toString();//getting flash message
        }
        FacesContext context = FacesContext.getCurrentInstance();
        if (message != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success!", message));
        }
    }

}
