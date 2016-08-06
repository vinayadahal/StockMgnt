package ManagedBean;

import ApplicationModels.ClientModel;
import Services.Commons;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean(name = "mbClient")
@RequestScoped
public class Client {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String mobile;
    private String email;

    public void setId(String client_id) {
        this.id = client_id;
    }

    public void setName(String client_name) {
        this.name = client_name;
    }

    public void setPhone(String phone_num) {
        this.phone = phone_num;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setEmail(String email_addr) {
        this.email = email_addr;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public List<Client> getAll() {
        return new ClientModel().getAllClientBean();
    }

    public void singleRecord() {
        ClientModel objClientBean = new ClientModel();
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String clientId = params.get("clientId");
        this.id = objClientBean.getSingleClientBean(clientId).getId();
        this.name = objClientBean.getSingleClientBean(clientId).getName();
        this.phone = objClientBean.getSingleClientBean(clientId).getPhone();
        this.mobile = objClientBean.getSingleClientBean(clientId).getMobile();
        this.email = objClientBean.getSingleClientBean(clientId).getEmail();
        this.address = objClientBean.getSingleClientBean(clientId).getAddress();
        System.out.println(this.name);
    }

    public void add() {
        if (new ClientModel().addClient(this.name, this.phone, this.mobile, this.email, this.address) == 0) {
            System.out.println("Could not insert category for some reason....show error page!!!");
            return;
        }
        String successMsg = "Client Added: " + this.name;
        new Commons().redirectPage("index", successMsg);
    }

    public void update() {
        if (this.name == null || this.id == null) { // update record if both values are true
            System.out.println("Data are null....fatal error from category bean");
            return;
        }
        if (new ClientModel().updateClient(this.name, this.phone, this.mobile, this.email, this.address, this.id) != 1) {
            System.out.println("could not update the record.....Show Error page!!!");
            return;
        }
        String successMsg = "Client Updated: " + this.name;
        new Commons().redirectPage("index", successMsg);
    }

    public void delete(String id) {
        new ClientModel().delete(id);
        String successMsg = "Client ID: " + id + " deleted";
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
