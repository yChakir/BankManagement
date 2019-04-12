package ma.tiwtiw.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ServiceException extends RuntimeException {

  public ServiceException(String message) {
    super(message);
  }
}
