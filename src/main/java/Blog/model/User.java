package Blog.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;


@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
//@SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", initialValue = 10, allocationSize = 1)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Please enter your username")
    private String username;
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Please enter your password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(
                    name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id", referencedColumnName = "id")})
    private Collection <Role> roles;

    public User(){
        super();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(Long id, String username, String password) {
        this.id = id;
       this.username = username;
       this.password = password;
    }

    public User(Long id, String username, String password, Collection<Role> roles) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

} 