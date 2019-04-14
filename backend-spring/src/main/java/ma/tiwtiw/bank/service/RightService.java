package ma.tiwtiw.bank.service;

import java.util.List;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.pojo.RightEnum;

public interface RightService {

  List<Right> findAll();

  List<Right> findAll(String username);

  Right findByRightEnum(RightEnum rightEnum);

  Right add(RightEnum rightEnum, String description);

  Right update(RightEnum rightEnum, String description);

  Right activate(RightEnum rightEnum);

  Right deactivate(RightEnum rightEnum);

}
