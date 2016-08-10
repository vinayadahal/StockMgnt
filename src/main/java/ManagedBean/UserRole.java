package ManagedBean;

import ApplicationModels.ProductModel;
import ApplicationModels.UserRoleModel;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbUserRole")
@RequestScoped
public class UserRole {

    private String id;
    private String username;
    private String password;
    private String role;
    private String user_id;

    public void setId(String user_id) {
        this.id = user_id;
    }

    public void setUsername(String uname) {
        this.username = uname;
    }

    public void setPassword(String passwd) {
        this.password = passwd;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setUserId(String u_id) {
        this.user_id = u_id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return user_id;
    }

    public List<UserRole> getAll() {
        return new UserRoleModel().getAllUserRoleBean();
    }

    public Map<String, String> getAllCategory() {
        return new ProductModel().getAllCategoryList();
    }

    public void singleRecord() {
        UserRoleModel objUserRoleModel = new UserRoleModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String userRoleId = params.get("userRoleId");
        this.id = objUserRoleModel.getSingleUser(userRoleId).getId();
        this.username = objUserRoleModel.getSingleUser(userRoleId).getUsername();
        this.password = objUserRoleModel.getSingleUser(userRoleId).getPassword();
        this.role = objUserRoleModel.getSingleUser(userRoleId).getRole();
        this.user_id = objUserRoleModel.getSingleUser(userRoleId).getUserId();
    }
//
//    public void add() {
//        if (new UserModel().addUser(this.firstName, this.lastName, this.phone, this.email) == 0) {
//            System.out.println("Could not insert category for some reason....show error page!!!");
//            return;
//        }
//        String successMsg = "User Added: " + this.firstName;
//        new Commons().redirectPage("users", "index", successMsg);
//    }
//
//    public void update() {
//        if (this.firstName == null || this.lastName == null || this.phone == null || this.email == null) {
//            System.out.println("Data are null....fatal error from Product bean");
//            return;
//        }
//        if (new UserModel().updateUser(this.firstName, this.lastName, this.phone, this.email, this.id) != 1) {
//            System.out.println("could not update the record.....Show Error page!!!");
//            return;
//        }
//        String successMsg = "User Updated: " + this.firstName;
//        new Commons().redirectPage("users", "index", successMsg);
//    }
//
//    public void delete(String id) {
//        new UserModel().delete(id);
//        String successMsg = "User ID: " + id + " deleted";
//        new Commons().redirectPage("users", "index", successMsg);
//    }

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
