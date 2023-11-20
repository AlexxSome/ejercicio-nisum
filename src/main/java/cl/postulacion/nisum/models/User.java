package cl.postulacion.nisum.models;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "users",schema = "public")
@ApiModel(value = "UserModel", description = "Model who represents an user entity")
public class User implements Serializable {
    private static final long serialVersionUID = -2974550049265724377L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    private Date modified;

    @Column(name = "last_login")
    @Temporal(TemporalType.DATE)
    private Date lastLogin;

    private Boolean isActive;

    @NotNull
    private String name;

    @Column(unique = true)
    @Email(message = "Formato de correo incorrecto")
    private String email;

    @NotNull
    private String password;

    private String token;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Phone.class)
    private Set<Phone> phones;

    public User(){}
    public User(Long id, Date createdAt, Date modified, Date lastLogin, Boolean isActive, String name, String email, String password, String token, Set<Phone> phones) {
        this.id = id;
        this.createdAt = createdAt;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.name = name;
        this.email = email;
        this.password = password;
        this.token = token;
        this.phones = phones;
    }

    public User( String name, String email, String password, Set<Phone> phones) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = phones;
    }

    public User(Long id, Date createdAt, Date modified, Date lastLogin, Boolean isActive, String token) {
        this.id = id;
        this.createdAt = createdAt;
        this.modified = modified;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", modified=" + modified +
                ", lastLogin=" + lastLogin +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", phones=" + phones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(createdAt, user.createdAt) && Objects.equals(modified, user.modified) && Objects.equals(lastLogin, user.lastLogin) && Objects.equals(isActive, user.isActive) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(token, user.token) && Objects.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, modified, lastLogin, isActive, name, email, password, token, phones);
    }
}
