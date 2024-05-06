/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.Groups;
import EntityPackage.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author Admin
 */
@Stateless
public class userGroupEJB {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public void addUser(String email, String password){
        Users model = new Users();
        Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();
        String hash = pb.generate(password.toCharArray());
        model.setEmail(email);
        model.setPassword(hash);
        em.persist(model);
    }
    
    public void addGroup(String groupName, String email){
        Groups model = new Groups();
        model.setGroupname(groupName);
        model.setEmail(email);
        em.persist(model);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
