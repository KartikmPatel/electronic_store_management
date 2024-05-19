/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanUserPackage;

import EntityPackage.ElectronicStoreSellingProduct;
import RestClientPackage.userClient;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "homeCDI")
@RequestScoped
public class homeCDI {

    RestClientPackage.userClient uc;
    Response rs;
    Collection<ElectronicStoreSellingProduct> sellingProducts;
    GenericType<Collection<ElectronicStoreSellingProduct>> gsellingProducts;
    
    
    public homeCDI() {
        uc = new userClient();
        sellingProducts = new ArrayList<>();
        gsellingProducts = new GenericType<Collection<ElectronicStoreSellingProduct>>(){};
    }

    public Collection<ElectronicStoreSellingProduct> getSellingProducts() {
        rs = uc.getAllSellingProducts(Response.class);
        sellingProducts = rs.readEntity(gsellingProducts);
        return sellingProducts;
    }

    public void setSellingProducts(Collection<ElectronicStoreSellingProduct> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }
}
