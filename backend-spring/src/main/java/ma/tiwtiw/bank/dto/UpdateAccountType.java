package ma.tiwtiw.bank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAccountType {

  @Size(min = 2, max = 50)
  private String name;
}
