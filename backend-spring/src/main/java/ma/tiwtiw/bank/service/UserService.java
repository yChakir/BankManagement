package ma.tiwtiw.bank.service;

import ma.tiwtiw.bank.dto.ChangePassword;
import ma.tiwtiw.bank.dto.EmailValidation;
import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.dto.ResetPassword;
import ma.tiwtiw.bank.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User findByEmail(String email);

  User register(Registration registration);

  void delete(String email);

  void emailValidation(EmailValidation emailValidation);

  void forgotPassword(String email);

  void resetPassword(ResetPassword resetPassword);

  void changePassword(String email, ChangePassword changePassword);
}
