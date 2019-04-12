package ma.tiwtiw.bank.repository;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.Token;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.pojo.TokenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends RevisionRepository<Token, Long, Integer>,
    JpaRepository<Token, Long> {

  @Query("select t from Token t where t.user = ?1 and t.token = ?2 and t.type = ?3")
  Optional<Token> findByUserAndTokenAndType(User user, String token, TokenType type);

  @Query("select t from Token t where t.user = ?1 and t.type = ?2 and t.used = false")
  List<Token> findAllByUserAndTypeAndUsedIsFalse(User user, TokenType tokenType);
}
