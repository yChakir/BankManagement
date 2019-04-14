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
  public ResponseEntity<List<Account>> findAll() {
    List<Account> result = accountService.findAll();

    return ResponseEntity.ok(result);
  }

  @GetMapping("own")
  public ResponseEntity<List<Account>> findAll(Principal principal) {
    List<Account> result = accountService.findAll(principal.getName());

    return ResponseEntity.ok(result);
  }

  @GetMapping("waiting-for-approval")
  public ResponseEntity<List<Account>> findAllApproved() {
    List<Account> result = accountService.findAll(AccountStatus.WAITING_FOR_APPROVAL);

    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity add(
      @RequestBody @Valid AddAccount addAccount,
      Principal principal
  ) {
    accountService.add(principal.getName(), addAccount.getName(), addAccount.getType());

    return ResponseEntity.created(null).build();
  }

  @PutMapping("{id}")
  public ResponseEntity update(
      @PathVariable Long id,
      @RequestBody @Valid UpdateAccount updateAccount
  ) {
    accountService.update(id, updateAccount.getName(), updateAccount.getType());

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    accountService.delete(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}/approve")
  public ResponseEntity approve(@PathVariable Long id) {
    accountService.approve(id);

    return ResponseEntity.noContent().build();
  }

  @PutMapping("{id}/reject")
  public ResponseEntity reject(@PathVariable Long id) {
    accountService.reject(id);

    return ResponseEntity.noContent().build();
  }
}
