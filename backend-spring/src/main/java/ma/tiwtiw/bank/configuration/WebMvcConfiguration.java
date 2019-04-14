package ma.tiwtiw.bank.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private HandlerInterceptor jwtAuthorizationFilter;

  private Converter registrationToUserConverter;

  private Converter accountToAccountResultConverter;

  @Autowired
  public void setRegistrationToUserConverter(
      Converter registrationToUserConverter) {
    this.registrationToUserConverter = registrationToUserConverter;
  }

  @Autowired
  public void setAccountToAccountResultConverter(
      Converter accountToAccountResultConverter) {
    this.accountToAccountResultConverter = accountToAccountResultConverter;
  }

  @Autowired
  public void setJwtAuthorizationFilter(HandlerInterceptor jwtAuthorizationFilter) {
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(registrationToUserConverter);
    registry.addConverter(accountToAccountResultConverter);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtAuthorizationFilter);
  }
}
