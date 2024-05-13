package com.mycompany.electronic_store_management.resources;

import EntityPackage.ElectronicStoreDetails;
import EJBPackage.electronicStoreDetailsEJB;
import EJBPackage.electronicStoreFestivalEJB;
import EJBPackage.electronicStoreProductStockEJB;
import EJBPackage.storeSellingProductEJB;
import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreOrder;
import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;

@Path("ElectronicStore")
@DeclareRoles("Store")
@RequestScoped
public class ElectronicStoreResource {
    @EJB electronicStoreDetailsEJB stoobj;
    @EJB electronicStoreProductStockEJB stockobj;
    @EJB electronicStoreFestivalEJB festobj;
    @EJB storeSellingProductEJB sellingobj;

    // get all store
    @GET
    @Path("displayStore")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> displayElectronicStore() {
        return stoobj.displayElectronicStoreDetails();
    }

    // add store
    @POST
    @Path("addStore/{name}/{email}/{contactNo}/{password}/{storeLogo}/{address}/{country}")
    @RolesAllowed("Store")
    public void addElectronicStore(@PathParam("name") String name, @PathParam("email") String email, @PathParam("contactNo") Integer contact, @PathParam("password") String password, @PathParam("storeLogo") String logo, @PathParam("address") String address, @PathParam("country") String country) {
        stoobj.addElectronicStoreDetails(name, email, contact, password, logo, address, country);
    }

    @GET
    @Path("getStoreById/{storeId}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> getStoreById(@PathParam("storeId") Integer storeId) {
        return stoobj.getDataByIdForUpdate(storeId);
    }

    // update store
    @POST
    @Path("updateStore/{storeId}/{name}/{email}/{contactNo}/{password}/{storeLogo}/{address}/{country}")
    @RolesAllowed("Store")
    public void updateElectronicStore(@PathParam("storeId") Integer storeId, @PathParam("name") String name, @PathParam("email") String email, @PathParam("contactNo") Integer contact, @PathParam("password") String password, @PathParam("storeLogo") String logo, @PathParam("address") String address, @PathParam("country") String country) {
        stoobj.updateElectronicStoreDetails(storeId, name, email, contact, password, logo, address, country);
    }

    @DELETE
    @Path("deleteStore/{storeId}")
    @RolesAllowed("Store")
    public void deleteElectronicStore(@PathParam("storeId") Integer storeId) {
        stoobj.deleteElectronicStoreDetails(storeId);
    }
    
    // Electronic Store Product Store
    @POST
    @Path("addstock/{pid}/{qty}")
    @RolesAllowed("Store")
    public void addStock(@PathParam("pid") Integer pid,@PathParam("qty") Integer qty)
    {
        stockobj.addStock(pid, qty);
    }
    
    @GET
    @Path("displaystock")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreProductStock> getAllStock()
    {
        return stockobj.getAllStock();
    }
    
    // display festival
    @GET
    @Path("displayFestival")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreFestival> displayFestival(){
        return festobj.displayFestival();
    }
    
