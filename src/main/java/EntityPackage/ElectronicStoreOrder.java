/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityPackage;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "electronic_store_order")
@NamedQueries({
    @NamedQuery(name = "ElectronicStoreOrder.findAll", query = "SELECT e FROM ElectronicStoreOrder e"),
    @NamedQuery(name = "ElectronicStoreOrder.findByStoreOrderId", query = "SELECT e FROM ElectronicStoreOrder e WHERE e.storeOrderId = :storeOrderId"),
    @NamedQuery(name = "ElectronicStoreOrder.findByQuantity", query = "SELECT e FROM ElectronicStoreOrder e WHERE e.quantity = :quantity"),
    @NamedQuery(name = "ElectronicStoreOrder.findByBillAmount", query = "SELECT e FROM ElectronicStoreOrder e WHERE e.billAmount = :billAmount"),
    @NamedQuery(name = "ElectronicStoreOrder.findByOrderDate", query = "SELECT e FROM ElectronicStoreOrder e WHERE e.orderDate = :orderDate"),
    @NamedQuery(name = "ElectronicStoreOrder.findByStatus", query = "SELECT e FROM ElectronicStoreOrder e WHERE e.status = :status")})
public class ElectronicStoreOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "store_order_id")
    private Integer storeOrderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bill_amount")
    private int billAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private ProductDetails productId;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne(optional = false)
    private CompanyDetails companyId;

    public ElectronicStoreOrder() {
    }

    public ElectronicStoreOrder(Integer storeOrderId) {
        this.storeOrderId = storeOrderId;
    }

    public ElectronicStoreOrder(Integer storeOrderId, int quantity, int billAmount, Date orderDate, int status) {
        this.storeOrderId = storeOrderId;
        this.quantity = quantity;
        this.billAmount = billAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Integer getStoreOrderId() {
        return storeOrderId;
    }

    public void setStoreOrderId(Integer storeOrderId) {
        this.storeOrderId = storeOrderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(int billAmount) {
        this.billAmount = billAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ProductDetails getProductId() {
        return productId;
    }

    public void setProductId(ProductDetails productId) {
        this.productId = productId;
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
        hash += (storeOrderId != null ? storeOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicStoreOrder)) {
            return false;
        }
        ElectronicStoreOrder other = (ElectronicStoreOrder) object;
        if ((this.storeOrderId == null && other.storeOrderId != null) || (this.storeOrderId != null && !this.storeOrderId.equals(other.storeOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ElectronicStoreOrder[ storeOrderId=" + storeOrderId + " ]";
    }
    
}
