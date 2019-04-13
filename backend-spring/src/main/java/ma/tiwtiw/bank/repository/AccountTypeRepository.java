package ma.tiwtiw.bank.repository;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends RevisionRepository<AccountType, Long, Integer>,
    JpaRepository<AccountType, Long> {

  List<AccountType> findAllByDeletedIsFalse();

  Optional<AccountType> findByIdAndDeletedIsFalse(Long id);

}
