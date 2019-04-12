package ma.tiwtiw.bank.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    response.addHeader("Access-Control-Allow-Origin", "*");
    response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
    response.addHeader("Access-Control-Allow-Headers",
        "Origin, Authorization, Content-Type, Headers, X-Requested-With, Accept, Access-Control-Request-Methods, Access-Control-Request-Headers, Accept-Language, Accept-Encoding");
    response.addHeader("Access-Control-Expose-Headers",
        "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Authorization");

    filterChain.doFilter(request, response);
  }
}
