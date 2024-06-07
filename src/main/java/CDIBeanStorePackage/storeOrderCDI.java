/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanStorePackage;

import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreOrder;
import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
import RestClientPackage.storeClient;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kartik Patel
 */
@Named(value = "storeOrderCDI")
@RequestScoped
public class storeOrderCDI {

    RestClientPackage.storeClient sc;
    Response rs;
    // store ordes
    Collection<ElectronicStoreOrder> sorder;
    GenericType<Collection<ElectronicStoreOrder>> gsorder;

    // store stocks
    Collection<ElectronicStoreProductStock> storeStock;
    GenericType<Collection<ElectronicStoreProductStock>> gstoreStock;

    // festival offres
    Collection<ElectronicStoreFestival> storefestival;
    GenericType<Collection<ElectronicStoreFestival>> gstorefestival;

    Collection<Object[]> saleProduct;
    GenericType<Collection<Object[]>> gsaleProduct;

    Collection<ElectronicStoreSellingProduct> storeSellingProducts;
    GenericType<Collection<ElectronicStoreSellingProduct>> gstoreSellingProducts;
    String pid;

    ElectronicStoreFestival festival = new ElectronicStoreFestival();

    // Count variable for store order,stock,store product,Electronic product,festival
    Integer storeOrderCount;
    Integer stockCount;
    Integer electronicProductCount;
    Integer storeProductCount;
    Integer offerCount;
    Integer userOrderCount;
    Integer userFeedbackCount;
    Integer soldProductCount;
    
    // for Error Message
    String succesMessage, errorMessage;
    @EJB
    EJBPackage.electronicStoreDetailsEJB esd;

    public storeOrderCDI() {
        sc = new storeClient();
        sorder = new ArrayList<>();
        gsorder = new GenericType<Collection<ElectronicStoreOrder>>() {
        };
        storeStock = new ArrayList<>();
        gstoreStock = new GenericType<Collection<ElectronicStoreProductStock>>() {
        };
        storefestival = new ArrayList<>();
        gstorefestival = new GenericType<Collection<ElectronicStoreFestival>>() {
        };

        saleProduct = new ArrayList<>();
        gsaleProduct = new GenericType<Collection<Object[]>>() {
        };

        storeSellingProducts = new ArrayList<>();
        gstoreSellingProducts = new GenericType<Collection<ElectronicStoreSellingProduct>>() {
        };

        succesMessage = null;
        errorMessage = null;
    }

    public Collection<ElectronicStoreOrder> getSorder() {
        rs = sc.getAllOrders(Response.class);
        sorder = rs.readEntity(gsorder);
        return sorder;
    }

    public void setSorder(Collection<ElectronicStoreOrder> sorder) {
        this.sorder = sorder;
    }

    public Collection<ElectronicStoreProductStock> getStoreStock() {
        rs = sc.getAllStock(Response.class);
        storeStock = rs.readEntity(gstoreStock);
        return storeStock;
    }

    public void setStoreStock(Collection<ElectronicStoreProductStock> storeStock) {
        this.storeStock = storeStock;
    }

    public Integer getStoreOrderCount() {
        rs = sc.getEleStoreOrderCount(Response.class);
        storeOrderCount = rs.readEntity(Integer.class);
        return storeOrderCount;
    }

    public void setStoreOrderCount(Integer storeOrderCount) {
        this.storeOrderCount = storeOrderCount;
    }

