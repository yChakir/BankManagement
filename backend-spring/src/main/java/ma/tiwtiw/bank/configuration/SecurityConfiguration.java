package ma.tiwtiw.bank.configuration;

import javax.annotation.Resource;
import ma.tiwtiw.bank.filter.CorsFilter;
import ma.tiwtiw.bank.filter.JwtAuthenticationFilter;
import ma.tiwtiw.bank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;

  private final CorsFilter corsFilter;

  @Resource(name = "userService")
  private UserDetailsService userDetailsService;

  @Autowired
  public SecurityConfiguration(PasswordEncoder passwordEncoder, CorsFilter corsFilter) {
    this.passwordEncoder = passwordEncoder;
    this.corsFilter = corsFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/login").permitAll()
        .antMatchers("/static/**").permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

    http.addFilter(new JwtAuthenticationFilter(authenticationManagerBean()));
    http.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
  }

  @Override
  @Bean(name = "myAuthManager")
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
