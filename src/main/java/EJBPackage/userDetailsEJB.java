/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.UserDetails;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class userDetailsEJB {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public void addUserDetails(String name, String email, Integer cno, String pass, Date dob, String gender, String pic, String address, String country){
        UserDetails model = new UserDetails();
        model.setUsername(name);
        model.setEmail(email);
        model.setContactNo(cno);
        model.setPassword(pass);
        model.setDob(dob);
        model.setGender(gender);
        model.setProfilePic(pic);
        model.setAddress(address);
        model.setCountry(country);
        em.persist(model);
    }
    
     public UserDetails getDataByIdForUpdate(Integer userId) {
        return em.createNamedQuery("UserDetails.findByUserId",UserDetails.class).setParameter("userId", userId).getSingleResult();
    }
    
    public void updateUserDetails(Integer id, String name, String email, Integer cno, String pass, Date dob, String gender, String pic, String address, String country){
        UserDetails model = em.find(UserDetails.class, id);
        model.setUsername(name);
        model.setEmail(email);
        model.setContactNo(cno);
        model.setPassword(pass);
        model.setDob(dob);
        model.setGender(gender);
        model.setProfilePic(pic);
        model.setAddress(address);
        model.setCountry(country);
        em.merge(model);
    }

    // get user id
    public Integer getUserId(String email) {
        Integer userId = (Integer) em.createQuery("SELECT c.userId FROM UserDetails c WHERE c.email = :email")
                                    .setParameter("email", email)
                                    .getSingleResult();
        return userId;
    }
}
