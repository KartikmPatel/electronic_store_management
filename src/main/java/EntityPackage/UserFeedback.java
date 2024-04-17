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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "user_feedback")
@NamedQueries({
    @NamedQuery(name = "UserFeedback.findAll", query = "SELECT u FROM UserFeedback u"),
    @NamedQuery(name = "UserFeedback.findByFeedbackId", query = "SELECT u FROM UserFeedback u WHERE u.feedbackId = :feedbackId"),
    @NamedQuery(name = "UserFeedback.findByFeedbackDate", query = "SELECT u FROM UserFeedback u WHERE u.feedbackDate = :feedbackDate")})
public class UserFeedback implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "feedback_id")
    private Integer feedbackId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "feedback_date")
    @Temporal(TemporalType.DATE)
    private Date feedbackDate;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private UserDetails userId;

    public UserFeedback() {
    }

    public UserFeedback(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public UserFeedback(Integer feedbackId, String message, Date feedbackDate) {
        this.feedbackId = feedbackId;
        this.message = message;
        this.feedbackDate = feedbackDate;
    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
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
        hash += (feedbackId != null ? feedbackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserFeedback)) {
            return false;
        }
        UserFeedback other = (UserFeedback) object;
        if ((this.feedbackId == null && other.feedbackId != null) || (this.feedbackId != null && !this.feedbackId.equals(other.feedbackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityPackage.UserFeedback[ feedbackId=" + feedbackId + " ]";
    }
    
}
