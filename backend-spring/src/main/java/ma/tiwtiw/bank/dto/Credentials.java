package ma.tiwtiw.bank.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString.Exclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials implements Serializable {

  private String username;

  @Exclude
  private String password;
}
