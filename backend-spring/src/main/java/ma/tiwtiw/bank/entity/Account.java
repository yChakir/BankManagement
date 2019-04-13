package ma.tiwtiw.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tiwtiw.bank.pojo.AccountStatus;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACCOUNTS")
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity {

  @Id
  @Column(name = "account_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "account_name")
  private String name;

  @Column(name = "account_status")
  private AccountStatus status;

  @ManyToOne
  private AccountType type;

  @ManyToOne
  private User user;
}
