package pl.coderslab.drivertips.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Data
@ToString(exclude = "roles")
@Table(name = "admins")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private int enabled;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToMany
    private List<Tip> tips;
}