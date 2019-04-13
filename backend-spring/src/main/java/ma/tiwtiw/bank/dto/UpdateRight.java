package ma.tiwtiw.bank.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateRight {

  @NotEmpty
  @Size(min = 2, max = 50)
  private String description;
}
