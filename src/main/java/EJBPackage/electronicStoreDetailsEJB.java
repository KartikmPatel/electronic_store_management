/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CompanyDetails;
import EntityPackage.CompanyProductStock;
import EntityPackage.ElectronicStoreDetails;
import EntityPackage.ElectronicStoreOrder;
import EntityPackage.ProductDetails;
import java.util.Collection;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class electronicStoreDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    public Collection<ElectronicStoreDetails> displayElectronicStoreDetails() {
        return em.createNamedQuery("ElectronicStoreDetails.findAll").getResultList();
    }

    public void addElectronicStoreDetails(String storeName, String email, Integer contactNo, String password, String storeLogo, String address, String country) {
        ElectronicStoreDetails model = new ElectronicStoreDetails();
        model.setStoreName(storeName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setStoreLogo(storeLogo);
        model.setAddress(address);
        model.setCountry(country);
        em.persist(model);
    }

    public ElectronicStoreDetails getDataByIdForUpdate() {
        return em.createNamedQuery("ElectronicStoreDetails.findByStoreId", ElectronicStoreDetails.class).setParameter("storeId", 1).getSingleResult();
    }

    public void updateElectronicStoreDetails(String storeName, String email, Integer contactNo, String password, String storeLogo, String address, String country) {
        ElectronicStoreDetails model = em.find(ElectronicStoreDetails.class, 1);
        model.setStoreName(storeName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setStoreLogo(storeLogo);
        model.setAddress(address);
        model.setCountry(country);
        em.merge(model);
    }

    public void deleteElectronicStoreDetails(Integer storeId) {
        ElectronicStoreDetails model = em.find(ElectronicStoreDetails.class, storeId);
        em.remove(model);
    }

    // functionality for get or display all the products
    // get All the products
    public Collection<ProductDetails> getAllProducts() {
        Collection<ProductDetails> pd = em.createNamedQuery("ProductDetails.findAll").getResultList();
        return pd;
    }

    // check quantity
    public Integer getQuantity(Integer pid) {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, pid);
        CompanyProductStock stocks = (CompanyProductStock) em.createQuery("SELECT c FROM CompanyProductStock c WHERE c.productId = :productId").setParameter("productId", pd).getSingleResult();

        if (stocks != null) {
            return stocks.getQuantity();
        } else {
            return 0;
        }
    }

    // get a product by product Id
    public ProductDetails getProductById(Integer pid) {
        ProductDetails pd = (ProductDetails) em.createNamedQuery("ProductDetails.findByProductId").setParameter("productId", pid).getSingleResult();
        return pd;
    }

    // add order
    public void addOrder(Integer qty, Integer billamt, Date odateDate, Integer status, Integer pid, Integer comid) {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, pid);
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comid);
        ElectronicStoreOrder order = new ElectronicStoreOrder();
        order.setQuantity(qty);
        order.setBillAmount(billamt);
        order.setOrderDate(odateDate);
        order.setStatus(status);
        order.setProductId(pd);
        order.setCompanyId(cd);
        em.persist(order);
    }

    // display orders
    public Collection<ElectronicStoreOrder> getAllOrders() {
        Collection<ElectronicStoreOrder> orders = em.createNamedQuery("ElectronicStoreOrder.findAll").getResultList();
        return orders;
    }

    // get count of store order for Store dashboard
    public Integer getEleStoreOrderCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.storeOrderId) FROM ElectronicStoreOrder e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of store product stock for store dashboard
    public Integer getStoreStockCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.storeStockId) FROM ElectronicStoreProductStock e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of Electronic Products for store dashboard
    public Integer getElectronicProductCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.productId) FROM ProductDetails e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of Selling Product for Store dashboard
    public Integer getStoreSellingProductCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.sellingProductId) FROM ElectronicStoreSellingProduct e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of Festival Offers for Store dashboard
    public Integer getOfferCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.festivalId) FROM ElectronicStoreFestival e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of user orders for Store dashboard
    public Integer getUserOrderCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.orderId) FROM UserOrderDetails e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of user feedback for Store dashboard
    public Integer getUserFeedbackCount() {
        Long count = (Long) em.createQuery("SELECT COUNT(e.feedbackId) FROM UserFeedback e")
                .getSingleResult();
        return count.intValue();
    }

    // get count of Sold Product for store dashboard
    public Integer getSaleProductCount() {
        Long count = (Long) em.createQuery("SELECT SUM(u.quantity) FROM UserOrderDetails u WHERE u.status = 1")
                .getSingleResult();
        return count != null ? count.intValue() : 0;
    }
    
    // get multiply quantity and price
    public Integer calculateTotalPrice(Integer qty,Integer price)
    {
        Integer totalPrice = qty * price;
        return totalPrice;
    }
}
