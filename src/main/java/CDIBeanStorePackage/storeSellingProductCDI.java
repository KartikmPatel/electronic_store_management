/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanStorePackage;

import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
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
@Named(value = "storeSellingProductCDI")
@RequestScoped
public class storeSellingProductCDI {

    RestClientPackage.storeClient sc;
    Response rs;
    Collection<ElectronicStoreSellingProduct> sellingprod;
    GenericType<Collection<ElectronicStoreSellingProduct>> gsellingprod;

    // load product available in store product stock
    Collection<ElectronicStoreProductStock> storeStock;
    GenericType<Collection<ElectronicStoreProductStock>> gstoreStock;
    Integer productId;
    Integer selPrice;
    Integer prodPrice;

    // for Error Message
    String succesMessage, errorMessage;

    public storeSellingProductCDI() {
        sc = new storeClient();
        sellingprod = new ArrayList<>();
        gsellingprod = new GenericType<Collection<ElectronicStoreSellingProduct>>() {
        };
        storeStock = new ArrayList<>();
        gstoreStock = new GenericType<Collection<ElectronicStoreProductStock>>() {
        };

        succesMessage = null;
        errorMessage = null;
    }

    public Collection<ElectronicStoreSellingProduct> getSellingprod() {
        rs = sc.getAllSellingProduct(Response.class);
        sellingprod = rs.readEntity(gsellingprod);
        return sellingprod;
    }

    public void setSellingprod(Collection<ElectronicStoreSellingProduct> sellingprod) {
        this.sellingprod = sellingprod;
    }

    public Collection<ElectronicStoreProductStock> getStoreStock() {
        rs = sc.getStoreProductStoreForSelling(Response.class);
        storeStock = rs.readEntity(gstoreStock);
        return storeStock;
    }

    public void setStoreStock(Collection<ElectronicStoreProductStock> storeStock) {
        this.storeStock = storeStock;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProdPrice() {
        if (productId != null) {
            rs = sc.getPriceOfProduct(Response.class, String.valueOf(productId));
            prodPrice = rs.readEntity(Integer.class);
            return prodPrice;
        } else {
            prodPrice = 0;
            return prodPrice;
        }
    }

    public void setProdPrice(Integer prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Integer getSelPrice() {
        return selPrice;
    }

    public void setSelPrice(Integer selPrice) {
        this.selPrice = selPrice;
    }

    // getter setter for error and success message
    public String getSuccesMessage() {
        return succesMessage;
    }

    public void setSuccesMessage(String succesMessage) {
        this.succesMessage = succesMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String addSellingProduct() {
        if (selPrice != null && selPrice > 0 && productId != null) {
            rs = sc.checkSellingProduct(Response.class, String.valueOf(productId));
            ElectronicStoreSellingProduct checkprod = rs.readEntity(ElectronicStoreSellingProduct.class);
            if (checkprod != null) {
                errorMessage = "Product is already in selling";
                return "addSellingProduct.jsf";
            } else {
                succesMessage = "The product has been added for sale";
                sc.addStoreSellingProductData(String.valueOf(selPrice), String.valueOf(productId));
                return "displaySellingProduct.jsf";
            }
        } else {
            errorMessage = "selling price or product is required or selling price is not less than 0";
            return "addSellingProduct.jsf";
        }
    }
}
