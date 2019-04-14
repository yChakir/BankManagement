package ma.tiwtiw.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.configuration.AppConstants;
import ma.tiwtiw.bank.dto.Registration;
import ma.tiwtiw.bank.entity.Role;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.service.RoleService;
import ma.tiwtiw.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationBootstrap implements ApplicationListener<ApplicationReadyEvent> {

  private RightService rightService;

  private RoleService roleService;

  private UserService userService;

  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {
    log.debug("onApplicationEvent() :: event = ", event);
    assertRights();
    assertAdminRole();
    assertAdminUser();
  }

  private void assertAdminUser() {
    log.debug("assertAdminUser() :: start");
    try {
      userService.findByEmail(AppConstants.ADMIN_EMAIL);
    } catch (UsernameNotFoundException ex) {
      log.debug("assertAdminUser() :: admin user not found, create it");
      Registration registration = new Registration();
      registration.setUsername(AppConstants.ADMIN_EMAIL);
      registration.setPassword(AppConstants.ADMIN_PASSWORD);
      registration.setFirstName(AppConstants.ADMIN_ROLE_NAME);
      registration.setLastName(AppConstants.ADMIN_ROLE_NAME);

      User user = userService.register(registration);
      Optional<Role> optionalRole = roleService.findByName(AppConstants.ADMIN_ROLE_NAME);
      optionalRole.ifPresent(user.getRoles()::add);
    }
  }

  private void assertAdminRole() {
    log.debug("assertAdminRole() :: start");
    Optional<Role> optionalAdmin = roleService.findByName(AppConstants.ADMIN_ROLE_NAME);

    if (!optionalAdmin.isPresent()) {
      log.debug("assertAdminRole() :: admin role not found, create it");
      Role role = roleService.add(AppConstants.ADMIN_ROLE_NAME, new ArrayList<>());
      roleService.update(role.getId(), role.getName(), new ArrayList<RightEnum>() {{
        add(RightEnum.ALL_RIGHTS);
      }});
    }
    Optional<Role> optionalActivated = roleService.findByName(AppConstants.ACTIVATED_ROLE_NAME);

    if (!optionalActivated.isPresent()) {
      log.debug("assertAdminRole() :: activated role not found, create it");
      roleService.add(AppConstants.ACTIVATED_ROLE_NAME, new ArrayList<>());
    }
  }

  private void assertRights() {
    log.debug("assertRights() :: start");
    Arrays.stream(RightEnum.values()).forEach(rightEnum -> {
      try {
        rightService.findByRightEnum(rightEnum);
      } catch (ResourceNotFoundException ex) {
        log.debug("assertRights() :: right not found, create {}", rightEnum);
        rightService.add(rightEnum, rightEnum.name());
      }
    });
  }

  @Autowired
  public void setRightService(RightService rightService) {
    this.rightService = rightService;
  }

  @Autowired
  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
