/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanStorePackage;

import EntityPackage.UserFeedback;
import EntityPackage.UserOrderDetails;
import RestClientPackage.storeClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "manageUserOrderCDI")
@RequestScoped
public class manageUserOrderCDI {

    RestClientPackage.storeClient sc;
    Response rs;
    Collection<UserOrderDetails> orderDetails1;
    GenericType<Collection<UserOrderDetails>> gorderDetails1;
    
    Collection<UserFeedback> feedbacks;
    GenericType<Collection<UserFeedback>> gfeedbacks;
    @EJB EJBPackage.manageUserOdersEJB manageorder;
    
    public manageUserOrderCDI() {
        sc = new storeClient();
        orderDetails1 = new ArrayList<>();
        gorderDetails1 = new GenericType<Collection<UserOrderDetails>>(){};
        
        feedbacks = new ArrayList<>();
        gfeedbacks = new GenericType<Collection<UserFeedback>>(){};
    }

    public Collection<UserOrderDetails> getOrderDetails1() {
//        rs = sc.displayAllUserOrders(Response.class);
//        orderDetails1 = rs.readEntity(gorderDetails1);
        orderDetails1 = manageorder.getAllUserOrder();
        return orderDetails1;
    }

    public void setOrderDetails1(Collection<UserOrderDetails> orderDetails1) {
        this.orderDetails1 = orderDetails1;
    }

    public Collection<UserFeedback> getFeedbacks() {
        rs = sc.getAllFeedback(Response.class);
        feedbacks = rs.readEntity(gfeedbacks);
        return feedbacks;
    }

    public void setFeedbacks(Collection<UserFeedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
    
    public String verifyUserOrder(Integer orderId,Integer qty,Integer prodId)
    {
        sc.verifyUserOrder(String.valueOf(1), String.valueOf(orderId));
        sc.cutStoreProductStock(String.valueOf(qty), String.valueOf(prodId));
        return "displayUserOrder";
    }
}
