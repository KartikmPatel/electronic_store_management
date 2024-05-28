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
@Table(name = "user_details")
@NamedQueries({
    @NamedQuery(name = "UserDetails.findAll", query = "SELECT u FROM UserDetails u"),
    @NamedQuery(name = "UserDetails.findByUserId", query = "SELECT u FROM UserDetails u WHERE u.userId = :userId"),
    @NamedQuery(name = "UserDetails.findByUsername", query = "SELECT u FROM UserDetails u WHERE u.username = :username"),
    @NamedQuery(name = "UserDetails.findByEmail", query = "SELECT u FROM UserDetails u WHERE u.email = :email"),
    @NamedQuery(name = "UserDetails.findByContactNo", query = "SELECT u FROM UserDetails u WHERE u.contactNo = :contactNo"),
    @NamedQuery(name = "UserDetails.findByPassword", query = "SELECT u FROM UserDetails u WHERE u.password = :password"),
    @NamedQuery(name = "UserDetails.findByDob", query = "SELECT u FROM UserDetails u WHERE u.dob = :dob"),
    @NamedQuery(name = "UserDetails.findByGender", query = "SELECT u FROM UserDetails u WHERE u.gender = :gender"),
    @NamedQuery(name = "UserDetails.findByProfilePic", query = "SELECT u FROM UserDetails u WHERE u.profilePic = :profilePic"),
    @NamedQuery(name = "UserDetails.findByAddress", query = "SELECT u FROM UserDetails u WHERE u.address = :address"),
    @NamedQuery(name = "UserDetails.findByCountry", query = "SELECT u FROM UserDetails u WHERE u.country = :country"),
    @NamedQuery(name = "UserDetails.findByToken", query = "SELECT u FROM UserDetails u WHERE u.token = :token")})
public class UserDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
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
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "gender")
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "profile_pic")
    private String profilePic;
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
    @Size(max = 50)
    @Column(name = "token")
    private String token;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserCartDetails> userCartDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserFeedback> userFeedbackCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Collection<UserOrderDetails> userOrderDetailsCollection;

    public UserDetails() {
    }

    public UserDetails(Integer userId) {
        this.userId = userId;
    }

    public UserDetails(Integer userId, String username, String email, long contactNo, String password, Date dob, String gender, String profilePic, String address, String country) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.profilePic = profilePic;
        this.address = address;
        this.country = country;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonbTransient
    public Collection<UserCartDetails> getUserCartDetailsCollection() {
        return userCartDetailsCollection;
    }

    @JsonbTransient
    public void setUserCartDetailsCollection(Collection<UserCartDetails> userCartDetailsCollection) {
        this.userCartDetailsCollection = userCartDetailsCollection;
    }

    @JsonbTransient
    public Collection<UserFeedback> getUserFeedbackCollection() {
        return userFeedbackCollection;
    }

    @JsonbTransient
    public void setUserFeedbackCollection(Collection<UserFeedback> userFeedbackCollection) {
        this.userFeedbackCollection = userFeedbackCollection;
    }

    @JsonbTransient
    public Collection<UserOrderDetails> getUserOrderDetailsCollection() {
        return userOrderDetailsCollection;
    }

    @JsonbTransient
    public void setUserOrderDetailsCollection(Collection<UserOrderDetails> userOrderDetailsCollection) {
        this.userOrderDetailsCollection = userOrderDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDetails)) {
            return false;
        }
        UserDetails other = (UserDetails) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.UserDetails[ userId=" + userId + " ]";
    }
    
}
