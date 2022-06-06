package com.smartharwareshop.shoppingcart.exception;

public class InvalidQuantityException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  public InvalidQuantityException(String ex) {
    super(ex);
  }
}
