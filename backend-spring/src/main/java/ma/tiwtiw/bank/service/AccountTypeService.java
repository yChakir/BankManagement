package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.AccountType;

public interface AccountTypeService {

  List<AccountType> findAll();

  AccountType findById(Long id);

  void add(String name);

  void update(Long id, String name);

  void delete(Long id);
}
