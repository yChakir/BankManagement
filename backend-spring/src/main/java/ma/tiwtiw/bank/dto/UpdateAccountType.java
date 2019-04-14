package ma.tiwtiw.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAccountType {

  @NotBlank
  @Size(min = 2, max = 50)
  private String name;
}
