package ma.tiwtiw.bank.controller;

import java.security.Principal;
import java.util.List;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @GetMapping
  public ResponseEntity<List<Account>> findAll() {
    List<Account> result = accountService.findAll();

    return ResponseEntity.ok(result);
  }

  @GetMapping
  public ResponseEntity<List<Account>> findAll(Principal principal) {
    List<Account> result = accountService.findAll(principal.getName());

    return ResponseEntity.ok(result);
  }
}
