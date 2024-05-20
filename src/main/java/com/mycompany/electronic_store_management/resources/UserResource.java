/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.electronic_store_management.resources;

import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.UserFeedback;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Kartik Patel
 */
@Path("user")
@RequestScoped
public class UserResource {
    
    @EJB EJBPackage.userFeedBackEJB ufe;
    @EJB EJBPackage.userHomeDetailsEJB usetHomeObj;
    @EJB EJBPackage.userDetailsEJB userDetailObj;
    
    // add feedback
    @POST
    @Path("addfeedback/{message}/{fdate}/{uid}")
    public void addFeedback(@PathParam("message") String message,@PathParam("fdate") String fdate,@PathParam("uid") Integer uid)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fdate1 = sdf.parse(fdate);
            ufe.addFeedback(message, fdate1, uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // display feedback
    @GET
    @Path("displayallfeedback")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserFeedback> getAllFeedback()
    {
        return ufe.getAllFeedback();
    }
    
    // display all selling products to the users
    @GET
    @Path("getallsellingproducts")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreSellingProduct> getAllSellingProducts()
    {
        return usetHomeObj.getAllSellingProducts();
    }
    
    @POST
    @Path("addUserDetail/{name}/{email}/{cno}/{pass}/{dob}/{gender}/{pic}/{address}/{country}")
    public void addUserDetails(@PathParam("name") String name, @PathParam("email") String email, @PathParam("cno") Integer cno, @PathParam("pass") String pass, @PathParam("dob") String dob, @PathParam("gender") String gender, @PathParam("pic") String pic, @PathParam("address") String address, @PathParam("country") String country){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(dob);
            userDetailObj.addUserDetails(name, email, cno, pass, mdate, gender, pic, address, country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
