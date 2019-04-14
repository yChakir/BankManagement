package ma.tiwtiw.bank.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import ma.tiwtiw.bank.entity.Right;
import ma.tiwtiw.bank.pojo.RightEnum;
import ma.tiwtiw.bank.service.RightService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthoritiesToRightsConverter implements Converter<List<GrantedAuthority>, List<Right>> {

  private final RightService rightService;

  public AuthoritiesToRightsConverter(RightService rightService) {
    this.rightService = rightService;
  }

  @Override
  public List<Right> convert(List<GrantedAuthority> grantedAuthorities) {
    return grantedAuthorities.stream()
        .map(this::stringToRightEnum)
        .map(rightService::findByRightEnum)
        .collect(Collectors.toList());
  }

  private RightEnum stringToRightEnum(GrantedAuthority grantedAuthority) {
    return Arrays.stream(RightEnum.values())
        .filter(rightEnum -> rightEnum.name().equals(grantedAuthority.getAuthority()))
        .findAny().orElse(null);
  }
}
