package ManagedBean;

import ApplicationModels.CategoryModel;
import ApplicationModels.ProductModel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbProduct")
@RequestScoped
public class Product {

    private String id;
    private String category_id;
    private String name;
    private String description;
    private String purchasePrice;
    private String sellingPrice;

    public void setId(String category_id) {
        this.id = category_id;
    }

    public void setCategoryId(String categoryId) {
        this.category_id = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setPurchasePrice(String purchase) {
        this.purchasePrice = purchase;
    }

    public void setSellingPrice(String sp) {
        this.sellingPrice = sp;
    }

    public String getId() {
        return id;
    }

    public String getCategoryId() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public List<Product> getAll() {
        ProductModel objProduct = new ProductModel();
        return objProduct.getAllProductBean();
    }

    public Map<String, String> getAllCategory() {
        ProductModel objProduct = new ProductModel();
        return objProduct.getAllCategoryList();
    }

    public void singleRecord() {
        ProductModel objProductModel = new ProductModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String productId = params.get("productId");
        this.id = objProductModel.getSingleProduct(productId).getId();
        this.name = objProductModel.getSingleProduct(productId).getName();
        this.description = objProductModel.getSingleProduct(productId).getDescription();
        this.category_id = objProductModel.getSingleProduct(productId).getCategoryId();
        this.purchasePrice = objProductModel.getSingleProduct(productId).getPurchasePrice();
        this.sellingPrice = objProductModel.getSingleProduct(productId).getSellingPrice();
    }

    public void add() {
        ProductModel objProductModel = new ProductModel();
        if (objProductModel.addProduct(this.name, this.purchasePrice, this.sellingPrice, this.description, this.category_id) == 0) {
            System.out.println("Could not insert category for some reason....show error page!!!");
            return;
        }
        String successMsg = "Product Added: " + this.name;
        redirectPage("index", successMsg);
    }

    public void update() {
        ProductModel objProductModel = new ProductModel();
        System.out.println(this.id);
        if (this.name == null || this.purchasePrice == null || this.sellingPrice == null || this.description == null || this.category_id == null) {
            System.out.println("Data are null....fatal error from Product bean");
            return;
        }
        if (objProductModel.updateProduct(this.name, this.purchasePrice, this.sellingPrice, this.description, this.category_id, this.id) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "Product Updated: " + this.name;
        redirectPage("index", successMsg);
    }

    public void delete(String id) {
        ProductModel objProductModel = new ProductModel();
        objProductModel.delete(id);
        String successMsg = "Product ID: " + id + " deleted";
        redirectPage("index", successMsg);
    }

    public void redirectPage(String pageName, String successMsg) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.getFlash().put("successMsg", successMsg); //setting flash message
            ec.redirect(ec.getRequestContextPath() + "/products/" + pageName + ".xhtml");
        } catch (IOException ex) {
            System.out.println("Caught IO Exception >>> " + ex);
        }
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
