/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package RestClientPackage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:UserGroupResource
 * [UserGroup]<br>
 * USAGE:
 * <pre>
 *        userGroupClient client = new userGroupClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Admin
 */
public class userGroupClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/electronic_store_management/resources";

    public userGroupClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("UserGroup");
    }

    public void addUser(String email, String password) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addUser/{0}/{1}", new Object[]{email, password})).request().post(null);
    }

    public void addGroup(String groupName, String email) throws ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("addGroup/{0}/{1}", new Object[]{groupName, email})).request().post(null);
    }

    public void close() {
        client.close();
    }
    
}
