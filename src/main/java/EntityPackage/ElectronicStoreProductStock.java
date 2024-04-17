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
@Table(name = "electronic_store_product_stock")
@NamedQueries({
    @NamedQuery(name = "ElectronicStoreProductStock.findAll", query = "SELECT e FROM ElectronicStoreProductStock e"),
    @NamedQuery(name = "ElectronicStoreProductStock.findByStoreStockId", query = "SELECT e FROM ElectronicStoreProductStock e WHERE e.storeStockId = :storeStockId"),
    @NamedQuery(name = "ElectronicStoreProductStock.findByQuantity", query = "SELECT e FROM ElectronicStoreProductStock e WHERE e.quantity = :quantity")})
public class ElectronicStoreProductStock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "store_stock_id")
    private Integer storeStockId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private ProductDetails productId;

    public ElectronicStoreProductStock() {
    }

    public ElectronicStoreProductStock(Integer storeStockId) {
        this.storeStockId = storeStockId;
    }

    public ElectronicStoreProductStock(Integer storeStockId, int quantity) {
        this.storeStockId = storeStockId;
        this.quantity = quantity;
    }

    public Integer getStoreStockId() {
        return storeStockId;
    }

    public void setStoreStockId(Integer storeStockId) {
        this.storeStockId = storeStockId;
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
        hash += (storeStockId != null ? storeStockId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicStoreProductStock)) {
            return false;
        }
        ElectronicStoreProductStock other = (ElectronicStoreProductStock) object;
        if ((this.storeStockId == null && other.storeStockId != null) || (this.storeStockId != null && !this.storeStockId.equals(other.storeStockId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ElectronicStoreProductStock[ storeStockId=" + storeStockId + " ]";
    }
    
}
