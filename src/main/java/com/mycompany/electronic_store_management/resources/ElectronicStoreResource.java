package com.mycompany.electronic_store_management.resources;

import EntityPackage.ElectronicStoreDetails;
import EJBPackage.electronicStoreDetailsEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("ElectronicStore")
@RequestScoped
public class ElectronicStoreResource {
    @EJB
    electronicStoreDetailsEJB stoobj;

    @GET
    @Path("displayStore")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> displayElectronicStore() {
        return stoobj.displayElectronicStoreDetails();
    }

    @POST
    @Path("addStore/{name}/{email}/{contactNo}/{password}/{storeLogo}/{address}/{country}")
    public void addElectronicStore(@PathParam("name") String name, @PathParam("email") String email, @PathParam("contactNo") Integer contact, @PathParam("password") String password, @PathParam("storeLogo") String logo, @PathParam("address") String address, @PathParam("country") String country) {
        stoobj.addElectronicStoreDetails(name, email, contact, password, logo, address, country);
    }

    @GET
    @Path("getStoreById/{storeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreDetails> getStoreById(@PathParam("storeId") Integer storeId) {
        return stoobj.getDataByIdForUpdate(storeId);
    }

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
}
