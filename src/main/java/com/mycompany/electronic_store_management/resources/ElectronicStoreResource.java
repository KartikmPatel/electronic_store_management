package com.mycompany.electronic_store_management.resources;

import EntityPackage.ElectronicStoreDetails;
import EJBPackage.electronicStoreDetailsEJB;
import EJBPackage.electronicStoreFestivalEJB;
import EJBPackage.electronicStoreProductStockEJB;
import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreProductStock;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Date;

@Path("ElectronicStore")
@RequestScoped
public class ElectronicStoreResource {
    @EJB electronicStoreDetailsEJB stoobj;
    @EJB electronicStoreProductStockEJB stockobj;
    @EJB electronicStoreFestivalEJB festobj;

    // get all store
    @GET
    @Path("displayStore")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> displayElectronicStore() {
        return stoobj.displayElectronicStoreDetails();
    }

    // add store
    @POST
    @Path("addStore/{name}/{email}/{contactNo}/{password}/{storeLogo}/{address}/{country}")
    public void addElectronicStore(@PathParam("name") String name, @PathParam("email") String email, @PathParam("contactNo") Integer contact, @PathParam("password") String password, @PathParam("storeLogo") String logo, @PathParam("address") String address, @PathParam("country") String country) {
        stoobj.addElectronicStoreDetails(name, email, contact, password, logo, address, country);
    }

    @GET
    @Path("getStoreById/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> getStoreById(@PathParam("storeId") Integer storeId) {
        return stoobj.getDataByIdForUpdate(storeId);
    }

    // update store
    @POST
    @Path("updateStore/{storeId}/{name}/{email}/{contactNo}/{password}/{storeLogo}/{address}/{country}")
    public void updateElectronicStore(@PathParam("storeId") Integer storeId, @PathParam("name") String name, @PathParam("email") String email, @PathParam("contactNo") Integer contact, @PathParam("password") String password, @PathParam("storeLogo") String logo, @PathParam("address") String address, @PathParam("country") String country) {
        stoobj.updateElectronicStoreDetails(storeId, name, email, contact, password, logo, address, country);
    }

    @DELETE
    @Path("deleteStore/{storeId}")
    public void deleteElectronicStore(@PathParam("storeId") Integer storeId) {
        stoobj.deleteElectronicStoreDetails(storeId);
    }
    
    // Electronic Store Product Store
    @POST
    @Path("addstock/{pid}/{qty}")
    public void addStock(@PathParam("pid") Integer pid,@PathParam("qty") Integer qty)
    {
        stockobj.addStock(pid, qty);
    }
    
    @GET
    @Path("displaystock")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreProductStock> getAllStock()
    {
        return stockobj.getAllStock();
    }
    
    // display festival
    @GET
    @Path("displayFestival")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreFestival> displayFestival(){
        return festobj.displayFestival();
    }
    
    // add festival
    @POST
    @Path("addFestival/{festivalName}/{festivalDate}/{festivalDiscount}")
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreFestival> getFestivalById(@PathParam("festivalId") Integer festivalId){
        return festobj.getDataByIdForUpdate(festivalId);
    }
    
    // update festival
    @POST
    @Path("updateFestival/{festivalId}/{festivalName}/{festivalDate}/{festivalDiscount}")
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
    public void deleteFestival(@PathParam("festivalId") Integer festivalId){
        festobj.deleteFestival(festivalId);
    }
}
