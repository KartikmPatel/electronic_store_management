/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityPackage;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "category_details")
@NamedQueries({
    @NamedQuery(name = "CategoryDetails.findAll", query = "SELECT c FROM CategoryDetails c"),
    @NamedQuery(name = "CategoryDetails.findByCategoryId", query = "SELECT c FROM CategoryDetails c WHERE c.categoryId = :categoryId"),
    @NamedQuery(name = "CategoryDetails.findByCategoryName", query = "SELECT c FROM CategoryDetails c WHERE c.categoryName = :categoryName")})
public class CategoryDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "category_id")
    private Integer categoryId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "category_name")
    private String categoryName;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private CompanyDetails companyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoryId")
    private Collection<ProductDetails> productDetailsCollection;

    public CategoryDetails() {
    }

    public CategoryDetails(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public CategoryDetails(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CompanyDetails getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyDetails companyId) {
        this.companyId = companyId;
    }

    public Collection<ProductDetails> getProductDetailsCollection() {
        return productDetailsCollection;
    }

    public void setProductDetailsCollection(Collection<ProductDetails> productDetailsCollection) {
        this.productDetailsCollection = productDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoryDetails)) {
            return false;
        }
        CategoryDetails other = (CategoryDetails) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.CategoryDetails[ categoryId=" + categoryId + " ]";
    }
    
}
