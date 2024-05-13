/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ElectronicStoreSellingProduct;
import EntityPackage.ProductDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class storeSellingProductEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    // display Selling Products
    public Collection<ElectronicStoreSellingProduct> getSellingProducts() {
        Collection<ElectronicStoreSellingProduct> selproduct = em.createNamedQuery("ElectronicStoreSellingProduct.findAll").getResultList();
        return selproduct;
    }

    // get Products available in product stock table
    public Collection<ElectronicStoreProductStock> getStoreProductStoreForSelling() {
        Collection<ElectronicStoreProductStock> storestock = em.createNamedQuery("ElectronicStoreProductStock.findAll").getResultList();
        return storestock;
    }

    // get actaul price of product by product Id
    public Integer getPriceOfProduct(Integer pid) {
        ProductDetails pd = (ProductDetails) em.createNamedQuery("ProductDetails.findByProductId").setParameter("productId", pid).getSingleResult();

        if (pd != null) {
            return pd.getPrice();
        } else {
            return 0;
        }
    }

    // add Selling Product
    public void addStoreSellingProduct(Integer price, Integer prodId) {
        try {
            ProductDetails pd = em.find(ProductDetails.class, prodId);
            ElectronicStoreSellingProduct sellingProduct = new ElectronicStoreSellingProduct();
            sellingProduct.setPrice(price);
            sellingProduct.setProductId(pd);
            em.persist(sellingProduct);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error occurred while adding selling product", ex);
        }
    }

    // check if the same Product exists or not in the selling product table
    public ElectronicStoreSellingProduct checkSellingProduct(Integer pid) {
        try {
            ProductDetails pd = em.find(ProductDetails.class, pid);
            return em.createQuery("SELECT e FROM ElectronicStoreSellingProduct e WHERE e.productId = :productId", ElectronicStoreSellingProduct.class).setParameter("productId", pd).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
