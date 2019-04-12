package ma.tiwtiw.bank.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.dto.Credentials;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.exception.ServiceException;
import ma.tiwtiw.bank.util.JwtUtil;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;

  private JwtUtil jwtUtil;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    log.debug("attemptAuthentication() :: authentication attempt.");
    Credentials user;

    try {
      user = new ObjectMapper().readValue(request.getInputStream(), Credentials.class);
      log.debug("attemptAuthentication() :: credentials = {}", user);

      Authentication authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(
              user.getUsername(),
              user.getPassword()
          ));

      log.debug("attemptAuthentication() :: authentication succeeded = {}", user);

      return authentication;
    } catch (BadCredentialsException e) {
      log.debug("attemptAuthentication() :: authentication failed, message = ", e.getMessage());
      response.setContentType("application/json;charset=UTF-8");
      ClientException exception = new ClientException(
          Translator.translate("exception.auth.bad-credentials"));
      exception.setStackTrace(new StackTraceElement[]{});
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      writeQuietly(response, exception);
    } catch (InternalAuthenticationServiceException e) {
      log.debug("attemptAuthentication() :: authentication failed, message = ", e.getMessage());
      if (e.getMessage().equals(Translator.translate("exception.auth.user-not-active"))) {
        ClientException exception = new ClientException(e.getMessage());
        exception.setStackTrace(new StackTraceElement[]{});
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        writeQuietly(response, exception);
      }
    } catch (IOException e) {
      log.debug("attemptAuthentication() :: authentication failed, message = ", e.getMessage());
      response.setContentType("application/json;charset=UTF-8");
      ServiceException exception = new ServiceException(
          Translator.translate("exception.internal-error"));
      exception.setStackTrace(new StackTraceElement[]{});
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      writeQuietly(response, exception);
    }

    return null;
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication authResult) {
    User user = (User) authResult.getPrincipal();
    log.debug("successfulAuthentication() :: user = {}", user.getEmail());

    String token = jwtUtil.getToken(user);

    log.debug("successfulAuthentication() :: adding access token to headers, user = {}, token = {}",
        user.getEmail(), token);
    response.addHeader(JwtUtil.HEADER_STRING, JwtUtil.TOKEN_PREFIX + " " + token);
  }

  private String convertObjectToJson(Object object) throws JsonProcessingException {
    if (object == null) {
      return null;
    }
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writeValueAsString(object);
  }

  private void writeQuietly(HttpServletResponse response, Object object) {
    try {
      response.getWriter().write(convertObjectToJson(object));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
