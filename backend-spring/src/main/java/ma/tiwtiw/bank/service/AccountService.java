package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.pojo.AccountStatus;

public interface AccountService {

  List<Account> findAll();

  List<Account> findAll(String username);

  List<Account> findAll(AccountStatus status);

  Account findById(Long id);

  Account add(String username, String name, Long type);

  Account update(Long id, String name, Long type);

  Account delete(Long id);

  Account approve(Long id);

  Account reject(Long id);
}
