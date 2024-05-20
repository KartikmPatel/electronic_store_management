/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@Named(value = "registerBean")
@RequestScoped
public class RegisterBean {

    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }
    
    private String selectedRole;

    public String getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(String selectedRole) {
        this.selectedRole = selectedRole;
    }
    
    public void proceedToRegister() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            System.out.println(selectedRole);
            if ("Company".equals(selectedRole)) {
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/companyPages/companyRegister.jsf");
            } else if ("User".equals(selectedRole)) {
                context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/userPages/userRegister.jsf");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
