package ma.tiwtiw.bank.repository;

import java.util.Optional;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.pojo.RightEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RightRepository extends RevisionRepository<Right, Long, Integer>,
    JpaRepository<Right, Long> {

  Optional<Right> findByName(RightEnum name);
}