    // add festival
    @POST
    @Path("addFestival/{festivalName}/{festivalDate}/{festivalDiscount}")
    @RolesAllowed("Store")
    public void addFestival(@PathParam("festivalName") String festivalName, @PathParam("festivalDate") String festivalDate, @PathParam("festivalDiscount") Integer festivalDiscount){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(festivalDate);
            festobj.addFestival(festivalName, mdate, festivalDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @GET
    @Path("getFestivalById/{festivalId}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreFestival> getFestivalById(@PathParam("festivalId") Integer festivalId){
        return festobj.getDataByIdForUpdate(festivalId);
    }
    
    // update festival
    @POST
    @Path("updateFestival/{festivalId}/{festivalName}/{festivalDate}/{festivalDiscount}")
    @RolesAllowed("Store")
    public void updateFestival(@PathParam("festivalId") Integer festivalId,@PathParam("festivalName") String festivalName, @PathParam("festivalDate") String festivalDate, @PathParam("festivalDiscount") Integer festivalDiscount){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(festivalDate);
            festobj.updateFestival(festivalId, festivalName, mdate, festivalDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @DELETE
    @Path("deleteFestival/{festivalId}")
    @RolesAllowed("Store")
    public void deleteFestival(@PathParam("festivalId") Integer festivalId){
        festobj.deleteFestival(festivalId);
    }
    
    // get all products for display products
    @GET
    @Path("getallproducts")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getAllProducts()
    {
        return stoobj.getAllProducts();
    }
    
    // check quantity
    @GET
    @Path("getquantity/{prodid}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getQuantity(@PathParam("prodid") Integer prodid)
    {
        return stoobj.getQuantity(prodid);
    }
    
    // get a product by product Id
    @GET
    @Path("getptoductbyid/{prodid}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDetails getProductById(@PathParam("prodid") Integer prodid)
    {
        return stoobj.getProductById(prodid);
    }
    
    // add order
    @POST
    @Path("addorder/{qty}/{billamt}/{odate}/{status}/{prodid}/{comid}")
    @RolesAllowed("Store")
    public void addOrder(@PathParam("qty") Integer qty,@PathParam("billamt") Integer billamt,@PathParam("odate") String odate,@PathParam("status") Integer status,@PathParam("prodid") Integer prodid,@PathParam("comid") Integer comid)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date odate1 = sdf.parse(odate);
            stoobj.addOrder(qty, billamt, odate1, status, prodid, comid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // display orders
    @GET
    @Path("getallorders")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreOrder> getAllOrders()
    {
        return stoobj.getAllOrders();
    }
    
    // get count of store order for Store dashboard
    @GET
    @Path("getelestoreordercount")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getEleStoreOrderCount()
    {
        return stoobj.getEleStoreOrderCount();
    }
    
    // get count of store product stock for store dashboard
    @GET
    @Path("getStoreStockCount")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getStoreStockCount()
    {
        return stoobj.getStoreStockCount();
    }
    
    // display Selling Products
    @GET
    @Path("getallsellingproduct")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreSellingProduct> getAllSellingProduct()
    {
        return sellingobj.getSellingProducts();
    }
    
    // get Products available in product stock table
    @GET
    @Path("getstoreproductstoreforselling")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreProductStock> getStoreProductStoreForSelling()
    {
        return sellingobj.getStoreProductStoreForSelling();
    }
    
    // get actaul price of product by product Id
    @GET
    @Path("getpriceofproduct/{pid}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getPriceOfProduct(@PathParam("pid") Integer pid) {
        return sellingobj.getPriceOfProduct(pid);
    }
    
    // add Selling Product
    @POST
    @Path("addstoresellingproduct/{price}/{pid}")
    @RolesAllowed("Store")
    public void addStoreSellingProductData(@PathParam("price") Integer price,@PathParam("pid") Integer pid) {
        sellingobj.addStoreSellingProduct(price, pid);
    }
    
    // check if the same Product exists or not in the selling product table
    @GET
    @Path("checksellingproduct/{pid}")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public ElectronicStoreSellingProduct checkSellingProduct(@PathParam("pid") Integer pid) {
        return sellingobj.checkSellingProduct(pid);
    }
    
    // get count of Electronic Products for store dashboard
    @GET
    @Path("getEleProductCount")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getElectronicProductCount() {
        return stoobj.getElectronicProductCount();
    }
    
    // get count of Selling Product for Store dashboard
    @GET
    @Path("getstoresellingproductcount")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getStoreSellingProductCount()
    {
        return stoobj.getStoreSellingProductCount();
    }
    
    // get count of Festival Offers for Store dashboard
    @GET
    @Path("getoffercount")
    @RolesAllowed("Store")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getOfferCount()
    {
        return stoobj.getOfferCount();
    }
    
}
