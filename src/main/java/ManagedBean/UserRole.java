package ManagedBean;

import ApplicationModels.ProductModel;
import ApplicationModels.UserRoleModel;
import Services.Commons;
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
    private String role_id;
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

    public void setRoleId(String role_id) {
        this.role_id = role_id;
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

    public String getRoleId() {
        return role_id;
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
        this.role_id = objUserRoleModel.getSingleUser(userRoleId).getRoleId();
        this.user_id = objUserRoleModel.getSingleUser(userRoleId).getUserId();
    }

    public void add() {
        if (new UserRoleModel().addUser(this.username, this.password, this.role_id, this.user_id) == 0) {
            System.out.println("Could not insert UserRole for some reason....show error page!!!");
            return;
        }
        String successMsg = "User Added: " + this.username;
        new Commons().redirectPage("userRole", "index", successMsg);
    }

    public void update() {
        if (this.username == null || this.password == null || this.role_id == null || this.user_id == null) {
            System.out.println("Data are null....fatal error from UserRole bean");
            return;
        }
        if (new UserRoleModel().updateUser(this.username, this.password, this.role_id, this.user_id, this.id) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "User Updated: " + this.username;
        new Commons().redirectPage("userRole", "index", successMsg);
    }

    public void delete(String id) {
        new UserRoleModel().delete(id);
        String successMsg = "User ID: " + id + " deleted";
        new Commons().redirectPage("userRole", "index", successMsg);
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
