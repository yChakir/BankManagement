package ma.tiwtiw.bank.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.entity.AccountType;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.AccountStatus;
import ma.tiwtiw.bank.repository.AccountRepository;
import ma.tiwtiw.bank.service.AccountService;
import ma.tiwtiw.bank.service.AccountTypeService;
import ma.tiwtiw.bank.service.UserService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final AccountTypeService accountTypeService;

  private final UserService userService;

  public AccountServiceImpl(AccountRepository accountRepository,
      AccountTypeService accountTypeService, UserService userService) {
    this.accountRepository = accountRepository;
    this.accountTypeService = accountTypeService;
    this.userService = userService;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll() {
    log.debug("findAll()");
    return accountRepository.findAllByDeletedIsFalse();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll(String username) {
    log.debug("findAll() :: username = {}", username);
    return accountRepository.findAllByUser_EmailAndDeletedIsFalse(username);
  }

  @Override
  @Transactional(readOnly = true)
  public List<Account> findAll(AccountStatus status) {
    log.debug("findAll() :: status = {}", status);
    return accountRepository.findAllByStatusAndDeletedIsFalse(status);
  }

  @Override
  public Account findById(Long id) {
    log.debug("findById() :: id = {}", id);
    return accountRepository.findByIdAndDeletedIsFalse(id)
        .orElseThrow(() -> {
          log.debug("findById() :: No account with id = {}", id);
          return new ResourceNotFoundException(Translator.translate("exception.account.not-found"));
        });
  }

  @Override
  public Account add(String username, String name, Long type) {
    log.debug("add() :: username = {}, name = {}, type = {}", username, name, type);

    User user = userService.findByEmail(username);
    AccountType accountType = accountTypeService.findById(type);

    Account account = new Account();

    account.setUser(user);
    account.setType(accountType);
    account.setStatus(AccountStatus.WAITING_FOR_APPROVAL);

    return accountRepository.save(account);
  }

  @Override
  public Account update(Long id, String name, Long type) {
    log.debug("update() :: id = {}, name = {}, type = {}", id, name, type);

    Account account = findById(id);

    account.setName(name);

    if (!account.getType().getId().equals(type)) {
      AccountType accountType = accountTypeService.findById(type);
      log.debug("update() :: account type changed, old = {}, new = {}", account.getType().getName(),
          accountType.getName());

      account.setType(accountType);
    }

    return accountRepository.save(account);
  }

  @Override
  public Account delete(Long id) {
    log.debug("delete() :: id = {}", id);

    Account account = findById(id);

    account.setDeleted(true);

    return accountRepository.save(account);
  }

  @Override
  public Account approve(Long id) {
    log.debug("approve() :: id = {}", id);

    Account account = findById(id);

    if (!account.getStatus().equals(AccountStatus.WAITING_FOR_APPROVAL)) {
      log.warn("approve() :: account status is not waiting for approval, status = {}", account.getStatus());
      throw new ClientException(Translator.translate("exception.account.not-waiting-approval"));
    }

    account.setStatus(AccountStatus.APPROVED);

    return accountRepository.save(account);
  }

  @Override
  public Account reject(Long id) {
    log.debug("reject() :: id = {}", id);

    Account account = findById(id);

    if (!account.getStatus().equals(AccountStatus.WAITING_FOR_APPROVAL)) {
      log.warn("reject() :: account status is not waiting for approval, status = {}", account.getStatus());
      throw new ClientException(Translator.translate("exception.account.not-waiting-approval"));
    }

    account.setStatus(AccountStatus.REJECTED);

    return accountRepository.save(account);
  }
}
