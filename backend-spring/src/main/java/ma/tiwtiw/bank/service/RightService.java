package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.pojo.RightEnum;

public interface RightService {

  List<Right> findAll();

  List<Right> findAll(String username);

  Right findByRightEnum(RightEnum rightEnum);

  void update(RightEnum rightEnum, String description);

  void activate(RightEnum rightEnum);

  void deactivate(RightEnum rightEnum);

}
