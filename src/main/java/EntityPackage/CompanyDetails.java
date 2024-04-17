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
@Table(name = "company_details")
@NamedQueries({
    @NamedQuery(name = "CompanyDetails.findAll", query = "SELECT c FROM CompanyDetails c"),
    @NamedQuery(name = "CompanyDetails.findByCompanyId", query = "SELECT c FROM CompanyDetails c WHERE c.companyId = :companyId"),
    @NamedQuery(name = "CompanyDetails.findByCompanyName", query = "SELECT c FROM CompanyDetails c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "CompanyDetails.findByEmail", query = "SELECT c FROM CompanyDetails c WHERE c.email = :email"),
    @NamedQuery(name = "CompanyDetails.findByContactNo", query = "SELECT c FROM CompanyDetails c WHERE c.contactNo = :contactNo"),
    @NamedQuery(name = "CompanyDetails.findByPassword", query = "SELECT c FROM CompanyDetails c WHERE c.password = :password"),
    @NamedQuery(name = "CompanyDetails.findByCompanyLogo", query = "SELECT c FROM CompanyDetails c WHERE c.companyLogo = :companyLogo"),
    @NamedQuery(name = "CompanyDetails.findByCountry", query = "SELECT c FROM CompanyDetails c WHERE c.country = :country")})
public class CompanyDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_id")
    private Integer companyId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "company_name")
    private String companyName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "contact_no")
    private long contactNo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company_logo")
    private String companyLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private Collection<CategoryDetails> categoryDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private Collection<ElectronicStoreOrder> electronicStoreOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private Collection<ProductDetails> productDetailsCollection;

    public CompanyDetails() {
    }

    public CompanyDetails(Integer companyId) {
        this.companyId = companyId;
    }

    public CompanyDetails(Integer companyId, String companyName, String email, long contactNo, String password, String companyLogo, String country) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.companyLogo = companyLogo;
        this.country = country;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Collection<CategoryDetails> getCategoryDetailsCollection() {
        return categoryDetailsCollection;
    }

    public void setCategoryDetailsCollection(Collection<CategoryDetails> categoryDetailsCollection) {
        this.categoryDetailsCollection = categoryDetailsCollection;
    }

    public Collection<ElectronicStoreOrder> getElectronicStoreOrderCollection() {
        return electronicStoreOrderCollection;
    }

    public void setElectronicStoreOrderCollection(Collection<ElectronicStoreOrder> electronicStoreOrderCollection) {
        this.electronicStoreOrderCollection = electronicStoreOrderCollection;
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
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyDetails)) {
            return false;
        }
        CompanyDetails other = (CompanyDetails) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.CompanyDetails[ companyId=" + companyId + " ]";
    }
    
}
