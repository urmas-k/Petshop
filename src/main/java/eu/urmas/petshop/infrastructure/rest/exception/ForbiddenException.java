package eu.urmas.petshop.infrastructure.rest.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class ForbiddenException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  private final String message;

  public ForbiddenException(String message) {
    super(message);
    this.message = message;
  }
}
