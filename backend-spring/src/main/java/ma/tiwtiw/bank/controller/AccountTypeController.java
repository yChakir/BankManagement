package ma.tiwtiw.bank.controller;

import java.util.List;
import javax.validation.Valid;
import ma.tiwtiw.bank.dto.AddAccountType;
import ma.tiwtiw.bank.dto.UpdateAccountType;
import ma.tiwtiw.bank.entity.AccountType;
import ma.tiwtiw.bank.service.AccountTypeService;
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
@RequestMapping("api/v1/account-type")
public class AccountTypeController {

  private AccountTypeService accountTypeService;

  @Autowired
  public void setAccountTypeService(AccountTypeService accountTypeService) {
    this.accountTypeService = accountTypeService;
  }

  @GetMapping
  public ResponseEntity<List<AccountType>> findAll() {
    List<AccountType> result = accountTypeService.findAll();

    return ResponseEntity.ok(result);
  }

  @PostMapping
  public ResponseEntity add(@RequestBody @Valid AddAccountType addAccountType) {
    accountTypeService.add(addAccountType.getName());

    return ResponseEntity.created(null).build();
  }

  @PutMapping("{id}")
  public ResponseEntity update(
      @PathVariable Long id,
      @RequestBody @Valid UpdateAccountType updateAccountType
  ) {
    accountTypeService.update(id, updateAccountType.getName());

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    accountTypeService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
