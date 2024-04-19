/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CompanyDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Admin
 */
@Stateless
public class companyDetailsEJB {
    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    public Collection<CompanyDetails> displayCompanyDetails(){
        return em.createNamedQuery("CompanyDetails.findAll").getResultList();
    }
    
    public void addCompanyDetails(String companyName, String email, Integer contactNo, String password, String companyLogo, String country){
        CompanyDetails model = new CompanyDetails();
        model.setCompanyName(companyName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setCompanyLogo(companyLogo);
        model.setCountry(country);
        em.persist(model);
    }
    
    public Collection<CompanyDetails> getDataByIdForUpdate(Integer companyId){
        return em.createNamedQuery("CompanyDetails.findByCompanyId").setParameter("companyId", companyId).getResultList();
    }
    
    public void updateCompanyDetails(Integer companyId, String companyName, String email, Integer contactNo, String password, String companyLogo, String country){
        CompanyDetails model = em.find(CompanyDetails.class, companyId);
        model.setCompanyName(companyName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setCompanyLogo(companyLogo);
        model.setCountry(country);
        em.merge(model);
    }
    
    public void deleteCompanyDetails(Integer companyId){
        CompanyDetails model = em.find(CompanyDetails.class, companyId);
        em.remove(model);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
