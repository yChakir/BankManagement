package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.pojo.AccountStatus;

public interface AccountService {

  List<Account> findAll();

  List<Account> findAll(String username);

  List<Account> findAll(AccountStatus status);

  Account findById(Long id);

  int countByTypeName(String name);

  int countByUsername(String username);

  void add(String username, String name, Long type);

  void update(Long id, String name, Long type);

  void delete(Long id);

  void approve(Long id);

  void reject(Long id);
}
