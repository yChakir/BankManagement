package ma.tiwtiw.bank.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.entity.AccountType;
import ma.tiwtiw.bank.entity.User;
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
  public int countByTypeName(String name) {
    log.debug("countByTypeName() :: name = {}", name);
    return accountRepository.countAllByType_Name(name);
  }

  @Override
  public int countByTypeUsername(String username) {
    log.debug("countByTypeUsername() :: username = {}", username);
    return accountRepository.countAllByUser_Email(username);
  }

  @Override
  public void add(String username, String name, Long type) {
    log.debug("add() :: username = {}, name = {}, type = {}", username, name, type);

    User user = userService.findByEmail(username);
    AccountType accountType = accountTypeService.findById(type);

    Account account = new Account();

    account.setUser(user);
    account.setType(accountType);
    account.setStatus(AccountStatus.WAITING_FOR_APPROVAL);

    accountRepository.save(account);
  }

  @Override
  public void update(Long id, String name, Long type) {
    log.debug("update() :: id = {}, name = {}, type = {}", id, name, type);

    Account account = findById(id);

    account.setName(name);

    if (!account.getType().getId().equals(type)) {
      AccountType accountType = accountTypeService.findById(type);
      log.debug("update() :: account type changed, old = {}, new = {}", account.getType().getName(),
          accountType.getName());

      account.setType(accountType);
    }

    accountRepository.save(account);
  }

  @Override
  public void delete(Long id) {
    log.debug("delete() :: id = {}", id);

    Account account = findById(id);

    account.setDeleted(true);

    accountRepository.save(account);
  }

  @Override
  public void approve(Long id) {
    log.debug("delete() :: id = {}", id);

    Account account = findById(id);

    account.setStatus(AccountStatus.APPROVED);

    accountRepository.save(account);
  }
}
