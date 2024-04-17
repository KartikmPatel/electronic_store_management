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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "company_product_stock")
@NamedQueries({
    @NamedQuery(name = "CompanyProductStock.findAll", query = "SELECT c FROM CompanyProductStock c"),
    @NamedQuery(name = "CompanyProductStock.findByCompanyStockId", query = "SELECT c FROM CompanyProductStock c WHERE c.companyStockId = :companyStockId"),
    @NamedQuery(name = "CompanyProductStock.findByQuantity", query = "SELECT c FROM CompanyProductStock c WHERE c.quantity = :quantity")})
public class CompanyProductStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "company_stock_id")
    private Integer companyStockId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private ProductDetails productId;

    public CompanyProductStock() {
    }

    public CompanyProductStock(Integer companyStockId) {
        this.companyStockId = companyStockId;
    }

    public CompanyProductStock(Integer companyStockId, int quantity) {
        this.companyStockId = companyStockId;
        this.quantity = quantity;
    }

    public Integer getCompanyStockId() {
        return companyStockId;
    }

    public void setCompanyStockId(Integer companyStockId) {
        this.companyStockId = companyStockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDetails getProductId() {
        return productId;
    }

    public void setProductId(ProductDetails productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyStockId != null ? companyStockId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyProductStock)) {
            return false;
        }
        CompanyProductStock other = (CompanyProductStock) object;
        if ((this.companyStockId == null && other.companyStockId != null) || (this.companyStockId != null && !this.companyStockId.equals(other.companyStockId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.CompanyProductStock[ companyStockId=" + companyStockId + " ]";
    }
    
}
