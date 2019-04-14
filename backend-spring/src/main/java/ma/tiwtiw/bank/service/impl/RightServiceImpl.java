package ma.tiwtiw.bank.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.exception.ClientException;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.repository.RightRepository;
import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.service.UserService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RightServiceImpl implements RightService {

  private RightRepository rightRepository;

  private UserService userService;

  private ConversionService conversionService;

  @Autowired
  public void setRightRepository(RightRepository rightRepository) {
    this.rightRepository = rightRepository;
  }

  @Autowired
  public void setConversionService(ConversionService conversionService) {
    this.conversionService = conversionService;
  }

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Right> findAll() {
    log.debug("findAll()");
    return rightRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Right> findAll(String username) {
    log.debug("findAll() :: username = {}", username);

    User user = userService.findByEmail(username);

    return user.getAuthorities().stream()
        .map(this::stringToRightEnum)
        .map(this::findByRightEnum)
        .collect(Collectors.toList());
  }

  private RightEnum stringToRightEnum(GrantedAuthority grantedAuthority) {
    return Arrays.stream(RightEnum.values())
        .filter(rightEnum -> rightEnum.name().equals(grantedAuthority.getAuthority()))
        .findAny().orElse(null);
  }

  @Override
  @Transactional(readOnly = true)
  public Right findByRightEnum(RightEnum rightEnum) {
    return rightRepository.findByName(rightEnum).orElseThrow(() -> {
      log.warn("findByRightEnum() :: No right with the name = {}", rightEnum);
      return new ResourceNotFoundException(Translator.translate("exception.right.not-found"));
    });
  }

  @Override
  public Right add(RightEnum rightEnum, String description) {
    log.debug("add() :: name = {}, description = {}", rightEnum, description);

    Right right = new Right();
    right.setName(rightEnum);
    right.setDescription(description);

    if (rightEnum.equals(RightEnum.ALL_RIGHTS)) {
      right.setActive(true);
    }

    return rightRepository.save(right);
  }

  @Override
  public Right update(RightEnum rightEnum, String description) {
    log.debug("update() :: name = {}, description = {}", rightEnum, description);
    Right right = findByRightEnum(rightEnum);

    right.setDescription(description);

    return rightRepository.save(right);
  }

  @Override
  public Right activate(RightEnum rightEnum) {
    log.debug("activate() :: name = {}", rightEnum);
    Right right = findByRightEnum(rightEnum);

    right.setActive(true);

    return rightRepository.save(right);
  }

  @Override
  public Right deactivate(RightEnum rightEnum) {
    log.debug("deactivate() :: name = {}", rightEnum);
    if (rightEnum.equals(RightEnum.ALL_RIGHTS)) {
      throw new ClientException(Translator.translate("exception.right.deactivate-all-rights"));
    }

    Right right = findByRightEnum(rightEnum);


    right.setActive(false);

    return rightRepository.save(right);
  }
}
