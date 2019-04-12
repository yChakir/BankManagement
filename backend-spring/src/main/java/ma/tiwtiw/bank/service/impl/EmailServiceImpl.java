package ma.tiwtiw.bank.service.impl;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Email;
import ma.tiwtiw.bank.event.PasswordResetEvent;
import ma.tiwtiw.bank.event.RegistrationEvent;
import ma.tiwtiw.bank.pojo.EmailType;
import ma.tiwtiw.bank.repository.EmailRepository;
import ma.tiwtiw.bank.service.EmailService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender sender;
  private final EmailRepository emailRepository;

  public EmailServiceImpl(JavaMailSender sender, EmailRepository emailRepository) {
    this.sender = sender;
    this.emailRepository = emailRepository;
  }

  @Override
  @EventListener
  public void sendEmailValidation(RegistrationEvent event) {
    log.debug("sendEmailValidation() :: event = {}", event);
    LocaleContextHolder.setLocale(event.getLocale());
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(event.getToken().getUser().getEmail());
    message.setSubject(Translator.translate("username.registration.title"));
    message.setText(Translator.translate(
        "username.registration.body",
        event.getToken().getUser().getFirstName(),
        event.getToken().getUser().getLastName(),
        event.getToken().getUser().getEmail(),
        event.getToken().getToken()
        )
    );

    sendAndSave(message, EmailType.ACCOUNT_CONFIRMATION);
  }

  @Override
  @EventListener
  public void sendPasswordReset(PasswordResetEvent event) {
    log.debug("sendPasswordReset() :: event = {}", event);
    LocaleContextHolder.setLocale(event.getLocale());
    SimpleMailMessage message = new SimpleMailMessage();

    message.setTo(event.getToken().getUser().getEmail());
    message.setSubject(Translator.translate("username.forgot-password.title"));
    message.setText(Translator.translate(
        "username.forgot-password.body",
        event.getToken().getUser().getFirstName(),
        event.getToken().getUser().getLastName(),
        event.getToken().getUser().getEmail(),
        event.getToken().getToken()
        )
    );

    sendAndSave(message, EmailType.FORGOT_PASSWORD);
  }

  @Override
  public void sendAndSave(SimpleMailMessage message, EmailType type) {
    Email email = new Email(
        null,
        message.getFrom(),
        Arrays.toString(message.getTo()),
        message.getSubject(),
        message.getText(),
        true,
        type
    );

    try {
      log.debug("sendAndSave() :: Sending username = {}", email);
      sender.send(message);
    } catch (Exception e) {
      log.error("sendAndSave() :: error while sending username = {}, message = {}", email,
          e.getMessage());
      email.setSent(false);
    } finally {
      emailRepository.save(email);
      log.debug("sendAndSave() :: Email sent = {}", email);
    }
  }
}
