package ma.tiwtiw.bank.controller;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import ma.tiwtiw.bank.dto.AddAccount;
import ma.tiwtiw.bank.dto.UpdateAccount;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.pojo.AccountStatus;
import ma.tiwtiw.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

  private AccountService accountService;

  @Autowired
  public void setAccountService(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  @PreAuthorize("hasAnyAuthority('SHOW_ACCOUNTS', 'ALL_RIGHTS')")
  public ResponseEntity<List<Account>> findAll() {
    List<Account> result = accountService.findAll();

    return ResponseEntity.ok(result);
  }

  @GetMapping("own")
  @PreAuthorize("hasAnyAuthority('SHOW_ACCOUNTS_OWN', 'ALL_RIGHTS')")
  public ResponseEntity<List<Account>> findAll(Principal principal) {
    List<Account> result = accountService.findAll(principal.getName());

    return ResponseEntity.ok(result);
  }

  @GetMapping("waiting-for-approval")
  @PreAuthorize("hasAnyAuthority('SHOW_ACCOUNTS_WAITING_FOR_APPROVAL', 'ALL_RIGHTS')")
  public ResponseEntity<List<Account>> findAllApproved() {
    List<Account> result = accountService.findAll(AccountStatus.WAITING_FOR_APPROVAL);

    return ResponseEntity.ok(result);
  }

  @PostMapping
  @PreAuthorize("hasAnyAuthority('ADD_ACCOUNT', 'ALL_RIGHTS')")
  public ResponseEntity add(
      @RequestBody @Valid AddAccount addAccount,
      Principal principal
  ) {
    accountService.add(principal.getName(), addAccount.getName(), addAccount.getType());

    return ResponseEntity.created(null).build();
  }

  @PutMapping("{id}")
  @PreAuthorize("hasAnyAuthority('UPDATE_ACCOUNT', 'ALL_RIGHTS')")
  public ResponseEntity update(
      @PathVariable Long id,
      @RequestBody @Valid UpdateAccount updateAccount
  ) {
    accountService.update(id, updateAccount.getName(), updateAccount.getType());

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  @PreAuthorize("hasAnyAuthority('DELETE_ACCOUNT', 'ALL_RIGHTS')")
  public ResponseEntity delete(@PathVariable Long id) {
    accountService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}/approve")
  @PreAuthorize("hasAnyAuthority('APPROVE_ACCOUNT', 'ALL_RIGHTS')")
  public ResponseEntity approve(@PathVariable Long id) {
    accountService.approve(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}/reject")
  @PreAuthorize("hasAnyAuthority('DECLINE_ACCOUNT', 'ALL_RIGHTS')")
  public ResponseEntity reject(@PathVariable Long id) {
    accountService.reject(id);

    return ResponseEntity.noContent().build();
  }
}
