/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserResource [user]<br>
 * USAGE:
 * <pre>
 *        userClient client = new userClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Kartik Patel
 */
public class userClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/electronic_store_management/resources";

    public userClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }

    public <T> T getStoreStock(Class<T> responseType, String prodId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getstorequantity/{0}", new Object[]{prodId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addUserOrder(String qty, String tamt, String odate, String selprodid, String userid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("adduserorder/{0}/{1}/{2}/{3}/{4}", new Object[]{qty, tamt, odate, selprodid, userid})).request().post(null);
    }

    public void removeCartItem(String cartId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removecartitem/{0}", new Object[]{cartId})).request().delete();
    }

    public void removeAllCartItems(String userId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("removeallcartitems/{0}", new Object[]{userId})).request().delete();
    }

    public void addFeedback(String message, String fdate, String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addfeedback/{0}/{1}/{2}", new Object[]{message, fdate, uid})).request().post(null);
    }

    public <T> T getCartCount(Class<T> responseType, String cartId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcartcount/{0}", new Object[]{cartId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getUserOrder(Class<T> responseType, String userId) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getuserorder/{0}", new Object[]{userId}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllCartDetails(Class<T> responseType, String userid) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getcartdetails/{0}", new Object[]{userid}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void decreaseQuantity(String cartId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updatedecqty/{0}", new Object[]{cartId})).request().post(null);
    }

    public <T> T getAllFestivalOffers(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallfestivaloffers");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public <T> T getAllSellingProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallsellingproducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addUserDetails(String name, String email, String cno, String pass, String dob, String gender, String pic, String address, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUserDetail/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{name, email, cno, pass, dob, gender, pic, address, country})).request().post(null);
    }

    public <T> T getAllFeedback(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayallfeedback");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void increaseQuantity(String cartId) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("updateincqty/{0}", new Object[]{cartId})).request().post(null);
    }

    public <T> T getUserId(Class<T> responseType, String email) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("getuserid/{0}", new Object[]{email}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addCartDetails(String qty, String selprod, String userid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addcartdetails/{0}/{1}/{2}", new Object[]{qty, selprod, userid})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
