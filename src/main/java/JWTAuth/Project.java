/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package JWTAuth;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author leap
 */
@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "jdbc/electronicStoreResource",
    callerQuery = "select password from users where email=?",
    groupsQuery = "select groupname from groups where email=?",
    hashAlgorithm = Pbkdf2PasswordHash.class,
    priority = 30
)
@ApplicationScoped
public class Project {

    /**
     * Creates a new instance of Project
     */
    public Project() {
    }
    
}
