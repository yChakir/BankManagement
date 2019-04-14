package ma.tiwtiw.bank.service;

import java.util.List;
import java.util.Optional;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.pojo.RightEnum;

public interface RoleService {

  List<Role> findAll();

  Role findById(Long id);

  Optional<Role> findByName(String name);

  Role add(String name, List<RightEnum> rightEnums);

  Role update(Long id, String name, List<RightEnum> rightEnums);

  Role delete(Long id);

}
