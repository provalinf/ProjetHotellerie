package m2.info.models.user;

import m2.info.config.BCryptManager;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User implements UserDetails {

    @NotNull
    @Column(name = "id")
    @Id protected String id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    protected String username;

    @NotNull
    @Column(name = "password")
    protected String password;

    @Column(name = "account_non_expired")
    protected boolean accountNonExpired;

    @Column(name = "account_non_locked")
    protected boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    protected boolean credentialsNonExpired;

    @Column(name = "enabled")
    protected boolean enabled;

    @NotNull
    @Column(name = "lastname", nullable = false)
    protected String lastname;

    @NotNull
    @Column(name = "firstname", nullable = false)
    protected String firstname;

    public User() {}

    public User(String id, String username, String password, String lastname, String firstname) {
        this.id = id;
        this.username = username;
        this.password = BCryptManager.passwordencoder().encode(password);
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    @Override
    public String getPassword() { return password; }

    @Override
    public String getUsername() { return username; }

    @Override
    public boolean isAccountNonExpired() { return accountNonExpired; }

    @Override
    public boolean isAccountNonLocked() { return accountNonLocked; }

    @Override
    public boolean isCredentialsNonExpired() { return credentialsNonExpired; }

    @Override
    public boolean isEnabled() { return enabled; }

    public void setPassword(String password) {
        if (!password.isEmpty())
            this.password = BCryptManager.passwordencoder().encode(password);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
}