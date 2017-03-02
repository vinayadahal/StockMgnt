package ManagedBean;

import ApplicationModels.ProductModel;
import ApplicationModels.UserModel;
import Services.Commons;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbUser")
@RequestScoped
public class User {

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String username;
    private String password;

    public void setId(String user_id) {
        this.id = user_id;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public void setEmail(String email_addr) {
        this.email = email_addr;
    }

    public void setPhone(String phone_no) {
        this.phone = phone_no;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getAll() {
        return new UserModel().getAllUserBean();
    }

    public Map<String, String> getAllCategory() {
        return new ProductModel().getAllCategoryList();
    }

    public void singleRecord() {
        UserModel objUserModel = new UserModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String userId = params.get("userId");
        this.id = objUserModel.getSingleUser(userId).getId();
        this.firstName = objUserModel.getSingleUser(userId).getFirstName();
        this.lastName = objUserModel.getSingleUser(userId).getLastName();
        this.phone = objUserModel.getSingleUser(userId).getPhone();
        this.email = objUserModel.getSingleUser(userId).getEmail();
        this.username = objUserModel.getSingleUser(userId).getUsername();
        this.password = objUserModel.getSingleUser(userId).getPassword();
        System.out.println("password: " + this.password);
    }

    public void add() {
        if (new UserModel().addUser(this.firstName, this.lastName, this.phone, this.email) == 0) {
            System.out.println("Could not insert category for some reason....show error page!!!");
            return;
        }
        String successMsg = "User Added: " + this.firstName;
        new Commons().redirectPage("users", "index", successMsg);
    }

    public void update() {
        if (this.firstName == null || this.lastName == null || this.phone == null || this.email == null) {
            System.out.println("Data are null....fatal error from Product bean");
            return;
        }
        if (new UserModel().updateUser(this.firstName, this.lastName, this.phone, this.email, this.id) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "User Updated: " + this.firstName;
        new Commons().redirectPage("users", "index", successMsg);
    }

    public void delete(String id) {
        new UserModel().delete(id);
        String successMsg = "User ID: " + id + " deleted";
        new Commons().redirectPage("users", "index", successMsg);
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
