package ma.tiwtiw.bank.dto;

import lombok.Data;
import ma.tiwtiw.bank.pojo.AccountStatus;

@Data
public class AccountResult {

  private String fullName;

  private String name;

  private Long typeId;

  private String type;

  private AccountStatus status;
}
