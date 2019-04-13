package ma.tiwtiw.bank.service.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.exception.ResourceNotFoundException;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.repository.RightRepository;
import ma.tiwtiw.bank.service.RightService;
import ma.tiwtiw.bank.util.Translator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class RightServiceImpl implements RightService {

  private final RightRepository rightRepository;

  public RightServiceImpl(RightRepository rightRepository) {
    this.rightRepository = rightRepository;
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
    return rightRepository.findAllByRoles_Users_Email(username);
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
  public void update(RightEnum rightEnum, String description) {
    log.debug("update() :: name = {}, description = {}", rightEnum, description);
    Right right = findByRightEnum(rightEnum);

    right.setDescription(description);

    rightRepository.save(right);
  }

  @Override
  public void activate(RightEnum rightEnum) {
    log.debug("activate() :: name = {}", rightEnum);
    Right right = findByRightEnum(rightEnum);

    right.setActive(true);

    rightRepository.save(right);
  }

  @Override
  public void deactivate(RightEnum rightEnum) {
    log.debug("deactivate() :: name = {}", rightEnum);
    Right right = findByRightEnum(rightEnum);

    right.setActive(false);

    rightRepository.save(right);
  }
}
