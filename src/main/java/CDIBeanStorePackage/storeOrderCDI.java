/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package CDIBeanStorePackage;

import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreOrder;
import EntityPackage.ElectronicStoreProductStock;
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
    ElectronicStoreFestival festival = new ElectronicStoreFestival();
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

    HttpSession session = request.getSession();
    
    // Count variable for store order,stock,store product,Electronic product,festival
    Integer storeOrderCount;
    Integer stockCount;
    Integer electronicProductCount;
    Integer storeProductCount;
    Integer offerCount;
    
    public storeOrderCDI() {
        sc = new storeClient();
        sorder = new ArrayList<>();
        gsorder = new GenericType<Collection<ElectronicStoreOrder>>(){};
        storeStock = new ArrayList<>();
        gstoreStock = new GenericType<Collection<ElectronicStoreProductStock>>(){};
        storefestival = new ArrayList<>();
        gstorefestival = new GenericType<Collection<ElectronicStoreFestival>>(){};
        session.removeAttribute("successmessage");
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
    
    // add Offer
    public String addFestival()
    {
        session.setAttribute("successmessage", "Offer Successfully Inserted");
        
        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(festival.getFestivalDate());
        
        sc.addFestival(festival.getFestivalName(), formattedDate, String.valueOf(festival.getFestivalDiscount()));
        return "displayFestivalOffer";
    }
    
    // for edit form
    public String editFestivalOffer(ElectronicStoreFestival festival)
    {
        this.festival = festival;
        return "editFestivalOffer";
    }
    
    // update offer
    public String updateFestivalOffer()
    {
        session.setAttribute("successmessage", "Offer Successfully Edited");
        
        // Format manufacture date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(festival.getFestivalDate());
        
        sc.updateFestival(String.valueOf(festival.getFestivalId()),festival.getFestivalName(), formattedDate, String.valueOf(festival.getFestivalDiscount()));
        return "displayFestivalOffer";
    }
    
    public String deleteOffer(Integer festId)
    {
        session.setAttribute("successmessage", "Offer Successfully deleted");
        sc.deleteFestival(String.valueOf(festId));
        return "displayFestivalOffer";
    }
}
