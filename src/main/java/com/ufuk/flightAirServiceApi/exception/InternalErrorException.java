package com.ufuk.flightAirServiceApi.exception;

/**
 * Wraps unexpected exceptions.
 *
 * <p>
 * Extends {@link RuntimeException}
 * </p>
 */
public class InternalErrorException extends RuntimeException {

  private final String message;

  public InternalErrorException(String message) {
    super(message);
    this.message = message;
  }

  public InternalErrorException(String message, Exception exception) {
    super(message, exception);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}