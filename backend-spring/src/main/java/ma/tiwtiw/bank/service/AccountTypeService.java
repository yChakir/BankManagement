package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.AccountType;

public interface AccountTypeService {

  List<AccountType> findAll();

  AccountType findById(Long id);

  AccountType add(String name);

  AccountType update(Long id, String name);

  AccountType delete(Long id);
}
