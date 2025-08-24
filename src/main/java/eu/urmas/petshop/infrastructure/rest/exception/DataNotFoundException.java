package eu.urmas.petshop.infrastructure.rest.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  private final String message;

  public DataNotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
