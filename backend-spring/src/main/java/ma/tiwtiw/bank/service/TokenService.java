package ma.tiwtiw.bank.service;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.Token;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.pojo.TokenType;

public interface TokenService {

  Token saveForUser(User user, TokenType type);

  Optional<Token> findByUserAndTokenAndType(User user, String token, TokenType type);

  Token save(Token token);

  List<Token> findAllByUserAndTypeAndNotUsed(User user, TokenType tokenType);
}
