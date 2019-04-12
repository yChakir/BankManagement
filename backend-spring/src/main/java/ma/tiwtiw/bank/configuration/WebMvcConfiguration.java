package ma.tiwtiw.bank.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final HandlerInterceptor jwtAuthorizationFilter;

  private final Converter registrationToUserConverter;

  public WebMvcConfiguration(HandlerInterceptor jwtAuthorizationFilter,
      Converter registrationToUserConverter) {
    this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    this.registrationToUserConverter = registrationToUserConverter;
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(registrationToUserConverter);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtAuthorizationFilter);
  }
}
