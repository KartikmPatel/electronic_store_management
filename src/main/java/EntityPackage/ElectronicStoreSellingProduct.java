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

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "electronic_store_selling_product")
@NamedQueries({
    @NamedQuery(name = "ElectronicStoreSellingProduct.findAll", query = "SELECT e FROM ElectronicStoreSellingProduct e"),
    @NamedQuery(name = "ElectronicStoreSellingProduct.findBySellingProductId", query = "SELECT e FROM ElectronicStoreSellingProduct e WHERE e.sellingProductId = :sellingProductId"),
    @NamedQuery(name = "ElectronicStoreSellingProduct.findByPrice", query = "SELECT e FROM ElectronicStoreSellingProduct e WHERE e.price = :price")})
public class ElectronicStoreSellingProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "selling_product_id")
    private Integer sellingProductId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private int price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellingProductId")
    private Collection<UserCartDetails> userCartDetailsCollection;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private ProductDetails productId;
    @JoinColumn(name = "festival_id", referencedColumnName = "festival_id")
    @ManyToOne(optional = false)
    private ElectronicStoreFestival festivalId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sellingProductId")
    private Collection<UserOrderDetails> userOrderDetailsCollection;

    public ElectronicStoreSellingProduct() {
    }

    public ElectronicStoreSellingProduct(Integer sellingProductId) {
        this.sellingProductId = sellingProductId;
    }

    public ElectronicStoreSellingProduct(Integer sellingProductId, int price) {
        this.sellingProductId = sellingProductId;
        this.price = price;
    }

    public Integer getSellingProductId() {
        return sellingProductId;
    }

    public void setSellingProductId(Integer sellingProductId) {
        this.sellingProductId = sellingProductId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Collection<UserCartDetails> getUserCartDetailsCollection() {
        return userCartDetailsCollection;
    }

    public void setUserCartDetailsCollection(Collection<UserCartDetails> userCartDetailsCollection) {
        this.userCartDetailsCollection = userCartDetailsCollection;
    }

    public ProductDetails getProductId() {
        return productId;
    }

    public void setProductId(ProductDetails productId) {
        this.productId = productId;
    }

    public ElectronicStoreFestival getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(ElectronicStoreFestival festivalId) {
        this.festivalId = festivalId;
    }

    public Collection<UserOrderDetails> getUserOrderDetailsCollection() {
        return userOrderDetailsCollection;
    }

    public void setUserOrderDetailsCollection(Collection<UserOrderDetails> userOrderDetailsCollection) {
        this.userOrderDetailsCollection = userOrderDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sellingProductId != null ? sellingProductId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicStoreSellingProduct)) {
            return false;
        }
        ElectronicStoreSellingProduct other = (ElectronicStoreSellingProduct) object;
        if ((this.sellingProductId == null && other.sellingProductId != null) || (this.sellingProductId != null && !this.sellingProductId.equals(other.sellingProductId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ElectronicStoreSellingProduct[ sellingProductId=" + sellingProductId + " ]";
    }
    
}
