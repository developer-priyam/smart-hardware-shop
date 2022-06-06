package com.smartharwareshop.shoppingcart.exception;

public class InvalidUserException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidUserException(String ex) {
    super(ex);
  }
}
