package ma.tiwtiw.bank.service.impl;


import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Token;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.pojo.TokenType;
import ma.tiwtiw.bank.repository.TokenRepository;
import ma.tiwtiw.bank.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class TokenServiceImpl implements TokenService {

  private TokenRepository repository;

  @Autowired
  public void setRepository(TokenRepository repository) {
    this.repository = repository;
  }

  @Override
  public Token saveForUser(User user, TokenType type) {
    log.debug("saveForUser() :: user = {}, type = {}", user.getEmail(), type);
    return repository.save(new Token(user, type));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Token> findByUserAndTokenAndType(User user, String token, TokenType type) {
    log.debug("findByUserAndTokenAndType() :: user = {}, token = {}, type = {}", user, token, type);
    return repository.findByUserAndTokenAndType(user, token, type);
  }

  @Override
  public Token save(Token token) {
    log.debug("save() :: token = {}", token);
    return repository.save(token);
  }

  @Override
  public List<Token> findAllByUserAndTypeAndNotUsed(User user, TokenType tokenType) {
    log.debug("findAllByUserAndTypeAndNotUsed() :: user = {}, type = {}", user, tokenType);
    return repository.findAllByUserAndTypeAndUsedIsFalse(user, tokenType);
  }
}
