/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreSellingProduct;
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
    public Collection<ElectronicStoreSellingProduct> getAllSellingProducts()
    {
        Collection<ElectronicStoreSellingProduct> sellingProd = em.createNamedQuery("ElectronicStoreSellingProduct.findAll").getResultList();
        return sellingProd;
    }
}
