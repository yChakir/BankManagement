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
public class ForgotPassword {

  @Email
  @NotBlank
  @Size(min = 3, max = 100)
  private String username;
}
