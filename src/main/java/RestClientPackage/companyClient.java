/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:CompanyResource [Company]<br>
 * USAGE:
 * <pre>
 *        companyClient client = new companyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Kartik Patel
 */
public class companyClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/electronic_store_management/resources";

    public companyClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("Company");
    }

    public <T> T getAllCompany(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallcompany");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProdIdByNameAndComId(Class<T> responseType, String pname, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getprodidbynameandcomid/{0}/{1}", new Object[]{pname, comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addProduct(String prod_name, String discount, String price, String pimage, String mfg_date, String warranty, String cat_id, String com_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addproduct/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{prod_name, discount, price, pimage, mfg_date, warranty, cat_id, com_id})).request().post(null);
    }

    public void updateProduct(String pid, String prod_name, String discount, String price, String pimage, String mfg_date, String warranty, String cat_id, String com_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateproduct/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{pid, prod_name, discount, price, pimage, mfg_date, warranty, cat_id, com_id})).request().post(null);
    }

    public void updateCategory(String cat_id, String cat_name, String com_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatecategory/{0}/{1}/{2}", new Object[]{cat_id, cat_name, com_id})).request().post(null);
    }

    public void updateCompany(String com_id, String com_name, String email, String cno, String password, String clogo, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatecompany/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{com_id, com_name, email, cno, password, clogo, country})).request().post(null);
    }

    public <T> T getProductCount(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getproductcount/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayCompanyProductStock(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("displayCompanyProStock/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addCategory1(String cat_name, String com_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addcategory/{0}/{1}", new Object[]{cat_name, com_id})).request().post(null);
    }

    public <T> T getProductById(Class<T> responseType, String prod_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getprodbyid/{0}", new Object[]{prod_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String path() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("p1");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public <T> T checkCompanyLogin(Class<T> responseType, String email, String password) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("companyLogin/{0}/{1}", new Object[]{email, password}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteCompanyProductStock(String companyStokeId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteCompanyProStock/{0}", new Object[]{companyStokeId})).request().delete();
    }

    public <T> T getStoreOrders(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getorderbycomid/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateCompanyProductStock(String companyStokeId, String quan, String prodid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateCompanyProStock/{0}/{1}/{2}", new Object[]{companyStokeId, quan, prodid})).request().post(null);
    }

    public void deleteProduct(String prod_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteproduct/{0}", new Object[]{prod_id})).request().delete();
    }

    public <T> T getStoreOrderCount(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getstoreordercount/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCompanyProductStockCount(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcompanyproductstockcount/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteCompany(String com_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletecompany/{0}", new Object[]{com_id})).request().delete();
    }

    public void addElectronicStoreProductStock(String prodid, String qty) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addstroreproductstock/{0}/{1}", new Object[]{prodid, qty})).request().post(null);
    }

    public void deleteCategory(String cat_id) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deletecategory/{0}", new Object[]{cat_id})).request().delete();
    }

    public <T> T getCompanyStockById(Class<T> responseType, String companyStokeId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getaddCompanyProStockById/{0}", new Object[]{companyStokeId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getCategoryById(Class<T> responseType, String cat_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcatbyid/{0}", new Object[]{cat_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public String path2() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("p2");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public <T> T getCompanyId(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getCompanyId/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addCompany(String com_name, String email, String cno, String password, String clogo, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addcompany/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{com_name, email, cno, password, clogo, country})).request().post(null);
    }

    public <T> T getCompanyById(Class<T> responseType, String com_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcombyid/{0}", new Object[]{com_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addCompanyProductStock(String quan, String prodid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addCompanyProStock/{0}/{1}", new Object[]{quan, prodid})).request().post(null);
    }

    public void changeStatus(String status, String oid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("changestatus/{0}/{1}", new Object[]{status, oid})).request().post(null);
    }

    public <T> T getAllCategory(Class<T> responseType, String com_id) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getallcategory/{0}", new Object[]{com_id}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllProduct(Class<T> responseType, String companyId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getallproduct/{0}", new Object[]{companyId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void minusCompanyProductStock(String qty, String prodid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("minuscompanyproductstock/{0}/{1}", new Object[]{qty, prodid})).request().post(null);
    }

    public void editComapnyProductStock(String prodid, String qty) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("editcompanyproductstock/{0}/{1}", new Object[]{prodid, qty})).request().post(null);
    }

    public <T> T getCategoryCount(Class<T> responseType, String comid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcategorycount/{0}", new Object[]{comid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
