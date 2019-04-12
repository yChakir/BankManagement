package ma.tiwtiw.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.tiwtiw.bank.pojo.MailType;
import org.hibernate.envers.Audited;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MAILS")
@EntityListeners(AuditingEntityListener.class)
public class Mail extends BaseEntity {

  @Id
  @Column(name = "mail_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "mail_from")
  private String from;

  @Column(name = "mail_to")
  private String to;

  @Column(name = "mail_subject")
  private String subject;

  @Column(name = "mail_content", length = 3000)
  private String content;

  @Column(name = "mail_sent")
  private boolean sent;

  @Column(name = "mail_type")
  @Enumerated(EnumType.STRING)
  private MailType type;
}
