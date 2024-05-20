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
 * @author Admin
 */
public class userClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/electronic_store_management/resources";

    public userClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }

    public <T> T getAllSellingProducts(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getallsellingproducts");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void addFeedback(String message, String fdate, String uid) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addfeedback/{0}/{1}/{2}", new Object[]{message, fdate, uid})).request().post(null);
    }

    public void addUserDetails(String name, String email, String cno, String pass, String dob, String gender, String pic, String address, String country) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUserDetail/{0}/{1}/{2}/{3}/{4}/{5}/{6}/{7}/{8}", new Object[]{name, email, cno, pass, dob, gender, pic, address, country})).request().post(null);
    }

    public <T> T getAllFeedback(Class<T> responseType) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("displayallfeedback");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    public void close() {
        client.close();
    }
    
}
