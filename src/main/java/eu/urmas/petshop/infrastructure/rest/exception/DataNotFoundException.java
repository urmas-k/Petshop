package eu.urmas.petshop.infrastructure.rest.exception;

import lombok.Getter;

@Getter
public class DataNotFoundException extends RuntimeException {
  private final String message;

  public DataNotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
