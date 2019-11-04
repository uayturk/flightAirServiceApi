package com.ufuk.flightAirServiceApi.exception;

/**
 * An unchecked exception.
 *
 * <p>
 * Extends {@link RuntimeException}
 * </p>
 */
public class ExternalServiceException extends RuntimeException {

  private final String message;

  public ExternalServiceException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
