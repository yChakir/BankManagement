package ma.tiwtiw.bank.repository;

import ma.tiwtiw.bank.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends RevisionRepository<Email, Long, Integer>,
    JpaRepository<Email, Long> {

}
