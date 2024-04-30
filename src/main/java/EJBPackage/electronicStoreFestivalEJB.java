/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.ElectronicStoreFestival;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class electronicStoreFestivalEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public Collection<ElectronicStoreFestival> displayFestival(){
        return em.createNamedQuery("ElectronicStoreFestival.findAll").getResultList();
    }
    
    public void addFestival(String festivalName, Date festivalDate, Integer festivalDiscount){
        ElectronicStoreFestival model = new ElectronicStoreFestival();
        model.setFestivalName(festivalName);
        model.setFestivalDate(festivalDate);
        model.setFestivalDiscount(festivalDiscount);
        em.persist(model);
    }
        
    public Collection<ElectronicStoreFestival> getDataByIdForUpdate(Integer festivalId){
        return em.createNamedQuery("ElectronicStoreFestival.findByFestivalId").setParameter("festivalId",festivalId).getResultList();
    }
    
    public void updateFestival(Integer festivalId, String festivalName, Date festivalDate, Integer festivalDiscount){
        ElectronicStoreFestival model = em.find(ElectronicStoreFestival.class, festivalId);
        model.setFestivalName(festivalName);
        model.setFestivalDate(festivalDate);
        model.setFestivalDiscount(festivalDiscount);
        em.merge(model);
    }
    
    public void deleteFestival(Integer festivalId){
        ElectronicStoreFestival model = em.find(ElectronicStoreFestival.class, festivalId);
        em.remove(model);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
