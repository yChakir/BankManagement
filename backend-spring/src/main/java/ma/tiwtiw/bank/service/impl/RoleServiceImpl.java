package ma.tiwtiw.bank.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.configuration.AppConstants;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.repository.RoleRepository;
import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.service.RoleService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  private final RightService rightService;

  public RoleServiceImpl(RoleRepository roleRepository,
      RightService rightService) {
    this.roleRepository = roleRepository;
    this.rightService = rightService;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Role> findAll() {
    log.debug("findAll()");
    return roleRepository.findAllByDeletedIsFalse();
  }

  @Override
  @Transactional(readOnly = true)
  public Role findById(Long id) {
    return roleRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() -> {
      log.debug("findById() :: id = {}", id);
      return new ResourceNotFoundException(Translator.translate("exception.role.not-found"));
    });
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Role> findByName(String name) {
    return roleRepository.findByName(name);
  }

  @Override
  public Role add(String name, List<RightEnum> rightEnums) {
    log.debug("add() :: name = {}, rights = {}", name, rightEnums);

    roleRepository.findByName(name).ifPresent(role -> {
      log.warn("add() :: role with the name = {}, already exist", name);
      throw new ClientException(Translator.translate("exception.role.already-exist"));
    });

    List<Right> rights = rightEnums.stream()
        .map(rightService::findByRightEnum)
        .collect(Collectors.toList());

    Role role = new Role();
    role.setName(name);
    role.setRights(rights);

    return roleRepository.save(role);
  }

  @Override
  public Role update(Long id, String name, List<RightEnum> rightEnums) {
    log.debug("update() :: id = {}, name = {}, rights = {}", id, name, rightEnums);

    roleRepository.findByName(name).ifPresent(role -> {
      if (!role.getId().equals(id)) {
        log.warn("add() :: role with the name = {}, already exist", name);
        throw new ClientException(Translator.translate("exception.role.already-exist"));
      }
    });

    Role role = findById(id);

    if (role.getName().equals(AppConstants.ADMIN_ROLE_NAME)) {
      throw new ClientException(Translator.translate("exception.role.update-admin"));
    }
    if (
        role.getName().equals(AppConstants.ACTIVATED_ROLE_NAME) &&
        !name.equals(AppConstants.ACTIVATED_ROLE_NAME)
    ) {
      throw new ClientException(Translator.translate("exception.role.update-activated-name"));
    }

    List<Right> rights = rightEnums.stream()
        .map(rightService::findByRightEnum)
        .collect(Collectors.toList());

    role.setName(name);
    role.getRights().clear();
    role.getRights().addAll(rights);

    return roleRepository.save(role);
  }

  @Override
  public Role delete(Long id) {
    log.debug("delete() :: id = {}", id);
    Role role = findById(id);

    if (role.getName().equals(AppConstants.ADMIN_ROLE_NAME)) {
      throw new ClientException(Translator.translate("exception.role.delete-admin"));
    }

    if (role.getName().equals(AppConstants.ACTIVATED_ROLE_NAME)) {
      throw new ClientException(Translator.translate("exception.role.delete-activated"));
    }

    role.setDeleted(true);

    return roleRepository.save(role);
  }
}
