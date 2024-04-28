/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntityPackage;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "electronic_store_festival")
@NamedQueries({
    @NamedQuery(name = "ElectronicStoreFestival.findAll", query = "SELECT e FROM ElectronicStoreFestival e"),
    @NamedQuery(name = "ElectronicStoreFestival.findByFestivalId", query = "SELECT e FROM ElectronicStoreFestival e WHERE e.festivalId = :festivalId"),
    @NamedQuery(name = "ElectronicStoreFestival.findByFestivalName", query = "SELECT e FROM ElectronicStoreFestival e WHERE e.festivalName = :festivalName"),
    @NamedQuery(name = "ElectronicStoreFestival.findByFestivalDate", query = "SELECT e FROM ElectronicStoreFestival e WHERE e.festivalDate = :festivalDate"),
    @NamedQuery(name = "ElectronicStoreFestival.findByFestivalDiscount", query = "SELECT e FROM ElectronicStoreFestival e WHERE e.festivalDiscount = :festivalDiscount")})
public class ElectronicStoreFestival implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "festival_id")
    private Integer festivalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "festival_name")
    private String festivalName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "festival_date")
    @Temporal(TemporalType.DATE)
    private Date festivalDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "festival_discount")
    private int festivalDiscount;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "festivalId")
    private Collection<ElectronicStoreSellingProduct> electronicStoreSellingProductCollection;

    public ElectronicStoreFestival() {
    }

    public ElectronicStoreFestival(Integer festivalId) {
        this.festivalId = festivalId;
    }

    public ElectronicStoreFestival(Integer festivalId, String festivalName, Date festivalDate, int festivalDiscount) {
        this.festivalId = festivalId;
        this.festivalName = festivalName;
        this.festivalDate = festivalDate;
        this.festivalDiscount = festivalDiscount;
    }

    public Integer getFestivalId() {
        return festivalId;
    }

    public void setFestivalId(Integer festivalId) {
        this.festivalId = festivalId;
    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public Date getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(Date festivalDate) {
        this.festivalDate = festivalDate;
    }

    public int getFestivalDiscount() {
        return festivalDiscount;
    }

    public void setFestivalDiscount(int festivalDiscount) {
        this.festivalDiscount = festivalDiscount;
    }

    @JsonbTransient
    public Collection<ElectronicStoreSellingProduct> getElectronicStoreSellingProductCollection() {
        return electronicStoreSellingProductCollection;
    }

    @JsonbTransient
    public void setElectronicStoreSellingProductCollection(Collection<ElectronicStoreSellingProduct> electronicStoreSellingProductCollection) {
        this.electronicStoreSellingProductCollection = electronicStoreSellingProductCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (festivalId != null ? festivalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectronicStoreFestival)) {
            return false;
        }
        ElectronicStoreFestival other = (ElectronicStoreFestival) object;
        if ((this.festivalId == null && other.festivalId != null) || (this.festivalId != null && !this.festivalId.equals(other.festivalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.ElectronicStoreFestival[ festivalId=" + festivalId + " ]";
    }
    
}
