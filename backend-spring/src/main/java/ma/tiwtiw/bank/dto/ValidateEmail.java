package ma.tiwtiw.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateEmail {

  @Email
  @NotBlank
  @Size(min = 3, max = 100)
  private String username;

  @NotBlank
  @Size(min = 30, max = 50)
  private String token;
}
