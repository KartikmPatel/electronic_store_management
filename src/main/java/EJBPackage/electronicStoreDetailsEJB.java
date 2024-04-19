/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreDetails;
import java.util.Collection;
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
    
    public Collection<ElectronicStoreDetails> displayElectronicStoreDetails(){
        return em.createNamedQuery("ElectronicStoreDetails.findAll").getResultList();
    }
    
    public void addElectronicStoreDetails(String storeName, String email, Integer contactNo, String password, String storeLogo, String address, String country){
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
    
    public Collection<ElectronicStoreDetails> getDataByIdForUpdate(Integer storeId){
        return em.createNamedQuery("ElectronicStoreDetails.findByStoreId").setParameter("storeId", storeId).getResultList();
    }
    
    public void updateElectronicStoreDetails(Integer storeId, String storeName, String email, Integer contactNo, String password, String storeLogo, String address, String country){
        ElectronicStoreDetails model = em.find(ElectronicStoreDetails.class, storeId);
        model.setStoreName(storeName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setStoreLogo(storeLogo);
        model.setAddress(address);
        model.setCountry(country);
        em.merge(model);
    }
    
    public void deleteElectronicStoreDetails(Integer storeId){
        ElectronicStoreDetails model = em.find(ElectronicStoreDetails.class, storeId);
        em.remove(model);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
