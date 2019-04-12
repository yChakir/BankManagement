package ma.tiwtiw.bank.repository;

import java.util.Optional;
import ma.tiwtiw.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RevisionRepository<User, Long, Integer>,
    JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
}
