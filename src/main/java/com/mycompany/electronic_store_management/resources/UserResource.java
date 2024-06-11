/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.electronic_store_management.resources;

import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import EntityPackage.UserFeedback;
import EntityPackage.UserOrderDetails;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Kartik Patel
 */
@Path("user")
@DeclareRoles("User")
@RequestScoped
public class UserResource {

    @EJB
    EJBPackage.userFeedBackEJB ufe;
    @EJB
    EJBPackage.userHomeDetailsEJB userHomeObj;
    @EJB
    EJBPackage.userDetailsEJB userDetailObj;
    @EJB
    EJBPackage.userOrderDetailsEJB userOrderObj;

    // add feedback
    @POST
    @Path("addfeedback/{message}/{fdate}/{uid}")
    @RolesAllowed("User")
    public void addFeedback(@PathParam("message") String message, @PathParam("fdate") String fdate, @PathParam("uid") Integer uid) {
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
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserFeedback> getAllFeedback() {
        return ufe.getAllFeedback();
    }

    // display all selling products to the users
    @GET
    @Path("getallsellingproducts")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreSellingProduct> getAllSellingProducts() {
        return userHomeObj.getAllSellingProducts();
    }

    @POST
    @Path("addUserDetail/{name}/{email}/{cno}/{pass}/{dob}/{gender}/{pic}/{address}/{country}")
    public void addUserDetails(@PathParam("name") String name, @PathParam("email") String email, @PathParam("cno") Integer cno, @PathParam("pass") String pass, @PathParam("dob") String dob, @PathParam("gender") String gender, @PathParam("pic") String pic, @PathParam("address") String address, @PathParam("country") String country) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(dob);
            userDetailObj.addUserDetails(name, email, cno, pass, mdate, gender, pic, address, country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("getuserbyid/{user_id}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDetails getUserById(@PathParam("user_id") Integer user_id) {
        return userDetailObj.getDataByIdForUpdate(user_id);
    }

    @POST
    @Path("updateUserDetail/{id}/{name}/{email}/{cno}/{pass}/{dob}/{gender}/{pic}/{address}/{country}")
    @RolesAllowed("User")
    public void updateUserDetails(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("email") String email, @PathParam("cno") Integer cno, @PathParam("pass") String pass, @PathParam("dob") String dob, @PathParam("gender") String gender, @PathParam("pic") String pic, @PathParam("address") String address, @PathParam("country") String country) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(dob);
            userDetailObj.updateUserDetails(id, name, email, cno, pass, mdate, gender, pic, address, country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // add user cart details
    @POST
    @Path("addcartdetails/{qty}/{selprod}/{userid}")
    @RolesAllowed("User")
    public void addCartDetails(@PathParam("qty") Integer qty, @PathParam("selprod") Integer selprod, @PathParam("userid") Integer userid) {
        userHomeObj.addCartDetails(qty, selprod, userid);
    }

    @GET
    @Path("getuserid/{email}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getUserId(@PathParam("email") String email) {
        return userDetailObj.getUserId(email);
    }

    @GET
    @Path("getcartdetails/{userid}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserCartDetails> getAllCartDetails(@PathParam("userid") Integer userid) {
        return userHomeObj.getAllCartDetails(userid);
    }

    // REST endpoint to update the quantity
    @POST
    @Path("updateincqty/{cartId}/{prodId}/{qty}/{userId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer updateQuantity(@PathParam("cartId") Integer cartId, @PathParam("prodId") Integer prodId, @PathParam("qty") Integer qty,@PathParam("userId") Integer userId) {
        return userHomeObj.increaseQuantity(cartId, prodId, qty,userId);
    }

    // update the quantity
    @POST
    @Path("updatedecqty/{cartId}/{userId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer decreaseQuantity(@PathParam("cartId") Integer cartId,@PathParam("userId") Integer userId) {
        return userHomeObj.decreaseQuantity(cartId,userId);
    }

    // get the store stock for checking
//    @GET
//    @Path("getstorequantity/{prodId}")
//    @RolesAllowed("User")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Integer getStoreStock(@PathParam("prodId") Integer prodId) {
//        return userHomeObj.getStoreStock(prodId);
//    }
    // get Cart Count
    @GET
    @Path("getcartcount/{cartId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getCartCount(@PathParam("cartId") Integer cartId) {
        return userHomeObj.getCartCount(cartId);
    }

    // remove cart item
    @DELETE
    @Path("removecartitem/{cartId}")
    @RolesAllowed("User")
    public void removeCartItem(@PathParam("cartId") Integer cartId) {
        userHomeObj.removeCartItem(cartId);
    }

    // add the user order
    @POST
    @Path("adduserorder/{qty}/{tamt}/{odate}/{selprodid}/{userid}")
    @RolesAllowed("User")
    public void addUserOrder(@PathParam("qty") Integer qty, @PathParam("tamt") Integer tamt, @PathParam("odate") String odate, @PathParam("selprodid") Integer selprodid, @PathParam("userid") Integer userid) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date odate1 = sdf.parse(odate);
            userOrderObj.addUserOrder(qty, tamt, odate1, selprodid, userid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // remove all the cart items of particular user
    @DELETE
    @Path("removeallcartitems/{userId}")
    @RolesAllowed("User")
    public void removeAllCartItems(@PathParam("userId") Integer userId) {
        userOrderObj.removeAllCartItems(userId);
    }

    // get user order of particular user
    @GET
    @Path("getuserorder/{userId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<UserOrderDetails> getUserOrder(@PathParam("userId") Integer userId) {
        return userOrderObj.getUserOrder(userId);
    }

    // get all festival offers
    @GET
    @Path("getallfestivaloffers")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreFestival> getAllFestivalOffers() {
        return userHomeObj.getAllFestivalOffers();
    }

    // searching the product and display to the user
    @GET
    @Path("getsearchsellingproducts/{prodId}")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ElectronicStoreSellingProduct> getSearchSellingProducts(@PathParam("prodId") Integer prodId) {
        return userHomeObj.getSearchSellingProducts(prodId);
    }

    // get All product for load products into the drop down
    @GET
    @Path("gelallproductforuser")
    @RolesAllowed("User")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getAllProductForUser() {
        return userHomeObj.getAllProductForUser();
    }
}
