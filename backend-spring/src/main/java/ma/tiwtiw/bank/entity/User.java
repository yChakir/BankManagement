package ma.tiwtiw.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;
import ma.tiwtiw.bank.pojo.RightEnum;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity implements UserDetails {

  @Transient
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  List<GrantedAuthority> authorities = new ArrayList<>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(name = "user_last_name")
  private String lastName;

  @Column(name = "user_first_name")
  private String firstName;

  @Column(name = "user_email", unique = true, nullable = false)
  private String email;

  @Exclude
  @Column(name = "user_password")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Column(name = "user_active")
  private boolean active;

  @OneToMany(mappedBy = "user")
  private List<Account> accounts = new ArrayList<>();

  @NotAudited
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USERS_ROLES", joinColumns = {
      @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles = new ArrayList<>();

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    authorities.clear();

    roles.forEach(role -> {
      if (!role.isDeleted()) {
        role.getRights().forEach(right -> {
          if (right.isActive() || right.getName().equals(RightEnum.ALL_RIGHTS)) {
            SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
                right.getName().name());
            if (!authorities.contains(grantedAuthority)) {
              authorities.add(grantedAuthority);
            }
          }
        });
      }
    });

    return authorities;
  }

  @Override
  public String getUsername() {
    return email;
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
