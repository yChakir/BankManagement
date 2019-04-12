package ma.tiwtiw.bank.service;

import ma.tiwtiw.bank.event.PasswordResetEvent;
import ma.tiwtiw.bank.event.RegistrationEvent;
import ma.tiwtiw.bank.pojo.EmailType;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

  void sendEmailValidation(RegistrationEvent event);

  void sendPasswordReset(PasswordResetEvent event);

  void sendAndSave(SimpleMailMessage message, EmailType type);
}
