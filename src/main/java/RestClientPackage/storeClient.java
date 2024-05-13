/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ElectronicStoreResource
 * [ElectronicStore]<br>
 * USAGE:
 * <pre>
 *        storeClient client = new storeClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Kartik Patel
 */
public class storeClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/electronic_store_management/resources";

    public storeClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("ElectronicStore");
    }

    public <T> T getStoreSellingProductCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getstoresellingproductcount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteFestival(String festivalId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteFestival/{0}", new Object[]{festivalId})).request().delete();
    }

    public <T> T getQuantity(Class<T> responseType, String prodid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getquantity/{0}", new Object[]{prodid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getElectronicProductCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getEleProductCount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void deleteElectronicStore(String storeId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("deleteStore/{0}", new Object[]{storeId})).request().delete();
    }

    public <T> T getAllStock(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displaystock");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getProductById(Class<T> responseType, String prodid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getptoductbyid/{0}", new Object[]{prodid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllOrders(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallorders");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStoreProductStoreForSelling(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getstoreproductstoreforselling");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSellingProduct(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallsellingproduct");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addStoreSellingProductData(String price, String pid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addstoresellingproduct/{0}/{1}", new Object[]{price, pid})).request().post(null);
    }

    public void addStock(String pid, String qty) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addstock/{0}/{1}", new Object[]{pid, qty})).request().post(null);
    }

    public <T> T getPriceOfProduct(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getpriceofproduct/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addOrder(String qty, String billamt, String odate, String status, String prodid, String comid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addorder/{0}/{1}/{2}/{3}/{4}/{5}", new Object[]{qty, billamt, odate, status, prodid, comid})).request().post(null);
    }

    public void updateFestival(String festivalId, String festivalName, String festivalDate, String festivalDiscount) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateFestival/{0}/{1}/{2}/{3}", new Object[]{festivalId, festivalName, festivalDate, festivalDiscount})).request().post(null);
    }

    public <T> T getEleStoreOrderCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getelestoreordercount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getOfferCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getoffercount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T checkSellingProduct(Class<T> responseType, String pid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("checksellingproduct/{0}", new Object[]{pid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStoreById(Class<T> responseType, String storeId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getStoreById/{0}", new Object[]{storeId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getFestivalById(Class<T> responseType, String festivalId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getFestivalById/{0}", new Object[]{festivalId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void updateElectronicStore(String storeId, String name, String email, String contactNo, String password, String storeLogo, String address, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateStore/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}", new Object[]{storeId, name, email, contactNo, password, storeLogo, address, country})).request().post(null);
    }

    public <T> T getAllProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallproducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getStoreStockCount(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getStoreStockCount");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addFestival(String festivalName, String festivalDate, String festivalDiscount) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addFestival/{0}/{1}/{2}", new Object[]{festivalName, festivalDate, festivalDiscount})).request().post(null);
    }

    public <T> T displayElectronicStore(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayStore");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T displayFestival(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayFestival");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addElectronicStore(String name, String email, String contactNo, String password, String storeLogo, String address, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addStore/{0}/{1}/{2}/{3}/{4}/{5}/{6}", new Object[]{name, email, contactNo, password, storeLogo, address, country})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
