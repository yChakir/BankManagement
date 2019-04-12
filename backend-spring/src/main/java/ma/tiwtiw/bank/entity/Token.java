package ma.tiwtiw.bank.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import ma.tiwtiw.bank.pojo.TokenType;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Audited
@AllArgsConstructor
@Table(name = "TOKENS")
@EntityListeners(AuditingEntityListener.class)
public class Token extends BaseEntity implements Serializable {

  @Id
  @Column(name = "token_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "token_string")
  private String token;

  @NotAudited
  @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
  private User user;

  @Column(name = "token_expire_date")
  private LocalDateTime expireDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "token_type")
  private TokenType type;

  @Column(name = "token_used")
  private boolean used = false;

  public Token() {
    this.token = UUID.randomUUID().toString();
    this.expireDate = LocalDateTime.now().plusDays(1);
  }

  public Token(User user, TokenType type) {
    this();
    this.user = user;
    this.type = type;
  }

}
