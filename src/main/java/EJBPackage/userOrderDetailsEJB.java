/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import EntityPackage.UserOrderDetails;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class userOrderDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    // add the user order
    public void addUserOrder(Integer qty, Integer totalamt, Date odate, Integer selProdId, Integer userId) {
        UserDetails ud = (UserDetails) em.find(UserDetails.class, userId);
        ElectronicStoreSellingProduct selProductId = (ElectronicStoreSellingProduct) em.find(ElectronicStoreSellingProduct.class, selProdId);

        UserOrderDetails orderDetails = new UserOrderDetails();
        orderDetails.setQuantity(qty);
        orderDetails.setTotalAmount(totalamt);
        orderDetails.setOrderDate(odate);
        orderDetails.setStatus(0);
        orderDetails.setSellingProductId(selProductId);
        orderDetails.setUserId(ud);
        em.persist(orderDetails);
    }

    // Remove all the cart items of a particular user
    public void removeAllCartItems(Integer userId) {
        UserDetails user = em.find(UserDetails.class, userId);
        if (user != null) {
            TypedQuery<UserCartDetails> query = em.createQuery(
                    "SELECT u FROM UserCartDetails u WHERE u.userId = :userId",
                    UserCartDetails.class
            ).setParameter("userId", user);

            Collection<UserCartDetails> cartDetailsList = query.getResultList();

            // Remove each cart item
            for (UserCartDetails cartDetail : cartDetailsList) {
                em.remove(cartDetail);
            }
        }
    }
    
    // get user order of particular user
    public Collection<UserOrderDetails> getUserOrder(Integer userId)
    {
        UserDetails ud = (UserDetails) em.find(UserDetails.class, userId);
        Collection<UserOrderDetails> userOrder = em.createQuery("SELECT u FROM UserOrderDetails u WHERE u.userId = :userId",UserOrderDetails.class).setParameter("userId", ud).getResultList();
        
        return userOrder;
    }  
}
