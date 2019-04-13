package ma.tiwtiw.bank.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.repository.RoleRepository;
import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.service.RoleService;
import ma.tiwtiw.bank.service.UserService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  private final UserService userService;

  private final RightService rightService;

  public RoleServiceImpl(RoleRepository roleRepository, UserService userService,
      RightService rightService) {
    this.roleRepository = roleRepository;
    this.userService = userService;
    this.rightService = rightService;
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAllAndDeletedIsFalse();
  }

  @Override
  public List<Role> findAll(String username) {
    log.debug("findAll() :: username = {}", username);

    User user = userService.findByEmail(username);

    return roleRepository.findAllByUsers(user);
  }

  @Override
  public Role findById(Long id) {
    return roleRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> {
      log.debug("findById() :: id = {}", id);
      return new ResourceNotFoundException(Translator.translate("exception.role.not-found"));
    });
  }

  @Override
  public void add(String name, List<RightEnum> rightEnums) {
    log.debug("add() :: name = {}, rights = {}", name, rightEnums);

    List<Right> rights = rightEnums.stream()
        .map(rightService::findByRightEnum)
        .collect(Collectors.toList());

    Role role = new Role();
    role.setName(name);
    role.setRights(rights);

    roleRepository.save(role);
  }

  @Override
  public void update(Long id, String name, List<RightEnum> rightEnums) {
    log.debug("update() :: id = {}, name = {}, rights = {}", id, name, rightEnums);

    Role role = findById(id);

    List<Right> rights = rightEnums.stream()
        .map(rightService::findByRightEnum)
        .collect(Collectors.toList());

    role.setName(name);
    role.getRights().clear();
    role.getRights().addAll(rights);

    roleRepository.save(role);
  }

  @Override
  public void delete(Long id) {
    log.debug("delete() :: id = {}", id);
    Role role = findById(id);

    role.setDeleted(true);

    roleRepository.save(role);
  }
}
