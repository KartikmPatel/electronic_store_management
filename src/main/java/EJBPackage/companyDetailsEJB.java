/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import EntityPackage.CompanyProductStock;
import EntityPackage.ProductDetails;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Admin
 */
@Stateless
public class companyDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    public Collection<CompanyDetails> displayCompanyDetails() {
        return em.createNamedQuery("CompanyDetails.findAll").getResultList();
    }

    public void addCompanyDetails(String companyName, String email, Integer contactNo, String password, String companyLogo, String country) {
        CompanyDetails model = new CompanyDetails();
        model.setCompanyName(companyName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setCompanyLogo(companyLogo);
        model.setCountry(country);
        em.persist(model);
    }

    public Collection<CompanyDetails> getDataByIdForUpdate(Integer companyId) {
        return em.createNamedQuery("CompanyDetails.findByCompanyId").setParameter("companyId", companyId).getResultList();
    }

    public void updateCompanyDetails(Integer companyId, String companyName, String email, Integer contactNo, String password, String companyLogo, String country) {
        CompanyDetails model = em.find(CompanyDetails.class, companyId);
        model.setCompanyName(companyName);
        model.setEmail(email);
        model.setContactNo(contactNo);
        model.setPassword(password);
        model.setCompanyLogo(companyLogo);
        model.setCountry(country);
        em.merge(model);
    }

    public void deleteCompanyDetails(Integer companyId) {
        CompanyDetails model = em.find(CompanyDetails.class, companyId);
        em.remove(model);
    }

    public boolean checkLogin(String email, String password) {
        Query chklog = em.createQuery("SELECT c FROM CompanyDetails c WHERE c.email = :email AND c.password = :password")
                .setParameter("email", email)
                .setParameter("password", password);

        List<CompanyDetails> resultList = chklog.getResultList();

        if (resultList != null && !resultList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    // get UserId
    public Integer getCompanyId(String email) {
        Integer comId = (Integer) em.createQuery("SELECT c.companyId FROM CompanyDetails c WHERE c.email = :email")
                                    .setParameter("email", email)
                                    .getSingleResult();
        return comId;
    }

    // count category for the company Dashboard
    public Integer getCategoryCount(Integer catid) {
        CompanyDetails cd = em.find(CompanyDetails.class, catid);
        Long count = (Long) em.createQuery("SELECT COUNT(c.categoryId) FROM CategoryDetails c WHERE c.companyId = :companyId")
                .setParameter("companyId", cd)
                .getSingleResult();
        return count.intValue();
    }

    // count product for the company Dashboard
    public Integer getProductsCount(Integer companyId) {
        CompanyDetails cd = em.find(CompanyDetails.class, companyId);
        Long count = (Long) em.createQuery("SELECT COUNT(p.productId) FROM ProductDetails p WHERE p.companyId = :companyId")
                .setParameter("companyId", cd)
                .getSingleResult();
        return count.intValue();
    }

    // count company product stock for the company dashboard
    public Integer getCompanyProductStockCount(Integer comId) {
        CompanyDetails cd = em.find(CompanyDetails.class, comId);
        Long count = (Long) em.createQuery("SELECT COUNT(c.companyStockId) FROM CompanyProductStock c WHERE c.productId = (SELECT p.productId FROM ProductDetails p WHERE p.companyId = :companyId)")
                .setParameter("companyId", cd)
                .getSingleResult();
        return count.intValue();
    }
    
    // count Electronic Store orders for the company dashboard
    public Integer getStoreOrderCount(Integer comId) {
        CompanyDetails cd = em.find(CompanyDetails.class, comId);
        Long count = (Long) em.createQuery("SELECT COUNT(e.storeOrderId) FROM ElectronicStoreOrder e WHERE e.companyId = :companyId")
                .setParameter("companyId", cd)
                .getSingleResult();
        return count.intValue();
    }
}
