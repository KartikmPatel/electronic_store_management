/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreFestival;
import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import EntityPackage.UserCartDetails;
import EntityPackage.UserDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class userHomeDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    // display the products to the user
    public Collection<ElectronicStoreSellingProduct> getAllSellingProducts() {
        Collection<ElectronicStoreSellingProduct> sellingProd = em.createQuery(
                "SELECT e FROM ElectronicStoreSellingProduct e "
                + "WHERE e.productId IN ("
                + "   SELECT s.productId FROM ElectronicStoreProductStock s "
                + "   WHERE s.quantity > 0"
                + ")",
                ElectronicStoreSellingProduct.class
        ).getResultList();
        return sellingProd;
    }

    // add user cart details
    public void addCartDetails(Integer qty, Integer selprodid, Integer userid) {
        ElectronicStoreSellingProduct selprod = (ElectronicStoreSellingProduct) em.find(ElectronicStoreSellingProduct.class, selprodid);
        UserDetails ud = (UserDetails) em.find(UserDetails.class, userid);

        Collection<UserCartDetails> ucd = em.createQuery("SELECT u FROM UserCartDetails u WHERE u.userId = :userId AND u.sellingProductId=:selProdId", UserCartDetails.class).setParameter("userId", ud).setParameter("selProdId", selprod).getResultList();

        if (!ucd.isEmpty()) {
            System.out.println("Product already exists in the cart");
        } else {
            UserCartDetails cartdetails = new UserCartDetails();
            cartdetails.setQuantity(qty);
            cartdetails.setSellingProductId(selprod);
            cartdetails.setUserId(ud);
            em.persist(cartdetails);
        }
    }

    // get All Cart details by userId
    public Collection<UserCartDetails> getAllCartDetails(Integer userid) {
        UserDetails ud = (UserDetails) em.find(UserDetails.class, userid);
        Collection<UserCartDetails> cartdetails = em.createQuery("SELECT u FROM UserCartDetails u WHERE u.userId = :userId", UserCartDetails.class).setParameter("userId", ud).getResultList();
        return cartdetails;
    }

    // increase the quantity and update the cart
    public void increaseQuantity(Integer cartId) {
        UserCartDetails uc = (UserCartDetails) em.find(UserCartDetails.class, cartId);
        Integer totalQty = uc.getQuantity() + 1;
        uc.setQuantity(totalQty);
        em.merge(uc);
    }

    // decrese the quantity and update the cart
    public void decreaseQuantity(Integer cartId) {
        UserCartDetails uc = (UserCartDetails) em.find(UserCartDetails.class, cartId);
        Integer totalQty = uc.getQuantity() - 1;
        uc.setQuantity(totalQty);
        em.merge(uc);
    }

    // get Electronic Stock for checking
    public Integer getStoreStock(Integer prodId) {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, prodId);
        ElectronicStoreProductStock productStock = (ElectronicStoreProductStock) em.createQuery("SELECT e FROM ElectronicStoreProductStock e WHERE e.productId = :productId", ElectronicStoreProductStock.class).setParameter("productId", pd).getSingleResult();

        if (productStock != null) {
            return productStock.getQuantity();
        } else {
            return 0;
        }
    }

    // count the products in cart
    public Integer getCartCount(Integer userId) {
        UserDetails ud = (UserDetails) em.find(UserDetails.class, userId);
        Long count = (Long) em.createQuery("SELECT COUNT(U.cartId) FROM UserCartDetails u WHERE u.userId = :userId")
                .setParameter("userId", ud)
                .getSingleResult();
        return count.intValue();
    }

    // remove cart item
    public void removeCartItem(Integer cartId) {
        UserCartDetails ud = (UserCartDetails) em.find(UserCartDetails.class, cartId);
        em.remove(ud);
    }
    
    // displat all festival offers
    public Collection<ElectronicStoreFestival> getAllFestivalOffers(){
        return em.createNamedQuery("ElectronicStoreFestival.findAll").getResultList();
    }
}
