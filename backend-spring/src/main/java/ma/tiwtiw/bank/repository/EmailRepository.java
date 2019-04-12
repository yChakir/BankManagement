package ma.tiwtiw.bank.repository;

import ma.tiwtiw.bank.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends RevisionRepository<Mail, Long, Integer>,
    JpaRepository<Mail, Long> {

}
