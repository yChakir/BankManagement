package ma.tiwtiw.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassword {

  @NotBlank
  @Size(min = 8, max = 50)
  private String currentPassword;

  @NotBlank
  @Size(min = 8, max = 50)
  //@Pattern(regexp = "^.*(([a-zA-Z]+.*[0-9]+)|([0-9]+.*[a-zA-Z]+)).*$", message = "{validations.password-strength}")
  private String newPassword;

  @NotBlank
  @Size(min = 8, max = 50)
  private String confirmation;
}
