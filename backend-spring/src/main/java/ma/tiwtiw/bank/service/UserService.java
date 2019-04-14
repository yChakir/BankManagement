package ma.tiwtiw.bank.service;

import ma.tiwtiw.bank.dto.ChangePassword;
import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.dto.ResetPassword;
import ma.tiwtiw.bank.dto.ValidateEmail;
import ma.tiwtiw.bank.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

  User findByEmail(String email);

  User register(Registration registration);

  User delete(String email);

  User emailValidation(ValidateEmail emailValidation);

  void forgotPassword(String email);

  User resetPassword(ResetPassword resetPassword);

  User changePassword(String email, ChangePassword changePassword);

}
