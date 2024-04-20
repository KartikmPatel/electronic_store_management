/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CategoryDetails;
import EntityPackage.CompanyDetails;
import EntityPackage.ProductDetails;
import java.util.Collection;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kartik Patel
 */
@Stateless
public class productDetailsEJB {

    @PersistenceContext(unitName = "my_persistence_unit")
    EntityManager em;

    // Add Product
    public void addProductDetails(String product_name, Integer discount, Integer price, String product_image, Date mfg_date, String Warranty, Integer category_id, Integer company_id) {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, company_id);
        CategoryDetails cm = (CategoryDetails) em.find(CategoryDetails.class, category_id);
        ProductDetails pd = new ProductDetails();
        pd.setProductName(product_name);
        pd.setDiscount(discount);
        pd.setPrice(price);
        pd.setProductImage(product_image);
        pd.setMfgDate(mfg_date);
        pd.setWarranty(Warranty);
        pd.setCategoryId(cm);
        pd.setCompanyId(cd);
        em.persist(pd);
    }

    // get All the products
    public Collection<ProductDetails> getAllProducts() {
        Collection<ProductDetails> pd = em.createNamedQuery("ProductDetails.findAll").getResultList();
        return pd;
    }

    // Get All the Category details
    public Collection<CategoryDetails> getAllCategory() {
        Collection<CategoryDetails> cm = em.createNamedQuery("CategoryDetails.findAll").getResultList();
        return cm;
    }

    // Get All the Company details
    public Collection<CompanyDetails> getAllCompany() {
        Collection<CompanyDetails> cd = em.createNamedQuery("CompanyDetails.findAll").getResultList();
        return cd;
    }

    // delete product
    public void deleteProduct(Integer pid) {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, pid);
        em.remove(pd);
    }

    // get a product by product Id
    public Collection<ProductDetails> getProductById(Integer pid) {
        Collection<ProductDetails> pd = em.createNamedQuery("ProductDetails.findByProductId").setParameter("productId", pid).getResultList();
        return pd;
    }

    // update the product details
    public void updateProduct(Integer pid, String product_name, Integer discount, Integer price, String product_image, Date mfg_date, String Warranty, Integer category_id, Integer company_id) {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, company_id);
        CategoryDetails cm = (CategoryDetails) em.find(CategoryDetails.class, category_id);
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, pid);
        pd.setProductName(product_name);
        pd.setDiscount(discount);
        pd.setPrice(price);
        pd.setProductImage(product_image);
        pd.setMfgDate(mfg_date);
        pd.setWarranty(Warranty);
        pd.setCategoryId(cm);
        pd.setCompanyId(cd);
        em.merge(pd);
    }
}
