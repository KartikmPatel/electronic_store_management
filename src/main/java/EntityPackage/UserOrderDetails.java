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
@Table(name = "user_order_details")
@NamedQueries({
    @NamedQuery(name = "UserOrderDetails.findAll", query = "SELECT u FROM UserOrderDetails u"),
    @NamedQuery(name = "UserOrderDetails.findByOrderId", query = "SELECT u FROM UserOrderDetails u WHERE u.orderId = :orderId"),
    @NamedQuery(name = "UserOrderDetails.findByQuantity", query = "SELECT u FROM UserOrderDetails u WHERE u.quantity = :quantity"),
    @NamedQuery(name = "UserOrderDetails.findByTotalAmount", query = "SELECT u FROM UserOrderDetails u WHERE u.totalAmount = :totalAmount"),
    @NamedQuery(name = "UserOrderDetails.findByOrderDate", query = "SELECT u FROM UserOrderDetails u WHERE u.orderDate = :orderDate"),
    @NamedQuery(name = "UserOrderDetails.findByStatus", query = "SELECT u FROM UserOrderDetails u WHERE u.status = :status")})
public class UserOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_amount")
    private int totalAmount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "selling_product_id", referencedColumnName = "selling_product_id")
    @ManyToOne(optional = false)
    private ElectronicStoreSellingProduct sellingProductId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserDetails userId;

    public UserOrderDetails() {
    }

    public UserOrderDetails(Integer orderId) {
        this.orderId = orderId;
    }

    public UserOrderDetails(Integer orderId, int quantity, int totalAmount, Date orderDate, int status) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
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

    public ElectronicStoreSellingProduct getSellingProductId() {
        return sellingProductId;
    }

    public void setSellingProductId(ElectronicStoreSellingProduct sellingProductId) {
        this.sellingProductId = sellingProductId;
    }

    public UserDetails getUserId() {
        return userId;
    }

    public void setUserId(UserDetails userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOrderDetails)) {
            return false;
        }
        UserOrderDetails other = (UserOrderDetails) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.UserOrderDetails[ orderId=" + orderId + " ]";
    }
    
}
