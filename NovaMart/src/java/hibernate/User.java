package hibernate;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "firstName", length = 20, nullable = false)
    private String firstName;
    @Column(name = "lastName", length = 20, nullable = false)
    private String lastName;
    @Column(name = "email", length = 20, nullable = false)
    private String email;
    @Column(name = "password", length = 50, nullable = false)
    private String password;
    @Column(name = "verificationCode", length = 10, nullable = false)
    private String verificationCode;
    @Column(name = "registerdAt", nullable = false)
    private Date registerdAt;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Date getRegisterdAt() {
        return registerdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setRegisterdAt(Date registerdAt) {
        this.registerdAt = registerdAt;
    }

}
