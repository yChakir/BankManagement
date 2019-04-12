package ma.tiwtiw.bank.controller;

import javax.validation.Valid;
import ma.tiwtiw.bank.dto.EmailValidation;
import ma.tiwtiw.bank.dto.ForgotPassword;
import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.dto.ResetPassword;
import ma.tiwtiw.bank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("register")
  public ResponseEntity register(@RequestBody @Valid Registration registration) {
    userService.register(registration);

    return ResponseEntity.created(null).build();
  }

  @PostMapping("email-validation")
  public ResponseEntity emailValidation(@RequestBody @Valid EmailValidation emailValidation) {
    userService.emailValidation(emailValidation);

    return ResponseEntity.noContent().build();
  }

  @PostMapping("forgot-password")
  public ResponseEntity forgotPassword(@RequestBody @Valid ForgotPassword forgotPassword) {
    userService.forgotPassword(forgotPassword.getEmail());

    return ResponseEntity.noContent().build();
  }

  @PostMapping("reset-password")
  public ResponseEntity resetPassword(@RequestBody @Valid ResetPassword resetPassword) {
    userService.resetPassword(resetPassword);

    return ResponseEntity.noContent().build();
  }
}
