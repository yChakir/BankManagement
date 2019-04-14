package ma.tiwtiw.bank.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.jws.soap.SOAPBinding.Use;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.configuration.AppConstants;
import ma.tiwtiw.bank.dto.ChangePassword;
import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.dto.ResetPassword;
import ma.tiwtiw.bank.dto.ValidateEmail;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.entity.Token;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.event.PasswordResetEvent;
import ma.tiwtiw.bank.event.RegistrationEvent;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.pojo.TokenType;
import ma.tiwtiw.bank.repository.UserRepository;
import ma.tiwtiw.bank.service.RoleService;
import ma.tiwtiw.bank.service.TokenService;
import ma.tiwtiw.bank.service.UserService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

  private UserRepository repository;

  private TokenService tokenService;

  private PasswordEncoder encoder;

  private ApplicationEventPublisher publisher;

  private ConversionService conversionService;

  private RoleServiceImpl roleService;

  @Autowired
  public void setConversionService(ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @Autowired
  public void setRoleService(RoleServiceImpl roleService) {
    this.roleService = roleService;
  }

  @Autowired
  public void setRepository(UserRepository repository) {
    this.repository = repository;
  }

  @Autowired
  public void setTokenService(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Autowired
  public void setEncoder(PasswordEncoder encoder) {
    this.encoder = encoder;
  }

  @Autowired
  public void setPublisher(ApplicationEventPublisher publisher) {
    this.publisher = publisher;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.debug("loadUserByUsername() :: username = {}", username);
    User user = repository.findByEmail(username).orElseThrow(() -> {
      log.warn("loadUserByUsername() :: user not found, username = {}", username);
      return new UsernameNotFoundException(Translator.translate("exception.user.not-found"));
    });

    if (user.isDeleted()) {
      log.warn("loadUserByUsername() :: the user '{}' is deleted.", username);
      throw new ClientException(Translator.translate("exception.auth.user-deleted"));
    }

    if (!user.isActive()) {
      log.warn("loadUserByUsername() :: the user '{}' is not active.", username);
      throw new ClientException(Translator.translate("exception.auth.user-not-active"));
    }

    log.debug("loadUserByUsername() :: the user '{}' found and active.", username);
    return user;
  }

  @Override
  @Transactional(readOnly = true)
  public User findByEmail(String email) {
    log.debug("findByEmail() :: username = {}", email);
    return repository.findByEmail(email).orElseThrow(
        () -> {
          log.warn("findByEmail() :: No user with email = {}", email);
          return new UsernameNotFoundException(Translator.translate("exception.user.not-found"));
        });
  }

  @Override
  public User delete(String email) {
    log.debug("delete() :: username = {}", email);
    User user = repository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException(Translator.translate("exception.user.not-found")));

    if (user.isDeleted()) {
      log.warn("delete() :: the user '{}' is deleted.", email);
      throw new ClientException(Translator.translate("exception.user.already-deleted"));
    }

    // todo handle dependencies...

    user.setDeleted(true);

    return repository.save(user);
  }

  @Override
  public User register(Registration registration) {
    log.debug("register() :: registration = {}", registration);
    Optional<User> alreadyExit = repository.findByEmail(registration.getUsername());

    if (alreadyExit.isPresent()) {
      log.warn("register() :: registration already exist: {}", registration.getUsername());
      throw new ClientException(Translator.translate("exception.user.already-exist"));
    }

    registration.setPassword(encoder.encode(registration.getPassword()));

    User user = conversionService.convert(registration, User.class);

    log.debug("register() :: saving user: {}", user);
    user = repository.save(user);

    if (user != null) {
      log.debug("register() :: saving token.");
      Token token = tokenService.saveForUser(user, TokenType.REGISTRATION);

      publisher.publishEvent(new RegistrationEvent(this, token, LocaleContextHolder.getLocale()));
    }

    return user;
  }

  @Override
  public User emailValidation(ValidateEmail emailValidation) {
    log.debug("emailValidation() :: emailValidation = {}", emailValidation);
    Optional<User> optionalUser = repository
        .findByEmail(emailValidation.getUsername());

    log.debug("emailValidation() :: check user exists: {}", emailValidation.getUsername());
    User user = optionalUser.orElseThrow(() -> {
      log.debug("emailValidation() :: check user exists: {}", emailValidation.getUsername());
      return new ClientException(Translator.translate("exception.token.invalid"));
    });

    Optional<Token> tokenOptional = tokenService
        .findByUserAndTokenAndType(user, emailValidation.getToken(), TokenType.REGISTRATION);

    log.debug("emailValidation() :: check token exists: {}", emailValidation.getToken());
    Token token = tokenOptional.orElseThrow(
        () -> new ClientException(Translator.translate("exception.token.invalid")));

    log.debug("emailValidation() :: check token is used: {}, used = {}", emailValidation.getToken(),
        token.isUsed());
    if (token.isUsed()) {
      throw new ClientException(Translator.translate("exception.token.already-used"));
    }

    if (token.getExpireDate().isBefore(LocalDateTime.now())) {
      log.debug("emailValidation() :: token expired: token = {}, used = {}",
          emailValidation.getToken(), token.getExpireDate());
      throw new ClientException(Translator.translate("exception.token.expired"));
    }

    log.debug("emailValidation() :: updating and saving user and token...");
    token.setUsed(true);
    user.setActive(true);

    Optional<Role> role = roleService.findByName(AppConstants.ACTIVATED_ROLE_NAME);
    role.ifPresent(user.getRoles()::add);

    tokenService.save(token);
    return repository.save(user);
  }

  @Override
  public void forgotPassword(String email) {
    log.debug("forgotPassword() :: username = {}", email);
    Optional<User> optionalUser = repository.findByEmail(email);

    log.debug("forgotPassword() :: check if user present username = {}, present = {}", email,
        optionalUser.isPresent());
    optionalUser.ifPresent(user -> {
      List<Token> tokens = tokenService
          .findAllByUserAndTypeAndNotUsed(user, TokenType.PASSWORD_RESET);

      log.debug(
          "forgotPassword() :: check if there is already a valid and unused token for the user.");
      Optional<Token> optionalToken = tokens.stream()
          .filter(token -> token.getExpireDate().isAfter(LocalDateTime.now()))
          .findAny();

      Token token;
      if (optionalToken.isPresent()) {
        log.debug(
            "forgotPassword() :: found a valid token in the database, update expiration date.");
        token = optionalToken.get();
        token.setExpireDate(LocalDateTime.now().plusDays(1));
      } else {
        log.debug("forgotPassword() :: no valid token found, create a new one.");
        token = new Token(user, TokenType.PASSWORD_RESET);
      }

      tokenService.save(token);

      publisher.publishEvent(new PasswordResetEvent(this, token, LocaleContextHolder.getLocale()));
    });
  }

  @Override
  public User resetPassword(ResetPassword resetPassword) {
    log.debug("resetPassword() :: resetPassword = {}", resetPassword);
    Optional<User> optionalUser = repository.findByEmail(resetPassword.getUsername());

    User user = optionalUser.orElseThrow(
        () -> {
          log.debug("resetPassword() :: user do not exist.");
          return new ClientException(Translator.translate("exception.token.invalid"));
        });

    Optional<Token> tokenOptional = tokenService
        .findByUserAndTokenAndType(user, resetPassword.getToken(), TokenType.PASSWORD_RESET);

    Token token = tokenOptional.orElseThrow(
        () -> {
          log.warn("resetPassword() :: token do not exist, token = {}", resetPassword.getToken());
          return new ClientException(Translator.translate("exception.token.invalid"));
        });

    if (token.isUsed()) {
      log.warn("resetPassword() :: token is used, token = {}", token);
      throw new ClientException(Translator.translate("exception.token.already-used"));
    }

    if (token.getExpireDate().isBefore(LocalDateTime.now())) {
      log.warn("resetPassword() :: token expired, token = {}", token);
      throw new ClientException(Translator.translate("exception.token.expired"));
    }

    log.debug("resetPassword() :: token is valid, update and save user and token.");
    token.setUsed(true);
    user.setPassword(encoder.encode(resetPassword.getPassword()));

    tokenService.save(token);
    return repository.save(user);
  }

  @Override
  public User changePassword(String email, ChangePassword changePassword) {
    log.debug("changePassword() :: username = {}, changePassword = {}", email, changePassword);

    if (!changePassword.getConfirmation().equals(changePassword.getNewPassword())) {
      log.warn("changePassword() :: new password do not match confirmation.");
      throw new ClientException(Translator.translate("exception.change-password.not-match"));
    }

    if (changePassword.getNewPassword().equals(changePassword.getCurrentPassword())) {
      log.warn("changePassword() :: new password matches old one.");
      throw new ClientException(Translator.translate("exception.change-password.match-old"));
    }

    User user = findByEmail(email);

    if (!encoder.matches(changePassword.getCurrentPassword(), user.getPassword())) {
      log.warn("changePassword() :: old password is invalid.");
      throw new ClientException(Translator.translate("exception.change-password.wrong-password"));
    }

    log.debug("changePassword() :: all test passed, changing the password.");
    user.setPassword(encoder.encode(changePassword.getNewPassword()));

    return repository.save(user);
  }
}
