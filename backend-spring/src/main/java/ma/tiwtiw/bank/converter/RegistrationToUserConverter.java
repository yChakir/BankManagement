package ma.tiwtiw.bank.converter;

import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RegistrationToUserConverter implements Converter<Registration, User> {

  @Override
  public User convert(Registration registration) {
    return new User() {{
      setFirstName(registration.getFirstName());
      setLastName(registration.getLastName());
      setEmail(registration.getEmail());
      setPassword(registration.getPassword());
    }};
  }
}
