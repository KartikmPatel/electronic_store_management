/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CompanyProductStock;
import EntityPackage.ProductDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class companyProductStockEJB {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public Collection<CompanyProductStock> displayCompanyProductStock(){
        return em.createNamedQuery("CompanyProductStock.findAll").getResultList();
    }
    
    public void addCompanyProductStock(Integer quantity, Integer productId){
        ProductDetails productDetails = em.find(ProductDetails.class, productId);
        CompanyProductStock model = new CompanyProductStock();
        model.setQuantity(quantity);
        model.setProductId(productDetails);
        em.persist(model);
    }
    
    public Collection<ProductDetails> displayProductDetails(){
        return em.createNamedQuery("ProductDetails.findAll").getResultList();
    }
    
    public Collection<CompanyProductStock> getDataByIdForUpdate(Integer companyStokeId){
        return em.createNamedQuery("CompanyProductStock.findByCompanyStockId").setParameter("companyStockId", companyStokeId).getResultList();
    }

    public void updateCompanyProductStock(Integer companyStokeId, Integer quantity, Integer productId){
        ProductDetails productDetails = em.find(ProductDetails.class, productId);
        CompanyProductStock model = em.find(CompanyProductStock.class, companyStokeId);
        model.setQuantity(quantity);
        model.setProductId(productDetails);
        em.merge(model);
    }
    
    public void deleteCompanyProductStock(Integer companyStokeId){
        CompanyProductStock model = em.find(CompanyProductStock.class, companyStokeId);
        em.remove(model);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
