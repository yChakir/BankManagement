package ma.tiwtiw.bank.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class AddAccount {

  @NotEmpty
  @Size(min = 2, max = 50)
  private String name;

  @Min(1)
  @NotNull
  private Long type;
}
