package ma.tiwtiw.bank.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.tiwtiw.bank.entity.User;
import ma.tiwtiw.bank.service.UserService;
import ma.tiwtiw.bank.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class JwtAuthorizationFilter extends HandlerInterceptorAdapter {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader(JwtUtil.HEADER_STRING);

    if (token != null && token.startsWith(JwtUtil.TOKEN_PREFIX)) {
      String username = JwtUtil.parse(token);

      User user = userService.findByEmail(username);

      if (user != null) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
            user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    return super.preHandle(request, response, handler);
  }
}
