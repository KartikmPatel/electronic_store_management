/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanUserPackage;

import CDIBeanPackage.LoginBean;
import EntityPackage.UserFeedback;
import EntityPackage.UserOrderDetails;
import RestClientPackage.userClient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "userOrderDetailsCDI")
@RequestScoped
public class userOrderDetailsCDI {

    RestClientPackage.userClient uc;
    UserFeedback ufd = new UserFeedback();
    @Inject LoginBean lb;
    
    Response rs;
    Collection<UserOrderDetails> userOrder;
    GenericType<Collection<UserOrderDetails>> guserOrder;
    String successMessage;
    
    public userOrderDetailsCDI() {
        uc = new userClient();
        userOrder = new ArrayList<>();
        guserOrder = new GenericType<Collection<UserOrderDetails>>(){};
        
        successMessage = null;
    }

    public UserFeedback getUfd() {
        return ufd;
    }

    public void setUfd(UserFeedback ufd) {
        this.ufd = ufd;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
    
    // add User Feedback
    public String addUserFeedback()
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateString = currentDate.format(formatter); // Convert LocalDate to String
        
        uc.addFeedback(ufd.getMessage(), dateString, String.valueOf(lb.getComId()));
        successMessage = "Your Review Successfully Send";
        return "addFeedback";
    }
    
    // get user order of particular user
    public Collection<UserOrderDetails> getUserOrder() {
        rs = uc.getUserOrder(Response.class, String.valueOf(lb.getComId()));
        userOrder = rs.readEntity(guserOrder);
        return userOrder;
    }

    public void setUserOrder(Collection<UserOrderDetails> userOrder) {
        this.userOrder = userOrder;
    }
    
}
