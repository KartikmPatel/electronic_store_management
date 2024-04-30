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
@Path("Company")
@RequestScoped
public class CompanyResource {

    @EJB EJBPackage.companyDetailsEJB cde;
    @EJB EJBPackage.categoryDetailsEJB catobj;
    @EJB EJBPackage.productDetailsEJB prodobj;
    @EJB EJBPackage.companyProductStockEJB cpsobj;
    
    @POST
    @Path("addcompany/{com_name}/{email}/{cno}/{password}/{clogo}/{country}")
    public void addCompany(@PathParam("com_name") String cname,@PathParam("email") String email,@PathParam("cno") Integer cno,@PathParam("password") String password,@PathParam("clogo") String clogo,@PathParam("country") String country)
    {
        cde.addCompanyDetails(cname, email, cno, password, clogo, country);
    }
    
    @GET
    @Path("companyLogin/{email}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean checkCompanyLogin(@PathParam("email") String email, @PathParam("password") String password){
        return cde.checkLogin(email, password);
    }
    
    @GET
    @Path("getallcompany")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyDetails> getAllCompany()
    {
        return cde.displayCompanyDetails();
    }
    
    @DELETE
    @Path("deletecompany/{com_id}")
    public void deleteCompany(@PathParam("com_id") Integer com_id)
    {
        cde.deleteCompanyDetails(com_id);
    }
    
    @GET
    @Path("getcombyid/{com_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CompanyDetails> getCompanyById(@PathParam("com_id") Integer com_id)
    {
        return cde.getDataByIdForUpdate(com_id);
    }
    
    @POST
    @Path("updatecompany/{com_id}/{com_name}/{email}/{cno}/{password}/{clogo}/{country}")
    public void updateCompany(@PathParam("com_id") Integer com_id,@PathParam("com_name") String cname,@PathParam("email") String email,@PathParam("cno") Integer cno,@PathParam("password") String password,@PathParam("clogo") String clogo,@PathParam("country") String country)
    {
        cde.updateCompanyDetails(com_id, cname, email, cno, password, clogo, country);
    }
    
    @POST
    @Path("addcategory/{cat_name}/{com_id}")
    public void addCategory(@PathParam("cat_name") String cname,@PathParam("com_id") Integer cid)
    {
        catobj.addcategorydetails(cname, cid);
    }
    
    @GET
    @Path("getallcategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CategoryDetails> getAllCategory()
    {
        return catobj.getAllCategory();
    }
    
    @DELETE
    @Path("deletecategory/{cat_id}")
    public void deleteCategory(@PathParam("cat_id") Integer cat_id)
    {
        catobj.deleteCategory(cat_id);
    }
    
    @GET
    @Path("getcatbyid/{cat_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CategoryDetails> getCategoryById(@PathParam("cat_id") Integer cat_id)
    {
        return catobj.getCategoryByCatid(cat_id);
    }
    
    @POST
    @Path("updatecategory/{cat_id}/{cat_name}/{com_id}")
    public void updateCategory(@PathParam("cat_id") Integer cat_id,@PathParam("cat_name") String cname,@PathParam("com_id") Integer cid)
    {
        catobj.updateCategory(cat_id, cname, cid);
    }
    
    @POST
    @Path("addproduct/{prod_name}/{discount}/{price}/{pimage}/{mfg_date}/{warranty}/{cat_id}/{com_id}")
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
    
    @GET
    @Path("getallproduct")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getAllProduct()
    {
        return prodobj.getAllProducts();
    }
    
    @DELETE
    @Path("deleteproduct/{prod_id}")
    public void deleteProduct(@PathParam("prod_id") Integer pid)
    {
        prodobj.deleteProduct(pid);
    }
    
    @GET
    @Path("getprodbyid/{prod_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<ProductDetails> getProductById(@PathParam("prod_id") Integer prod_id)
    {
        return prodobj.getProductById(prod_id);
    }
    
    @POST
    @Path("updateproduct/{pid}/{prod_name}/{discount}/{price}/{pimage}/{mfg_date}/{warranty}/{cat_id}/{com_id}")
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
    
    @GET
    @Path("displayCompanyProStock")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CompanyProductStock> displayCompanyProductStock(){
        return cpsobj.displayCompanyProductStock();
    }
    
    @POST
    @Path("addCompanyProStock/{quan}/{prodid}")
    public void addCompanyProductStock(@PathParam("quan") Integer quan, @PathParam("prodid") Integer prodid){
        cpsobj.addCompanyProductStock(quan, prodid);
    }
    
    @GET
    @Path("getaddCompanyProStockById/{companyStokeId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Collection<CompanyProductStock> getCompanyStockById(@PathParam("companyStokeId") Integer companyStokeId){
        return cpsobj.getDataByIdForUpdate(companyStokeId);
    }
    
    @POST
    @Path("updateCompanyProStock/{companyStokeId}/{quan}/{prodid}")
    public void updateCompanyProductStock(@PathParam("companyStokeId") Integer companyStokeId, @PathParam("quan") Integer quan, @PathParam("prodid") Integer prodid){
        cpsobj.updateCompanyProductStock(companyStokeId, quan, prodid);
    }
    
    @DELETE
    @Path("deleteCompanyProStock/{companyStokeId}")
    public void deleteCompanyProductStock(@PathParam("companyStokeId") Integer companyStokeId){
        cpsobj.deleteCompanyProductStock(companyStokeId);
    }
}
