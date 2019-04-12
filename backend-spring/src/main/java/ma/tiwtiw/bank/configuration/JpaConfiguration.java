package ma.tiwtiw.bank.configuration;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaConfiguration {

  @Bean
  org.springframework.data.domain.AuditorAware<String> auditorProvider() {
    return new AuditorAware();
  }

  @Component
  class AuditorAware implements org.springframework.data.domain.AuditorAware<String> {

    public Optional<String> getCurrentAuditor() {
      Optional<String> result = Optional.empty();

      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (authentication != null && authentication.isAuthenticated()) {
        result = Optional.of(authentication.getPrincipal().toString());
      }

      return result;
    }
  }
}
