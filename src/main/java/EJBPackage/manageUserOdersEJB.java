/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import EntityPackage.UserFeedback;
import EntityPackage.UserOrderDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class manageUserOdersEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    // display all userOrder for store
    public Collection<UserOrderDetails> getAllUserOrder() {
        Collection<UserOrderDetails> userOrder = em.createNamedQuery("UserOrderDetails.findAll").getResultList();
        return userOrder;
    }

    // verify user order at store side
    public void verifyUserOrder(Integer status,Integer orderId) {
        UserOrderDetails odetail = (UserOrderDetails) em.find(UserOrderDetails.class, orderId);
        odetail.setStatus(status);
        em.merge(odetail);
    }
    
    // display the feedback
    public Collection<UserFeedback> getAllFeedback()
    {
        Collection<UserFeedback> uf = em.createNamedQuery("UserFeedback.findAll").getResultList();
        return uf;
    }
    
    // cut the product stock of store
    public void cutStoreProductStock(Integer qty,Integer prodId)
    {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, prodId);
        ElectronicStoreProductStock productStock = em.createQuery("SELECT e FROM ElectronicStoreProductStock e WHERE e.productId = :productId",ElectronicStoreProductStock.class).setParameter("productId", pd).getSingleResult();
        
        Integer totalQty = productStock.getQuantity() - qty;
        productStock.setQuantity(totalQty);
        em.merge(productStock);
    }
}
