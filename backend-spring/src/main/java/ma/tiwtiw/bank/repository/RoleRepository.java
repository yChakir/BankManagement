package ma.tiwtiw.bank.repository;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends RevisionRepository<Role, Long, Integer>, JpaRepository<Role, Long> {

  List<Role> findAllAndDeletedIsFalse();

  List<Role> findAllByUsers(User user);

  Optional<Role> findByIdAndDeletedIsFalse(Long id);
}
