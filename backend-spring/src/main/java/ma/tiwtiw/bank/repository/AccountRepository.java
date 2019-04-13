package ma.tiwtiw.bank.repository;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.Account;
import ma.tiwtiw.bank.pojo.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  List<Account> findAllByDeletedIsFalse();

  List<Account> findAllByUser_EmailAndDeletedIsFalse(String email);

  List<Account> findAllByStatusAndDeletedIsFalse(AccountStatus status);

  int countAllByType_Name(String name);

  int countAllByUser_Email(String email);

  Optional<Account> findByIdAndDeletedIsFalse(Long id);
}
