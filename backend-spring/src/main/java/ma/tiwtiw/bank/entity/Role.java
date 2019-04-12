package ma.tiwtiw.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
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
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ROLES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long id;

  @Column(name = "role_name", unique = true, nullable = false)
  private String name;

  @JsonIgnore
  @NotAudited
  @ManyToMany
  @JoinTable(name = "USERS_ROLES", joinColumns = {
      @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
  private List<User> users = new ArrayList<>();

  @NotAudited
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "ROLES_RIGHTS", joinColumns = {
      @JoinColumn(name = "role_id")}, inverseJoinColumns = {@JoinColumn(name = "right_id")})
  private List<Right> rights = new ArrayList<>();
}
