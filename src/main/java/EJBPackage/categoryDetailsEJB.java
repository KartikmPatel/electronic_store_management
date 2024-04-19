/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class categoryDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;
    
    // Add Category
    public void addcategorydetails(String cname,Integer comid)
    {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comid);
        CategoryDetails cm = new CategoryDetails();
        cm.setCategoryName(cname);
        cm.setCompanyId(cd);
        em.persist(cm);
    }
    
    // Get All the Category
    public Collection<CategoryDetails> getAllCategory()
    {
        Collection<CategoryDetails> cm = em.createNamedQuery("CategoryDetails.findAll").getResultList();
        return cm;
    }
    
    // Get All the Company details
    public Collection<CompanyDetails> getAllCompany()
    {
        Collection<CompanyDetails> cd = em.createNamedQuery("CompanyDetails.findAll").getResultList();
        return cd;
    }
    
    // delete the category
    public void deleteCategory(Integer catid)
    {
        CategoryDetails cm = (CategoryDetails) em.find(CategoryDetails.class, catid);
        em.remove(cm);
    }
    
    // get the category by the category Id
    public Collection<CategoryDetails> getCategoryByCatid(Integer catid)
    {
        Collection<CategoryDetails> cm = em.createNamedQuery("CategoryDetails.findByCategoryId").setParameter("categoryId", catid).getResultList();
        return cm;
    }
    
    // update the category
    public void updateCategory(Integer catid,String cname,Integer comid)
    {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comid);
        CategoryDetails cm = (CategoryDetails) em.find(CategoryDetails.class, catid);
        cm.setCategoryName(cname);
        cm.setCompanyId(cd);
        em.merge(cm);
    }
}
