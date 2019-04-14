package ma.tiwtiw.bank.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import ma.tiwtiw.bank.pojo.RightEnum;

@Data
public class EditRole {

  @NotEmpty
  @Size(min = 2, max = 50)
  private String name;

  @NotNull
  private List<RightEnum> rights;
}
