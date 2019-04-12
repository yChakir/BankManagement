package ma.tiwtiw.bank.service.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.configuration.AppConstants;
import ma.tiwtiw.bank.entity.Mail;
import ma.tiwtiw.bank.event.PasswordResetEvent;
import ma.tiwtiw.bank.event.RegistrationEvent;
import ma.tiwtiw.bank.pojo.MailType;
import ma.tiwtiw.bank.repository.EmailRepository;
import ma.tiwtiw.bank.service.EmailService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.context.event.EventListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender sender;
  private final EmailRepository emailRepository;
  private final Configuration freeMarkerConfiguration;

  public EmailServiceImpl(JavaMailSender sender, EmailRepository emailRepository,
      Configuration freeMarkerConfiguration) {
    this.sender = sender;
    this.emailRepository = emailRepository;
    this.freeMarkerConfiguration = freeMarkerConfiguration;
  }

  @Override
  @EventListener
  public void sendEmailValidation(RegistrationEvent event) {
    log.debug("sendEmailValidation() :: event = {}", event);
    LocaleContextHolder.setLocale(event.getLocale());

    Mail mail = new Mail();
    mail.setTo(event.getToken().getUser().getEmail());
    mail.setSubject(Translator.translate("username.registration.title"));
    mail.setType(MailType.VALIDATE_EMAIL);

    Map model = new HashMap();

    model.put("firstName", event.getToken().getUser().getFirstName());
    model.put("lastName", event.getToken().getUser().getLastName());
    model.put("email", event.getToken().getUser().getEmail());
    model.put("token", event.getToken().getToken());
    model.put("expireDate", event.getToken().getExpireDate().toLocalDate());
    model.put("expireTime", event.getToken().getExpireDate().toLocalTime());
    model.put("frontEnd", AppConstants.FRONTEND_URL);

    sendAndSave(mail, model);
  }

  @Override
  @EventListener
  public void sendPasswordReset(PasswordResetEvent event) {
    log.debug("sendPasswordReset() :: event = {}", event);
    LocaleContextHolder.setLocale(event.getLocale());

    Mail mail = new Mail();
    mail.setTo(event.getToken().getUser().getEmail());
    mail.setSubject(Translator.translate("username.forgot-password.title"));
    mail.setType(MailType.FORGOT_PASSWORD);

    Map model = new HashMap();

    model.put("firstName", event.getToken().getUser().getFirstName());
    model.put("lastName", event.getToken().getUser().getLastName());
    model.put("email", event.getToken().getUser().getEmail());
    model.put("token", event.getToken().getToken());
    model.put("expireDate", event.getToken().getExpireDate().toLocalDate());
    model.put("expireTime", event.getToken().getExpireDate().toLocalTime());
    model.put("frontEnd", AppConstants.FRONTEND_URL);

    sendAndSave(mail, model);
  }

  @Override
  public void sendAndSave(Mail mail, Map model) {
    try {
      MimeMessage message = sender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message,
          MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
          StandardCharsets.UTF_8.name());


      String templateName = getTemplateNameByMailType(mail.getType());
      Template template = freeMarkerConfiguration.getTemplate(templateName);
      String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

      mail.setContent(html);

      helper.setTo(mail.getTo());
      helper.setSubject(mail.getSubject());
      helper.setText(html, true);
//      helper.addInline("logo.png", new ClassPathResource("static/image/default.png"));

      log.debug("sendAndSave() :: Sending username = {}", mail);
      sender.send(message);
    } catch (Exception e) {
      log.error("sendAndSave() :: error while sending mail = {}, message = {}", mail,
          e.getMessage());
      mail.setSent(false);
    } finally {
      emailRepository.save(mail);
      log.debug("sendAndSave() :: Email sent = {}", mail);
    }
  }

  private String getTemplateNameByMailType(MailType type) {
    switch (type) {
      case VALIDATE_EMAIL:
        return AppConstants.VALIDATE_EMAIL_EMAIL;
      case FORGOT_PASSWORD:
        return AppConstants.FORGOT_PASSWORD_EMAIL;
      default:
        return null;
    }
  }
}
