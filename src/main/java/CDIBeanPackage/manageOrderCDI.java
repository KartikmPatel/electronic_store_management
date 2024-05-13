/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanPackage;

import EntityPackage.ElectronicStoreOrder;
import RestClientPackage.companyClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "manageOrderCDI")
@RequestScoped
public class manageOrderCDI {

    RestClientPackage.companyClient cc;
    @Inject LoginBean lb;
    Response rs;
    Collection<ElectronicStoreOrder> esorder;
    GenericType<Collection<ElectronicStoreOrder>> gesorder;
    
    public manageOrderCDI() {
        cc = new companyClient();
        esorder = new ArrayList<>();
        gesorder = new GenericType<Collection<ElectronicStoreOrder>>(){};
    }

    public Collection<ElectronicStoreOrder> getEsorder() {
        rs = cc.getStoreOrders(Response.class, String.valueOf(lb.getComId()));
        esorder = rs.readEntity(gesorder);
        return esorder;
    }

    public void setEsorder(Collection<ElectronicStoreOrder> esorder) {
        this.esorder = esorder;
    }
    
    public String acceptOrder(Integer orderId,Integer qty,Integer prodId)
    {
        cc.changeStatus(String.valueOf(1), String.valueOf(orderId));
        cc.minusCompanyProductStock(String.valueOf(qty), String.valueOf(prodId));
        cc.addElectronicStoreProductStock(String.valueOf(prodId), String.valueOf(qty));
        return "manageOrder.jsf";
    }
}
