package ManagedBean;

import ApplicationModels.RoleModel;
import Services.Commons;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbRoles")
@RequestScoped
public class Role {

    private String id;
    private String role;

    public void setId(String role_id) {
        this.id = role_id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public List<Role> getAll() {
        return new RoleModel().getAllRoleBean();
    }

    public void singleRecord() {
        RoleModel objRoleModel = new RoleModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String roleId = params.get("roleId");
        this.id = objRoleModel.getSingleUser(roleId).getId();
        this.role = objRoleModel.getSingleUser(roleId).getRole();
    }

    public void add() {
        if (new RoleModel().addRole(this.role) == 0) {
            System.out.println("Could not insert category for some reason....show error page!!!");
            return;
        }
        String successMsg = "Role Added: " + this.role;
        new Commons().redirectPage("role", "index", successMsg);
    }

    public void update() {
        if (this.role == null) {
            System.out.println("Data are null....fatal error from Product bean");
            return;
        }
        if (new RoleModel().updateRole(this.role, this.id) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "Role Updated: " + this.role;
        new Commons().redirectPage("role", "index", successMsg);
    }

    public void delete(String id) {
        new RoleModel().delete(id);
        String successMsg = "Role ID: " + id + " deleted";
        new Commons().redirectPage("role", "index", successMsg);
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
