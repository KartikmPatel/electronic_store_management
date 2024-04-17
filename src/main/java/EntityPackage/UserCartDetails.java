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
@Table(name = "user_cart_details")
@NamedQueries({
    @NamedQuery(name = "UserCartDetails.findAll", query = "SELECT u FROM UserCartDetails u"),
    @NamedQuery(name = "UserCartDetails.findByCartId", query = "SELECT u FROM UserCartDetails u WHERE u.cartId = :cartId"),
    @NamedQuery(name = "UserCartDetails.findByQuantity", query = "SELECT u FROM UserCartDetails u WHERE u.quantity = :quantity")})
public class UserCartDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cart_id")
    private Integer cartId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserDetails userId;
    @JoinColumn(name = "selling_product_id", referencedColumnName = "selling_product_id")
    @ManyToOne(optional = false)
    private ElectronicStoreSellingProduct sellingProductId;

    public UserCartDetails() {
    }

    public UserCartDetails(Integer cartId) {
        this.cartId = cartId;
    }

    public UserCartDetails(Integer cartId, int quantity) {
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UserDetails getUserId() {
        return userId;
    }

    public void setUserId(UserDetails userId) {
        this.userId = userId;
    }

    public ElectronicStoreSellingProduct getSellingProductId() {
        return sellingProductId;
    }

    public void setSellingProductId(ElectronicStoreSellingProduct sellingProductId) {
        this.sellingProductId = sellingProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cartId != null ? cartId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserCartDetails)) {
            return false;
        }
        UserCartDetails other = (UserCartDetails) object;
        if ((this.cartId == null && other.cartId != null) || (this.cartId != null && !this.cartId.equals(other.cartId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.UserCartDetails[ cartId=" + cartId + " ]";
    }
    
}
