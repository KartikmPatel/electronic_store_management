/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityPackage;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "electronic_store_details")
@NamedQueries({
    @NamedQuery(name = "ElectronicStoreDetails.findAll", query = "SELECT e FROM ElectronicStoreDetails e"),
    @NamedQuery(name = "ElectronicStoreDetails.findByStoreId", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.storeId = :storeId"),
    @NamedQuery(name = "ElectronicStoreDetails.findByStoreName", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.storeName = :storeName"),
    @NamedQuery(name = "ElectronicStoreDetails.findByEmail", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.email = :email"),
    @NamedQuery(name = "ElectronicStoreDetails.findByContactNo", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.contactNo = :contactNo"),
    @NamedQuery(name = "ElectronicStoreDetails.findByPassword", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.password = :password"),
    @NamedQuery(name = "ElectronicStoreDetails.findByStoreLogo", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.storeLogo = :storeLogo"),
    @NamedQuery(name = "ElectronicStoreDetails.findByAddress", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.address = :address"),
    @NamedQuery(name = "ElectronicStoreDetails.findByCountry", query = "SELECT e FROM ElectronicStoreDetails e WHERE e.country = :country")})
public class ElectronicStoreDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "store_id")
    private Integer storeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "store_name")
    private String storeName;
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
    @Column(name = "store_logo")
    private String storeLogo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "country")
    private String country;

    public ElectronicStoreDetails() {
    }

    public ElectronicStoreDetails(Integer storeId) {
        this.storeId = storeId;
    }

    public ElectronicStoreDetails(Integer storeId, String storeName, String email, long contactNo, String password, String storeLogo, String address, String country) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.storeLogo = storeLogo;
        this.address = address;
        this.country = country;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicStoreDetails)) {
            return false;
        }
        ElectronicStoreDetails other = (ElectronicStoreDetails) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ElectronicStoreDetails[ storeId=" + storeId + " ]";
    }
    
}
