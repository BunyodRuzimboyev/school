package uz.br29.appschool.security;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.br29.appschool.enums.Role;
import uz.br29.appschool.enums.UserType;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "_user")
@Builder
public class User extends BaseEntity implements UserDetails {


    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//-------------- OTHER FIELDS ------------------------------------
    @Column(nullable = false)
    private String firstName = "";

    @Column(nullable = false)
    private String lastName = "";

    private String middleName = "";

    @Column(nullable = false)
    private UUID identificationId;

    @Column(nullable = false)
    private UUID imageId;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDate;

    private Integer age;

    @Column(nullable = false)
    private UUID addressId;

    @Enumerated(EnumType.STRING)
    private UserType userType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
