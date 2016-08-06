package Services;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class Commons {

    public void redirectPage(String pageName, String successMsg) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.getFlash().put("successMsg", successMsg); //setting flash message
            ec.redirect(ec.getRequestContextPath() + "/clients/" + pageName + ".xhtml");
        } catch (IOException ex) {
            System.out.println("Caught IO Exception >>> " + ex);
        }
    }

}
