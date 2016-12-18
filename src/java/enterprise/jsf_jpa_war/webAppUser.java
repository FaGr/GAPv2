

package enterprise.jsf_jpa_war;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * <p>This entity provides a very basic representation of a web application user.</p>
 * @author anon
 */
@Entity
@Table(name = "WUSER")
@NamedQueries( {@NamedQuery(name = "Wuser.findById", query = "SELECT w FROM Wuser w WHERE w.id = :id"), @NamedQuery(name = "Wuser.findByFirstname", query = "SELECT w FROM Wuser w WHERE w.firstname = :firstname"), @NamedQuery(name = "Wuser.findByLastname", query = "SELECT w FROM Wuser w WHERE w.lastname = :lastname"), @NamedQuery(name = "Wuser.findByMail", query = "SELECT w FROM Wuser w WHERE w.mail = :mail"), @NamedQuery(name = "Wuser.findByPassword", query = "SELECT w FROM Wuser w WHERE w.password = :password"), @NamedQuery(name = "Wuser.findBySince", query = "SELECT w FROM Wuser w WHERE w.since = :since")})
public class webAppUser implements Serializable {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;
    
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "MAIL", nullable = false)
    private String mail;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "SINCE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date since;
    
    /** Creates a new instance of Wuser */
    public webAppUser() {
    }

    public webAppUser(Integer id) {
        this.id = id;
    }

    public webAppUser(Integer id, String firstname, String lastname, String status, String mail, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.status = status;
        this.mail = mail;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSince() {
        return this.since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public int hashCode() {
        int hash = 0;
        hash += (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object object) {
        if (object == null || !this.getClass().equals(object.getClass())) {
            return false;
        }
        webAppUser other = (webAppUser)object;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) return false;
        return true;
    }

    public String toString() {
        //TODO change toString() implementation to return a better display name
        return "" + this.id;
    }
    
}
