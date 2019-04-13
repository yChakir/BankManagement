package ma.tiwtiw.bank.converter;

import ma.tiwtiw.bank.dto.AccountResult;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.entity.AccountType;
import ma.tiwtiw.bank.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AccountToAccountResultConverter implements Converter<Account, AccountResult> {

  @Override
  public AccountResult convert(Account account) {
    AccountResult result = new AccountResult();

    User user = account.getUser();
    AccountType type = account.getType();

    result.setFullName(user.getFirstName() + " " + user.getLastName());
    result.setName(account.getName());
    result.setStatus(account.getStatus());
    result.setType(type.getName());
    result.setTypeId(type.getId());

    return result;
  }
}
