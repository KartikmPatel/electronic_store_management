/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.UserDetails;
import EntityPackage.UserFeedback;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class userFeedBackEJB {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    // Store the feedback
    public void addFeedback(String message,Date fdate,Integer uid)
    {
        UserDetails ud = (UserDetails) em.find(UserDetails.class, uid);
        UserFeedback uf = new UserFeedback();
        uf.setMessage(message);
        uf.setFeedbackDate(fdate);
        uf.setUserId(ud);
        em.persist(uf);
    }
    
    // display the feedback
    public Collection<UserFeedback> getAllFeedback()
    {
        Collection<UserFeedback> uf = em.createNamedQuery("UserFeedback.findAll").getResultList();
        return uf;
    }
}
