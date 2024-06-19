/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.electronic_store_management.resources;

import EJBPackage.userGroupEJB;
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
 * @author Admin
 */
@Path("UserGroup")
@RequestScoped
public class UserGroupResource {
    @EJB userGroupEJB ugobj;
    
    @POST
    @Path("addUser/{email}/{password}")
    public void addUser(@PathParam("email") String email, @PathParam("password") String password){
        ugobj.addUser(email, password);
    }
    
    @POST
    @Path("addGroup/{groupName}/{email}")
    public void addGroup(@PathParam("groupName") String groupName,@PathParam("email") String email){
        ugobj.addGroup(groupName,email);
    }
    
    @POST
    @Path("changePassword/{email}/{password}")
    public void changePassword(@PathParam("email") String email,@PathParam("password") String password){
        ugobj.changePassword(email, password);
    }
    
    @GET
    @Path("checkEmail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean checkEmail(@PathParam("email") String email){
        return ugobj.emailExists(email);
    }
}
