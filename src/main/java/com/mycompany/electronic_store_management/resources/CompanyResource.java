/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.mycompany.electronic_store_management.resources;

import EJBPackage.companyProductStockEJB;
import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import EntityPackage.CompanyProductStock;
import EntityPackage.ProductDetails;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Kartik Patel
 */
@Path("Company")
@DeclareRoles({"Admin","User","Company"})
@RequestScoped
public class CompanyResource {

    @EJB EJBPackage.companyDetailsEJB cde;
    @EJB EJBPackage.categoryDetailsEJB catobj;
    @EJB EJBPackage.productDetailsEJB prodobj;
    @EJB EJBPackage.companyProductStockEJB cpsobj;
    
    @GET
    @Path("p1")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public String path(){
        return "admin allowed";
    }
    @GET
    @Path("p2")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    public String path2(){
        return "User allowed";
    }
  
    // add company
    @POST
    @Path("addcompany/{com_name}/{email}/{cno}/{password}/{clogo}/{country}")
    public void addCompany(@PathParam("com_name") String cname,@PathParam("email") String email,@PathParam("cno") Integer cno,@PathParam("password") String password,@PathParam("clogo") String clogo,@PathParam("country") String country)
    {
        cde.addCompanyDetails(cname, email, cno, password, clogo, country);
    }
    
    // company login
    @GET
    @Path("companyLogin/{email}/{password}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean checkCompanyLogin(@PathParam("email") String email, @PathParam("password") String password){
        return cde.checkLogin(email, password);
    }
    
    // get all company
    @GET
    @Path("getallcompany")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyDetails> getAllCompany()
    {
        return cde.displayCompanyDetails();
    }
    
    @DELETE
    @Path("deletecompany/{com_id}")
    @RolesAllowed("Company")
    public void deleteCompany(@PathParam("com_id") Integer com_id)
    {
        cde.deleteCompanyDetails(com_id);
    }
    
