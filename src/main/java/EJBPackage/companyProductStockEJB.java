/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package EJBPackage;

import EntityPackage.CompanyDetails;
import EntityPackage.CompanyProductStock;
import EntityPackage.ElectronicStoreOrder;
import EntityPackage.ElectronicStoreProductStock;
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

    public Collection<CompanyProductStock> displayCompanyProductStock(Integer comId) {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comId);
        return em.createQuery("SELECT c FROM CompanyProductStock c WHERE c.productId = (SELECT p.productId FROM ProductDetails p WHERE p.companyId = :companyId)", CompanyProductStock.class).setParameter("companyId", cd).getResultList();
    }

    public void addCompanyProductStock(Integer quantity, Integer productId) {
        ProductDetails productDetails = em.find(ProductDetails.class, productId);
        CompanyProductStock model = new CompanyProductStock();
        model.setQuantity(quantity);
        model.setProductId(productDetails);
        em.persist(model);
    }

    public Collection<ProductDetails> displayProductDetails() {
        return em.createNamedQuery("ProductDetails.findAll").getResultList();
    }

    public Collection<CompanyProductStock> getDataByIdForUpdate(Integer companyStokeId) {
        return em.createNamedQuery("CompanyProductStock.findByCompanyStockId").setParameter("companyStockId", companyStokeId).getResultList();
    }

    public void updateCompanyProductStock(Integer companyStokeId, Integer quantity, Integer productId) {
        ProductDetails productDetails = em.find(ProductDetails.class, productId);
        CompanyProductStock model = em.find(CompanyProductStock.class, companyStokeId);
        model.setQuantity(quantity);
        model.setProductId(productDetails);
        em.merge(model);
    }

    public void deleteCompanyProductStock(Integer companyStokeId) {
        CompanyProductStock model = em.find(CompanyProductStock.class, companyStokeId);
        em.remove(model);
    }

    // get prodId by name & company
    public ProductDetails getProdIdByNameAndCompanyId(String pname, Integer comid) {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comid);
        return em.createQuery("SELECT p FROM ProductDetails p WHERE p.productName = :productName AND p.companyId = :companyId", ProductDetails.class).setParameter("productName", pname).setParameter("companyId", cd).getSingleResult();
    }

    // get orders by companyID
    public Collection<ElectronicStoreOrder> getStoreOrders(Integer comId) {
        CompanyDetails cd = (CompanyDetails) em.find(CompanyDetails.class, comId);
        Collection<ElectronicStoreOrder> orders = em.createQuery("SELECT e FROM ElectronicStoreOrder e WHERE e.companyId = :companyId", ElectronicStoreOrder.class).setParameter("companyId", cd).getResultList();
        return orders;
    }

    // get permission for order(Change status method)
    public void changeStatus(Integer status, Integer orderId) {
        ElectronicStoreOrder order = (ElectronicStoreOrder) em.find(ElectronicStoreOrder.class, orderId);
        order.setStatus(status);
        em.merge(order);
    }

    // minus quantity from the Company Product Stock Table
    public void minusCompanyProductStock(Integer qty, Integer prodId) {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, prodId);
        CompanyProductStock pstock = em.createQuery("SELECT c FROM CompanyProductStock c WHERE c.productId = :productId", CompanyProductStock.class).setParameter("productId", pd).getSingleResult();
        Integer q = pstock.getQuantity();
        Integer minusQty = q - qty;
        pstock.setQuantity(minusQty);
        em.merge(pstock);
    }

    // Add or insert the quantity of a product into the Store Product Stock Table after getting the permission
    public void addElectronicStoreProductStock(Integer prodId, Integer qty) {
        ProductDetails pd = em.find(ProductDetails.class, prodId);

        // Try to find ElectronicStoreProductStock for the given ProductDetails
        ElectronicStoreProductStock stock = em.createQuery("SELECT e FROM ElectronicStoreProductStock e WHERE e.productId = :productId", ElectronicStoreProductStock.class)
                .setParameter("productId", pd)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

        if (stock != null) {
            stock.setQuantity(stock.getQuantity() + qty);
        } else {
            // If ElectronicStoreProductStock doesn't exist, create a new one
            stock = new ElectronicStoreProductStock();
            stock.setProductId(pd);
            stock.setQuantity(qty);
            em.persist(stock);
        }
    }

    // edit company product stock
    public void editComapnyProductStock(Integer prodId,Integer qty)
    {
        ProductDetails pd = (ProductDetails) em.find(ProductDetails.class, prodId);
        CompanyProductStock productStock = (CompanyProductStock) em.createQuery("SELECT c FROM CompanyProductStock c WHERE c.productId = :productId",CompanyProductStock.class).setParameter("productId", pd).getSingleResult();
        Integer newStock = productStock.getQuantity() + qty;
        productStock.setQuantity(newStock);
        em.merge(productStock);
    }
}
