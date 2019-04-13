package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.pojo.RightEnum;

public interface RoleService {

  List<Role> findAll();

  List<Role> findAll(String username);

  Role findById(Long id);

  void add(String name, List<RightEnum> rightEnums);

  void update(Long id, String name, List<RightEnum> rightEnums);

  void delete(Long id);

}