    @GET
    @Path("getcombyid/{com_id}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CompanyDetails> getCompanyById(@PathParam("com_id") Integer com_id)
    {
        return cde.getDataByIdForUpdate(com_id);
    }
    
    // update company
    @POST
    @Path("updatecompany/{com_id}/{com_name}/{email}/{cno}/{password}/{clogo}/{country}")
    @RolesAllowed("Company")
    public void updateCompany(@PathParam("com_id") Integer com_id,@PathParam("com_name") String cname,@PathParam("email") String email,@PathParam("cno") Integer cno,@PathParam("password") String password,@PathParam("clogo") String clogo,@PathParam("country") String country)
    {
        cde.updateCompanyDetails(com_id, cname, email, cno, password, clogo, country);
    }
    
    // add category
    @POST
    @Path("addcategory/{cat_name}/{com_id}")
    @RolesAllowed("Company")
    public void addCategory1(@PathParam("cat_name") String cname,@PathParam("com_id") Integer cid)
    {
        catobj.addcategorydetails(cname, cid);
    }
    
    // get all category by company id
    @GET
    @Path("getallcategory/{com_id}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CategoryDetails> getAllCategory(@PathParam("com_id") Integer com_id)
    {
        return catobj.getAllCategory(com_id);
    }
    
    @DELETE
    @Path("deletecategory/{cat_id}")
    @RolesAllowed("Company")
    public void deleteCategory(@PathParam("cat_id") Integer cat_id)
    {
        catobj.deleteCategory(cat_id);
    }
    
    @GET
    @Path("getcatbyid/{cat_id}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CategoryDetails> getCategoryById(@PathParam("cat_id") Integer cat_id)
    {
        return catobj.getCategoryByCatid(cat_id);
    }
    
    // update category
    @POST
    @Path("updatecategory/{cat_id}/{cat_name}/{com_id}")
    @RolesAllowed("Company")
    public void updateCategory(@PathParam("cat_id") Integer cat_id,@PathParam("cat_name") String cname,@PathParam("com_id") Integer cid)
    {
        catobj.updateCategory(cat_id, cname, cid);
    }
    
    // add product
    @POST
    @Path("addproduct/{prod_name}/{discount}/{price}/{pimage}/{mfg_date}/{warranty}/{cat_id}/{com_id}")
    @RolesAllowed("Company")
    public void addProduct(@PathParam("prod_name") String pname,@PathParam("discount") Integer dis,@PathParam("price") Integer price,@PathParam("pimage") String pimage,@PathParam("mfg_date") String mfg_date,@PathParam("warranty") String war,@PathParam("cat_id") Integer cat_id,@PathParam("com_id") Integer com_id)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(mfg_date);
            prodobj.addProductDetails(pname, dis, price, pimage, mdate, war, cat_id, com_id);
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    // get all product by company id
    @GET
    @Path("getallproduct/{companyId}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getAllProduct(@PathParam("companyId") Integer companyId)
    {
        return prodobj.getAllProducts(companyId);
    }
    
    // get prod id by name and company id
    @GET
    @Path("getprodidbynameandcomid/{pname}/{comid}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public ProductDetails getProdIdByNameAndComId(@PathParam("pname") String pname,@PathParam("comid") Integer comid)
    {
        return cpsobj.getProdIdByNameAndCompanyId(pname, comid);
    }
    
    @DELETE
    @Path("deleteproduct/{prod_id}")
    @RolesAllowed("Company")
    public void deleteProduct(@PathParam("prod_id") Integer pid)
    {
        prodobj.deleteProduct(pid);
    }
    
    @GET
    @Path("getprodbyid/{prod_id}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getProductById(@PathParam("prod_id") Integer prod_id)
    {
        return prodobj.getProductById(prod_id);
    }
    
    // update product
    @POST
    @Path("updateproduct/{pid}/{prod_name}/{discount}/{price}/{pimage}/{mfg_date}/{warranty}/{cat_id}/{com_id}")
    @RolesAllowed("Company")
    public void updateProduct(@PathParam("pid") Integer pid,@PathParam("prod_name") String pname,@PathParam("discount") Integer dis,@PathParam("price") Integer price,@PathParam("pimage") String pimage,@PathParam("mfg_date") String mfg_date,@PathParam("warranty") String war,@PathParam("cat_id") Integer cat_id,@PathParam("com_id") Integer com_id)
    {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date mdate = sdf.parse(mfg_date);
            prodobj.updateProduct(pid, pname, dis, price, pimage, mdate, war, cat_id, com_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // get stock by company id
    @GET
    @Path("displayCompanyProStock/{comid}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyProductStock> displayCompanyProductStock(@PathParam("comid") Integer comid){
        return cpsobj.displayCompanyProductStock(comid);
    }
    
    // add company stock
    @POST
    @Path("addCompanyProStock/{quan}/{prodid}")
    @RolesAllowed("Company")
    public void addCompanyProductStock(@PathParam("quan") Integer quan, @PathParam("prodid") Integer prodid){
        cpsobj.addCompanyProductStock(quan, prodid);
    }
    
    @GET
    @Path("getaddCompanyProStockById/{companyStokeId}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CompanyProductStock> getCompanyStockById(@PathParam("companyStokeId") Integer companyStokeId){
        return cpsobj.getDataByIdForUpdate(companyStokeId);
    }
    
    // update the company stock
    @POST
    @Path("updateCompanyProStock/{companyStokeId}/{quan}/{prodid}")
    @RolesAllowed("Company")
    public void updateCompanyProductStock(@PathParam("companyStokeId") Integer companyStokeId, @PathParam("quan") Integer quan, @PathParam("prodid") Integer prodid){
        cpsobj.updateCompanyProductStock(companyStokeId, quan, prodid);
    }
    
    @DELETE
    @Path("deleteCompanyProStock/{companyStokeId}")
    @RolesAllowed("Company")
    public void deleteCompanyProductStock(@PathParam("companyStokeId") Integer companyStokeId){
        cpsobj.deleteCompanyProductStock(companyStokeId);
    }
    
    // count Category for the company dashboard
    @GET
    @Path("getcategorycount/{comid}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getCategoryCount(@PathParam("comid") Integer comid)
    {
        return cde.getCategoryCount(comid);
    }
    
    // count Product for the company dashboard
    @GET
    @Path("getproductcount/{comid}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getProductCount(@PathParam("comid") Integer comid)
    {
        return cde.getProductsCount(comid);
    }
    
    // count company product stock for the company dashboard
    @GET
    @Path("getcompanyproductstockcount/{comid}")
    @RolesAllowed("Company")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getCompanyProductStockCount(@PathParam("comid") Integer comid)
    {
        return cde.getCompanyProductStockCount(comid);
    }
    
    // get user id by email
    @GET
    @Path("getCompanyId/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer getCompanyId(@PathParam("email") String email) {
        return cde.getCompanyId(email);
    }
}
