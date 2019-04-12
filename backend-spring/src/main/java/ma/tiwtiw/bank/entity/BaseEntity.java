package ma.tiwtiw.bank.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

  @CreatedDate
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime created;

  @LastModifiedDate
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private LocalDateTime updated;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private boolean deleted;

  @CreatedBy
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String createdBy;

  @LastModifiedBy
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String modifiedBy;
}
