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

    // display total sale of each product
    public Collection<Object[]> displayAllSales() {
        Collection<Object[]> saleProduct = em.createNativeQuery("SELECT p.product_name,c.category_name,cd.company_name,SUM(o.quantity),SUM(o.total_amount) FROM user_order_details o,electronic_store_selling_product s,product_details p,category_details c,company_details cd WHERE o.selling_product_id = s.selling_product_id AND s.product_id = p.product_id AND c.category_id = p.category_id AND cd.company_id = p.company_id AND o.status = 1 GROUP BY o.selling_product_id;").getResultList();
        return saleProduct;
    }

    // display total sale of particular product
    public Collection<Object[]> displayAllSales1(Integer pid) {
        Collection<Object[]> saleProduct = em.createNativeQuery("SELECT p.product_name,c.category_name,cd.company_name,SUM(o.quantity),SUM(o.total_amount) FROM user_order_details o,electronic_store_selling_product s,product_details p,category_details c,company_details cd WHERE o.selling_product_id = s.selling_product_id AND s.product_id = p.product_id AND c.category_id = p.category_id AND cd.company_id = p.company_id AND o.status = 1 AND o.selling_product_id = ?;").setParameter(1, pid).getResultList();
        return saleProduct;
    }

}
