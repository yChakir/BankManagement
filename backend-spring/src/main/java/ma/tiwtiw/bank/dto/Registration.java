package ma.tiwtiw.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Registration {

  @Size(min = 2, max = 50)
  private String firstName;

  @Size(min = 2, max = 50)
  private String lastName;

  @Email
  @NotBlank
  @Size(min = 3, max = 100)
  private String username;

  @NotBlank
  @Size(min = 8, max = 50)
  //@Pattern(regexp = "^.*(([a-zA-Z]+.*[0-9]+)|([0-9]+.*[a-zA-Z]+)).*$", message = "{validations.password-strength}")
  private String password;
}
