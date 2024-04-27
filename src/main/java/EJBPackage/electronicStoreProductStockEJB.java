/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreProductStock;
import EntityPackage.ProductDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class electronicStoreProductStockEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public void addStock(Integer prodid,Integer qty)
    {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, prodid);
        ElectronicStoreProductStock ps = new ElectronicStoreProductStock();
        ps.setProductId(pd);
        ps.setQuantity(qty);
        em.persist(ps);
    }
    
    public Collection<ElectronicStoreProductStock> getAllStock()
    {
        Collection<ElectronicStoreProductStock> ps = em.createNamedQuery("ElectronicStoreProductStock.findAll").getResultList();
        return ps;
    }
}
