package ma.tiwtiw.bank.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.AccountType;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.repository.AccountTypeRepository;
import ma.tiwtiw.bank.service.AccountService;
import ma.tiwtiw.bank.service.AccountTypeService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService {

  private final AccountTypeRepository accountTypeRepository;

  private final AccountService accountService;

  public AccountTypeServiceImpl(AccountTypeRepository accountTypeRepository,
      AccountService accountService) {
    this.accountTypeRepository = accountTypeRepository;
    this.accountService = accountService;
  }

  @Override
  @Transactional(readOnly = true)
  public List<AccountType> findAll() {
    log.debug("findAll()");
    return accountTypeRepository.findAllByDeletedIsFalse();
  }

  @Override
  @Transactional(readOnly = true)
  public AccountType findById(Long id) {
    log.debug("findById() :: id = {}", id);
    return accountTypeRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> {
      log.warn("update() :: No account type with id = {}", id);
      return new ResourceNotFoundException(Translator.translate("exception.account-type.not-found"));
    });
  }

  @Override
  public void add(String name) {
    log.debug("add() :: name = {}", name);

    AccountType type = new AccountType();
    type.setName(name);

    accountTypeRepository.save(type);
  }

  @Override
  public void update(Long id, String name) {
    log.debug("update() :: id = {}, name = {}", id, name);

    AccountType accountType = findById(id);

    accountType.setName(name);

    accountTypeRepository.save(accountType);
  }

  @Override
  public void delete(Long id) {
    log.debug("delete() :: id = {}", id);

    AccountType accountType = findById(id);

    int count = accountService.countByTypeName(accountType.getName());

    if (count > 0) {
      log.warn("delete() :: can't delete affected account type, id = {}, name = {}, count = {}", id,
          accountType.getName(), count);
      throw new ClientException(Translator.translate("exception.account-type.delete.affected"));
    }

    accountType.setDeleted(true);

    accountTypeRepository.save(accountType);
  }
}
