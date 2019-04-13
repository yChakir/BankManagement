package ma.tiwtiw.bank.service;

import java.util.Map;
import ma.tiwtiw.bank.entity.Mail;
import ma.tiwtiw.bank.event.PasswordResetEvent;
import ma.tiwtiw.bank.event.RegistrationEvent;
import org.springframework.context.event.EventListener;

public interface EmailService {

  @EventListener
  void sendEmailValidation(RegistrationEvent event);

  @EventListener
  void sendPasswordReset(PasswordResetEvent event);

  void sendAndSave(Mail mail, Map model);
}
