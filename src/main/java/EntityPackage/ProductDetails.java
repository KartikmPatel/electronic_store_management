/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityPackage;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "product_details")
@NamedQueries({
    @NamedQuery(name = "ProductDetails.findAll", query = "SELECT p FROM ProductDetails p"),
    @NamedQuery(name = "ProductDetails.findByProductId", query = "SELECT p FROM ProductDetails p WHERE p.productId = :productId"),
    @NamedQuery(name = "ProductDetails.findByProductName", query = "SELECT p FROM ProductDetails p WHERE p.productName = :productName"),
    @NamedQuery(name = "ProductDetails.findByDiscount", query = "SELECT p FROM ProductDetails p WHERE p.discount = :discount"),
    @NamedQuery(name = "ProductDetails.findByPrice", query = "SELECT p FROM ProductDetails p WHERE p.price = :price"),
    @NamedQuery(name = "ProductDetails.findByProductImage", query = "SELECT p FROM ProductDetails p WHERE p.productImage = :productImage"),
    @NamedQuery(name = "ProductDetails.findByMfgDate", query = "SELECT p FROM ProductDetails p WHERE p.mfgDate = :mfgDate"),
    @NamedQuery(name = "ProductDetails.findByWarranty", query = "SELECT p FROM ProductDetails p WHERE p.warranty = :warranty")})
public class ProductDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "product_name")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount")
    private int discount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "product_image")
    private String productImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mfg_date")
    @Temporal(TemporalType.DATE)
    private Date mfgDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Warranty")
    private String warranty;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ElectronicStoreSellingProduct> electronicStoreSellingProductCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ElectronicStoreProductStock> electronicStoreProductStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<CompanyProductStock> companyProductStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private Collection<ElectronicStoreOrder> electronicStoreOrderCollection;
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    @ManyToOne(optional = false)
    private CategoryDetails categoryId;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private CompanyDetails companyId;

    public ProductDetails() {
    }

    public ProductDetails(Integer productId) {
        this.productId = productId;
    }

    public ProductDetails(Integer productId, String productName, int discount, int price, String productImage, Date mfgDate, String warranty) {
        this.productId = productId;
        this.productName = productName;
        this.discount = discount;
        this.price = price;
        this.productImage = productImage;
        this.mfgDate = mfgDate;
        this.warranty = warranty;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Date getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(Date mfgDate) {
        this.mfgDate = mfgDate;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Collection<ElectronicStoreSellingProduct> getElectronicStoreSellingProductCollection() {
        return electronicStoreSellingProductCollection;
    }

    public void setElectronicStoreSellingProductCollection(Collection<ElectronicStoreSellingProduct> electronicStoreSellingProductCollection) {
        this.electronicStoreSellingProductCollection = electronicStoreSellingProductCollection;
    }

    public Collection<ElectronicStoreProductStock> getElectronicStoreProductStockCollection() {
        return electronicStoreProductStockCollection;
    }

    public void setElectronicStoreProductStockCollection(Collection<ElectronicStoreProductStock> electronicStoreProductStockCollection) {
        this.electronicStoreProductStockCollection = electronicStoreProductStockCollection;
    }

    public Collection<CompanyProductStock> getCompanyProductStockCollection() {
        return companyProductStockCollection;
    }

    public void setCompanyProductStockCollection(Collection<CompanyProductStock> companyProductStockCollection) {
        this.companyProductStockCollection = companyProductStockCollection;
    }

    public Collection<ElectronicStoreOrder> getElectronicStoreOrderCollection() {
        return electronicStoreOrderCollection;
    }

    public void setElectronicStoreOrderCollection(Collection<ElectronicStoreOrder> electronicStoreOrderCollection) {
        this.electronicStoreOrderCollection = electronicStoreOrderCollection;
    }

    public CategoryDetails getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryDetails categoryId) {
        this.categoryId = categoryId;
    }

    public CompanyDetails getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyDetails companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductDetails)) {
            return false;
        }
        ProductDetails other = (ProductDetails) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ProductDetails[ productId=" + productId + " ]";
    }
    
}
