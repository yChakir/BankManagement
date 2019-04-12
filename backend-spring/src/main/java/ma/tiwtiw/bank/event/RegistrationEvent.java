package ma.tiwtiw.bank.event;

import java.util.Locale;
import lombok.Data;
import ma.tiwtiw.bank.entity.Token;
import org.springframework.context.ApplicationEvent;

@Data
public class RegistrationEvent extends ApplicationEvent {

  private Token token;

  private Locale locale;

  public RegistrationEvent(Object source, Token token, Locale locale) {
    super(source);
    this.token = token;
    this.locale = locale;
  }
}
