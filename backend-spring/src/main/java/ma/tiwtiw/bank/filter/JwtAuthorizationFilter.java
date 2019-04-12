package ma.tiwtiw.bank.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ma.tiwtiw.bank.util.JwtUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class JwtAuthorizationFilter extends HandlerInterceptorAdapter {

  private JwtUtil jwtUtil;

  public JwtAuthorizationFilter(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    String token = request.getHeader(JwtUtil.HEADER_STRING);

    if (token != null && token.startsWith(JwtUtil.TOKEN_PREFIX)) {
      SecurityContextHolder.getContext().setAuthentication(jwtUtil.parse(token));
    }

    return super.preHandle(request, response, handler);
  }
}
