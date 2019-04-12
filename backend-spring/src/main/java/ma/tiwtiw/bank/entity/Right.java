package ma.tiwtiw.bank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import ma.tiwtiw.bank.pojo.RightEnum;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRIVILEGES")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Right extends BaseEntity {

  @Id
  @Column(name = "right_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "right_name", updatable = false, nullable = false, unique = true)
  private RightEnum name;

  @Column(name = "right_description")
  private String description;

  @Column(name = "right_active")
  private boolean active = true;

  @JsonIgnore
  @NotAudited
  @ManyToMany
  @JoinTable(name = "ROLES_RIGHTS", joinColumns = {
      @JoinColumn(name = "right_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private List<Role> roles = new ArrayList<>();
}
