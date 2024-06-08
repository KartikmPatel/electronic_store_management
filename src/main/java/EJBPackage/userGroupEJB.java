/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CompanyDetails;
import EntityPackage.ElectronicStoreDetails;
import EntityPackage.Groups;
import EntityPackage.UserDetails;
import EntityPackage.Users;
import java.util.List;
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
    
    public void changePassword(String email, String password) {
        System.out.println("*****4*64656546*56*46*" + email + password + "*****4*64656546*56*46*");

        // Check and update password in CompanyDetails
        List<CompanyDetails> companyDetailsList = em.createNamedQuery("CompanyDetails.findByEmail", CompanyDetails.class)
                                                    .setParameter("email", email)
                                                    .getResultList();

        if (!companyDetailsList.isEmpty()) {
            CompanyDetails cd = companyDetailsList.get(0);
            cd.setPassword(password);
            System.out.println("Updated CompanyDetails Password: " + cd);
            em.merge(cd);
        }
        
        // Check and update password in StroeDetails
        List<ElectronicStoreDetails> storeDetailsList = em.createNamedQuery("ElectronicStoreDetails.findByEmail", ElectronicStoreDetails.class)
                                                    .setParameter("email", email)
                                                    .getResultList();

        if (!storeDetailsList.isEmpty()) {
            ElectronicStoreDetails ed = storeDetailsList.get(0);
            ed.setPassword(password);
            System.out.println("Updated StoreDetails Password: " + ed);
            em.merge(ed);
        }

        // Check and update password in UserDetails
        List<UserDetails> userDetailsList = em.createNamedQuery("UserDetails.findByEmail", UserDetails.class)
                                              .setParameter("email", email)
                                              .getResultList();

        if (!userDetailsList.isEmpty()) {
            UserDetails ud = userDetailsList.get(0);
            ud.setPassword(password);
            System.out.println("Updated UserDetails Password: " + ud);
            em.merge(ud);
        }

        // Always update password in Users entity
        Users user = em.find(Users.class, email);

        if (user != null) {
            Pbkdf2PasswordHashImpl pb = new Pbkdf2PasswordHashImpl();
            String hash = pb.generate(password.toCharArray());
            user.setPassword(hash);
            System.out.println("Updated User Password: " + user);
            em.merge(user);
        } else {
            System.out.println("No Users entity found with email: " + email);
        }
    }
}