    public Integer getStockCount() {
        rs = sc.getStoreStockCount(Response.class);
        stockCount = rs.readEntity(Integer.class);
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Integer getElectronicProductCount() {
        rs = sc.getElectronicProductCount(Response.class);
        electronicProductCount = rs.readEntity(Integer.class);
        return electronicProductCount;
    }

    public void setElectronicProductCount(Integer electronicProductCount) {
        this.electronicProductCount = electronicProductCount;
    }

    public Integer getStoreProductCount() {
        rs = sc.getStoreSellingProductCount(Response.class);
        storeProductCount = rs.readEntity(Integer.class);
        return storeProductCount;
    }

    public void setStoreProductCount(Integer storeProductCount) {
        this.storeProductCount = storeProductCount;
    }

    public Integer getOfferCount() {
        rs = sc.getOfferCount(Response.class);
        offerCount = rs.readEntity(Integer.class);
        return offerCount;
    }

    public void setOfferCount(Integer offerCount) {
        this.offerCount = offerCount;
    }

    public Integer getUserOrderCount() {
//        rs = sc.getUserOrderCount(Response.class);
//        userOrderCount = rs.readEntity(Integer.class);
        userOrderCount = esd.getUserOrderCount();
        return userOrderCount;
    }

    public void setUserOrderCount(Integer userOrderCount) {
        this.userOrderCount = userOrderCount;
    }

    public Integer getUserFeedbackCount() {
        rs = sc.getUserFeedbackCount(Response.class);
        userFeedbackCount = rs.readEntity(Integer.class);
        return userFeedbackCount;
    }

    public void setUserFeedbackCount(Integer userFeedbackCount) {
        this.userFeedbackCount = userFeedbackCount;
    }

    public Integer getSoldProductCount() {
        rs = sc.getSaleProductCount(Response.class);
        soldProductCount = rs.readEntity(Integer.class);
        return soldProductCount;
    }

    public void setSoldProductCount(Integer soldProductCount) {
        this.soldProductCount = soldProductCount;
    }
    
    public Collection<ElectronicStoreFestival> getStorefestival() {
        rs = sc.displayFestival(Response.class);
        storefestival = rs.readEntity(gstorefestival);
        return storefestival;
    }

    public void setStorefestival(Collection<ElectronicStoreFestival> storefestival) {
        this.storefestival = storefestival;
    }

    public ElectronicStoreFestival getFestival() {
        return festival;
    }

    public void setFestival(ElectronicStoreFestival festival) {
        this.festival = festival;
    }

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

    // add Offer
    public String addFestival() {
        succesMessage = "Offer Successfully Inserted";
        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(festival.getFestivalDate());

        sc.addFestival(festival.getFestivalName(), formattedDate, String.valueOf(festival.getFestivalDiscount()));
        return "displayFestivalOffer";
    }

    // for edit form
    public String editFestivalOffer(ElectronicStoreFestival festival) {
        this.festival = festival;
        return "editFestivalOffer";
    }

    // update offer
    public String updateFestivalOffer() {
        succesMessage = "Offer Successfully Edited";

        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(festival.getFestivalDate());

        sc.updateFestival(String.valueOf(festival.getFestivalId()), festival.getFestivalName(), formattedDate, String.valueOf(festival.getFestivalDiscount()));
        return "displayFestivalOffer";
    }

    public String deleteOffer(Integer festId) {
        succesMessage = "Offer Successfully deleted";

        sc.deleteFestival(String.valueOf(festId));
        return "displayFestivalOffer";
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    // get all seeling product for load data in dropdown
    public Collection<ElectronicStoreSellingProduct> getStoreSellingProducts() {
        rs = sc.getAllSellingProduct(Response.class);
        storeSellingProducts = rs.readEntity(gstoreSellingProducts);
        return storeSellingProducts;
    }

    public void setStoreSellingProducts(Collection<ElectronicStoreSellingProduct> storeSellingProducts) {
        this.storeSellingProducts = storeSellingProducts;
    }

    // display total sale of each product
    public Collection<Object[]> getSaleProduct() {
        if (pid != null) {
            rs = sc.displayAllSales1(Response.class, pid);
            saleProduct = rs.readEntity(gsaleProduct);
            return saleProduct;
        }else{
            rs = sc.displayAllSales(Response.class);
            saleProduct = rs.readEntity(gsaleProduct);
            return saleProduct;
        }
    }

    public void setSaleProduct(Collection<Object[]> saleProduct) {
        this.saleProduct = saleProduct;
    }
}
